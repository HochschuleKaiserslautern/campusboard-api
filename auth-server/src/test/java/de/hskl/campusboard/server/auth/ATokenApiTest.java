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

package de.hskl.campusboard.server.auth;

import de.hskl.campusboard.server.auth.service.OAuthService;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class ATokenApiTest
{

    private OAuthService oauthService;
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String grantType;

    private ATokenApi targetUnderTest;

    @Before
    public void prepare()
    {
        this.oauthService = mock(OAuthService.class);
        this.username = "test_username";
        this.password = "test_password";
        this.clientId = "test_client_id";
        this.clientSecret = "test_client_secret";
        this.grantType = "password";

        targetUnderTest = new ATokenApi()
        {
            @Override
            protected OAuthService<?, ?, ?> getOAuthServiceImpl()
            {
                return oauthService;
            }
        };

        targetUnderTest.log = mock(Logger.class);
        targetUnderTest.oAuthErrorBuilder = mock(OAuthErrorBuilder.class);

    }

    private void prepareValidRequest()
    {
        //prepare valid request
        targetUnderTest.httpRequest = mock(HttpServletRequest.class);
        when(targetUnderTest.httpRequest.getParameter("grant_type")).thenReturn(grantType);
        when(targetUnderTest.httpRequest.getMethod()).thenReturn(OAuth.HttpMethod.POST);
        when(targetUnderTest.httpRequest.getContentType()).thenReturn(OAuth.ContentType.URL_ENCODED);
        when(targetUnderTest.httpRequest.getParameter("username")).thenReturn(username);
        when(targetUnderTest.httpRequest.getParameter("password")).thenReturn(password);
        when(targetUnderTest.httpRequest.getParameter("client_id")).thenReturn(clientId);
        when(targetUnderTest.httpRequest.getParameter("client_secret")).thenReturn(clientSecret);
    }

    @Test
    public void valicdAccesTokenRequestTest() throws Exception
    {
        final String expectedStringInResponse = "VALID_RESPONSE";

        prepareValidRequest();

        when(oauthService.getValidateGrantType(grantType)).thenReturn(GrantType.PASSWORD);
        when(oauthService.isClientValid(clientId, clientSecret)).thenReturn(true);
        when(oauthService.getAccessTokenByPassword(username, password, clientId)).thenReturn(Response.ok().entity(expectedStringInResponse).build());

        Response actualResponse = targetUnderTest.getAccesToken();

        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatus(), is(200));
        assertThat(actualResponse.getEntity(), is(expectedStringInResponse));
    }

    @Test
    public void invalidGrantTypeTest() throws Exception
    {
        final String expectedStringInResponse = "EXPECTED_ERROR_RETURNED";

        prepareValidRequest();

        when(oauthService.getValidateGrantType(grantType)).thenReturn(null);
        when(targetUnderTest.oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.unsupported_grant_type,
                "valid grant_types are password, refresh_token")).
                thenReturn(Response.status(Response.Status.BAD_REQUEST).entity(expectedStringInResponse).build());

        Response actualResponse = targetUnderTest.getAccesToken();

        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatus(), is(400));
        assertThat(actualResponse.getEntity(), is(expectedStringInResponse));
    }

    @Test
    public void invalidClientTest() throws Exception
    {
        final String expectedStringInResponse = "EXPECTED_ERROR_RETURNED";

        prepareValidRequest();

        when(oauthService.getValidateGrantType(grantType)).thenReturn(GrantType.PASSWORD);
        when(oauthService.isClientValid(clientId, clientSecret)).thenReturn(false);
        when(targetUnderTest.oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.unauthorized_client,
                "invalid client_id / client_secrect combination")).
                thenReturn(Response.status(Response.Status.BAD_REQUEST).entity(expectedStringInResponse).build());

        Response actualResponse = targetUnderTest.getAccesToken();

        assertNotNull(actualResponse);
        assertThat(actualResponse.getStatus(), is(400));
        assertThat(actualResponse.getEntity(), is(expectedStringInResponse));
    }
}
