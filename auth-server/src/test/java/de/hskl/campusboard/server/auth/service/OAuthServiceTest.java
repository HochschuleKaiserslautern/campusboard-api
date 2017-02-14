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

import de.hskl.campusboard.server.auth.entity.IRefreshToken;
import de.hskl.campusboard.server.auth.security.JwtTokenSignService;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder;
import de.hskl.campusboard.server.security.JwtTokenVerifyService;
import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
public class OAuthServiceTest
{    
    OAuthService targetUnderTest;
    
    @Before
    public void init()
    {
        this.targetUnderTest = mock(OAuthService.class, Mockito.CALLS_REAL_METHODS);
        
        this.targetUnderTest.log = mock(Logger.class);
        this.targetUnderTest.jwtTokenService = mock(JwtTokenSignService.class);
        this.targetUnderTest.jwtTokenVerifyService = mock(JwtTokenVerifyService.class);
        this.targetUnderTest.oAuthErrorBuilder = mock(OAuthErrorBuilder.class);
        
        IRefreshToken newInstance = mock(IRefreshToken.class);
        when(this.targetUnderTest.getNewInstanceOfRefreshToken()).thenReturn(newInstance);
        when(this.targetUnderTest.insertNewRefreshToken(newInstance)).thenReturn(newInstance);
    }
    
    /**
     * Test of getAccessTokenByPassword method, of class OAuthService.
     */
    @Test
    public void getAccessTokenByPasswordTest() throws OAuthSystemException
    {
        String username = "test_username";
        String password = "test_password";
        String clientId = "test_client_id";
        
        String signedAccesToken = "sigend_token";        
        
        when(this.targetUnderTest.validateCredentials(username, password)).thenReturn(true);
        when(this.targetUnderTest.jwtTokenService.createSignedAccessToken(username, clientId)).thenReturn(signedAccesToken);
        
        System.out.println(this.targetUnderTest.getAccessTokenByPassword(username, password, clientId).getEntity());
        
    }     
}
