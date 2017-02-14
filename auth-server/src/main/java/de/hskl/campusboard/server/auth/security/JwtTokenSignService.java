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

package de.hskl.campusboard.server.auth.security;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.pem.PemReader;

import de.hskl.campusboard.server.auth.entity.IRefreshToken;
import de.hskl.campusboard.server.conf.PropertyName;
import de.hskl.campusboard.server.security.JwtTokenVerifyService.TOKEN_PAYLOAD_FIELDS;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Singleton
public class JwtTokenSignService
{
	private PrivateKey privateKey = null;
	
	@Inject
	private Logger log;
        
        @Inject
        @PropertyName("JWT_PRIVATE_KEY")
        private String jwtPrivateKeyPath;
        
        @Inject
        @PropertyName("ACCESS_TOKEN_DEFAULT_EXPIRES")
        private int accessTokenDefaultExpires;
        
        @Inject
        @PropertyName("DEFAULT_TOKEN_ISSUER")
        private String defaultTokenIssuer;
		
	@PostConstruct
	protected void loadKeys()
	{		
		log.trace("loadKeys()");
		
		try
		{
			privateKey = PemReader.readPrivateKey(this.jwtPrivateKeyPath);
		}
		catch (NoSuchProviderException | NoSuchAlgorithmException
				| InvalidKeySpecException | IOException e)
		{
			log.error(e);
		}
	}
	
	public String createSignedAccessToken(String username, String clientId)
	{
		log.trace("createSignedAccessToken(): {}", () -> "username: " + username + ", clientId: " + clientId);
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(TOKEN_PAYLOAD_FIELDS.clientid.name(), clientId);
		claims.put(TOKEN_PAYLOAD_FIELDS.username.name(), username);
		claims.put(TOKEN_PAYLOAD_FIELDS.type.name(), "access");
		
		return createJwtToken(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), claims);
	}	

	public String createSignedRefreshToken(IRefreshToken refreshToken)
	{
		log.trace("createSignedRefreshToken(): {}", () -> "refreshToken: " + refreshToken);
		
		Map<String, Object> claims = new HashMap<>();
		claims.put(TOKEN_PAYLOAD_FIELDS.clientid.name(), refreshToken.getClientId());
		claims.put(TOKEN_PAYLOAD_FIELDS.username.name(), refreshToken.getUsername());
		claims.put(TOKEN_PAYLOAD_FIELDS.type.name(), "refresh");
		claims.put(TOKEN_PAYLOAD_FIELDS.refreshTokenId.name(), refreshToken.getTokenId());
		
		return createJwtToken(refreshToken.getIssuedDateTime().toEpochSecond(ZoneOffset.UTC), claims);
	}		
	
	private String createJwtToken(long issuedAtTime, Map<String, Object> claims)
	{
		log.trace("createJwtToken(): {}", () -> "issuedAtTime: " + issuedAtTime);
		
		if(privateKey == null)
			throw new IllegalStateException("The privateKey has not been loaded -> abort jwt-signing");
		
		final long exp = issuedAtTime + this.accessTokenDefaultExpires; 

		final JWTSigner signer = new JWTSigner(this.privateKey);
		
		claims.put(TOKEN_PAYLOAD_FIELDS.iss.name(), this.defaultTokenIssuer);
		claims.put(TOKEN_PAYLOAD_FIELDS.exp.name(), exp);
		claims.put(TOKEN_PAYLOAD_FIELDS.iat.name(), issuedAtTime);
				
		final JWTSigner.Options options = new JWTSigner.Options();
		options.setAlgorithm(Algorithm.RS256);
		
		final String jwt = signer.sign(claims, options);
		
		return jwt;
	}
}
