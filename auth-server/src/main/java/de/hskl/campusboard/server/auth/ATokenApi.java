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



import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import de.hskl.campusboard.server.auth.entity.IClient;
import de.hskl.campusboard.server.auth.entity.IClientSecret;
import de.hskl.campusboard.server.auth.entity.IRefreshToken;
import de.hskl.campusboard.server.auth.service.OAuthService;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;


/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public abstract class ATokenApi<CS extends IClientSecret,C extends IClient<CS>,RT extends IRefreshToken>
{	
	@Context
	protected HttpServletRequest httpRequest;	
	
	@Inject
	protected Logger log;
	
	@Inject
            protected OAuthErrorBuilder oAuthErrorBuilder;
	
	protected abstract OAuthService<CS,C,RT> getOAuthServiceImpl();	
		
	@POST
	public Response getAccesToken() throws OAuthSystemException, OAuthProblemException
	{
		log.trace("getAccessToken()");
		
		OAuthTokenRequest oauthRequest = new OAuthTokenRequest(httpRequest);
		
		GrantType grantType = getOAuthServiceImpl().getValidateGrantType(oauthRequest.getGrantType());
		
           
		if(grantType == null)
			return oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.unsupported_grant_type, "valid grant_types are password, refresh_token");
                
		if(!getOAuthServiceImpl().isClientValid(oauthRequest.getClientId(), oauthRequest.getClientSecret()))
			return oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.unauthorized_client, "invalid client_id / client_secrect combination");                

		switch(grantType)
		{
			case PASSWORD: 
				return getOAuthServiceImpl().getAccessTokenByPassword(oauthRequest.getUsername(), oauthRequest.getPassword(), oauthRequest.getClientId());
				
			case REFRESH_TOKEN: 
				return getOAuthServiceImpl().getAccessTokenByRefreshToken(oauthRequest.getRefreshToken(), oauthRequest.getClientId());
				
			default:
				throw new IllegalStateException("no valid grantType");
		}
	}	
}
