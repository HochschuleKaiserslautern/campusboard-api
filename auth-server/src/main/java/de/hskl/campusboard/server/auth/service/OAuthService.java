/*
/* *
/* * Copyright 2016 Hochschule Kaiserslautern
/* *
/* * This file is part of de.hskl.campusboard.server.auth-server.
/* *
/* * de.hskl.campusboard.server.auth-server is free software: you can redistribute it and/or modify
/* * it under the terms of the GNU General Public License as published by
/* * the Free Software Foundation, either version 3 of the License, or
/* * (at your option) any later version.
/* *
/* * de.hskl.campusboard.server.auth-server is distributed in the hope that it will be useful,
/* * but WITHOUT ANY WARRANTY; without even the implied warranty of
/* * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/* * GNU General Public License for more details.
/* *
/* * You should have received a copy of the GNU General Public License
/* * along with de.hskl.campusboard.server.auth-server.  If not, see <http://www.gnu.org/licenses/>.
/* *
 */

package de.hskl.campusboard.server.auth.service;

import de.hskl.campusboard.server.auth.entity.IClient;
import de.hskl.campusboard.server.auth.entity.IClientSecret;
import de.hskl.campusboard.server.auth.entity.IRefreshToken;
import de.hskl.campusboard.server.auth.security.JwtTokenSignService;
import de.hskl.campusboard.server.conf.PropertyName;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder;
import de.hskl.campusboard.server.security.JwtTokenVerifyService;
import de.hskl.campusboard.server.security.JwtTokenVerifyService.TOKEN_PAYLOAD_FIELDS;
import java.time.LocalDateTime;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.TokenType;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public abstract class OAuthService<CS extends IClientSecret, C extends IClient<CS>, RT extends IRefreshToken>
{
    @Inject
    Logger log;

    @Inject
    JwtTokenSignService jwtTokenService;

    @Inject
    JwtTokenVerifyService jwtTokenVerifyService;

    @Inject
    OAuthErrorBuilder oAuthErrorBuilder;
    
    @Inject
    @PropertyName("ACCESS_TOKEN_DEFAULT_EXPIRES")
    private int accessTokenDefaultExpires;

    public abstract C findClientByIdAndSecret(String clientId, String clientSecret);

    public abstract RT findRefreshToken(final int refreshTokenId);

    public abstract RT insertNewRefreshToken(RT newRefreshToken);

    public abstract RT getNewInstanceOfRefreshToken();

    public abstract boolean validateCredentials(String username, String password);

    public abstract void invalidateToken(RT tokenFromDatabase);

    public Response getAccessTokenByPassword(String username, String password, String clientId) throws OAuthSystemException
    {
        log.trace("getAccessTokenByPassword(): {}", () -> "username: " + username + ", password: HIDDEN_FROM_LOG , clientId: " + clientId);

        if (!this.validateCredentials(username, password))
        {
            return oAuthErrorBuilder.createOauthErrorResponse(OAuthErrorBuilder.ERROR_TYPES.invalid_grant, "invalid username / password combination");
        }

        return getValidNewTokenResponse(clientId, username);
    }

    public Response getAccessTokenByRefreshToken(String oldRefreshToken, String clientId) throws OAuthSystemException
    {
        log.trace("getAccessTokenByRefreshToken(): {}", () -> "oldRefreshToken: " + oldRefreshToken + ", clientId: " + clientId);

        RT tokenFromDatabase;

        try
        {
            tokenFromDatabase = isValidRefreshToken(oldRefreshToken);
        }
        catch (SecurityException validationException)
        {
            return oAuthErrorBuilder.createOauthErrorResponse(OAuthErrorBuilder.ERROR_TYPES.invalid_grant, validationException.getMessage());
        }

        this.invalidateToken(tokenFromDatabase);
        return getValidNewTokenResponse(clientId, tokenFromDatabase.getUsername());
    }

    public Response getValidNewTokenResponse(String clientId, String username) throws OAuthSystemException
    {
        log.trace("getValidNewTokenResponse(): {}", () -> "clientId: " + clientId + ", username: " + username);

        String accessToken = this.jwtTokenService.createSignedAccessToken(username, clientId);

        LocalDateTime issuedDateTime = LocalDateTime.now();

        RT newRefreshToken = getFilledRefreshTokenInstance(clientId, username, issuedDateTime);
        newRefreshToken = this.insertNewRefreshToken(newRefreshToken);

        String token = this.jwtTokenService.createSignedRefreshToken(newRefreshToken);

        // some code
        OAuthResponse r = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .setTokenType(TokenType.BEARER.name().toLowerCase())
                .setExpiresIn(String.valueOf(accessTokenDefaultExpires))
                .setAccessToken(accessToken)
                .setRefreshToken(token).buildJSONMessage();

        return Response.status(r.getResponseStatus()).entity(r.getBody()).build();
    }

    public RT getFilledRefreshTokenInstance(String clientId, String username, LocalDateTime issuedDateTime)
    {
        log.trace("createNewEmptyRefreshToken(): {}", () -> "clientId: " + clientId + ", username: " + username);

        RT newRefreshToken = this.getNewInstanceOfRefreshToken();
        newRefreshToken.setClientId(clientId);
        newRefreshToken.setIssuedDateTime(issuedDateTime);
        newRefreshToken.setRevoked(false);
        newRefreshToken.setUsername(username);

        return newRefreshToken;
    }

    public GrantType getValidateGrantType(String grantType)
    {
        log.trace("getValidateGrantType(): {}", () -> "grantType: " + grantType);

        if (grantType == null)
        {
            throw new NullPointerException("grant_type can't be null");
        }

        String upperCaseGrantType = grantType.toUpperCase();

        if (GrantType.PASSWORD.name().equals(upperCaseGrantType))
        {
            return GrantType.PASSWORD;
        }

        if (GrantType.REFRESH_TOKEN.name().equals(upperCaseGrantType))
        {
            return GrantType.REFRESH_TOKEN;
        }

        return null;
    }

    public boolean isClientValid(String clientId, String clientSecrect)
    {
        log.trace("isClientValid(): {}", () -> "clientId: " + clientId + ", clientSecrect: HIDDEN_FROM_LOG");

        if (clientId == null)
        {
            throw new NullPointerException("client id can't be null");
        }
        if (clientSecrect == null)
        {
            throw new NullPointerException("clientSecrect can't be null");
        }

        C c = this.findClientByIdAndSecret(clientId, clientSecrect);

        return (c != null);
    }

    public RT isValidRefreshToken(String refreshToken) throws SecurityException
    {
        log.trace("isValidRefreshToken(): {}", () -> "refreshToken: " + refreshToken);

        if (refreshToken == null)
        {
            throw new NullPointerException("refreshToken can't be null");
        }

        Map<String, Object> payload = this.jwtTokenVerifyService.hasValidJwsTokenSignature(refreshToken);

        if (!"refresh".equals(payload.get(TOKEN_PAYLOAD_FIELDS.type.name())))
        {
            throw new SecurityException("invalid token type -> maybe a access_token and not a refresh_token");
        }

        int refreshTokenId = (Integer) payload.get(TOKEN_PAYLOAD_FIELDS.refreshTokenId.name());

        RT tokenFromDatabase = this.findRefreshToken(refreshTokenId);

        if (tokenFromDatabase == null)
        {
            throw new IllegalStateException("a valid refresh token couldn't be found in database");
        }

        if (tokenFromDatabase.isRevoked())
        {
            //TODO notify abuse-system
            System.err.println("reuse of revoked refresh_token");
            throw new SecurityException("invalid refresh_token");
        }

        return tokenFromDatabase;
    }
}
