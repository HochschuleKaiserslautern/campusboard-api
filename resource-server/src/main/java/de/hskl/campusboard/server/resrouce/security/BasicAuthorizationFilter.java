/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resrouce.security;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;

import de.hskl.campusboard.server.exception.OAuthErrorBuilder;
import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;
import de.hskl.campusboard.server.security.JwtTokenVerifyService;
import de.hskl.campusboard.server.security.JwtTokenVerifyService.TOKEN_PAYLOAD_FIELDS;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Provider
@BasisAuthorization
@Stateless
public class BasicAuthorizationFilter implements ContainerRequestFilter
{
	@Context
	private HttpServletRequest httpRequest;
	
	@Inject
	private JwtTokenVerifyService jwTokenVerifyService; 
	
	@Inject
	private Logger log;
	
	@Inject
	private OAuthErrorBuilder oAuthErrorBuilder;
	
	@Override
	public void filter(ContainerRequestContext requestContext)
	{
		log.trace("filter()");

		String accessTokenFromRequest = null;
		
		try
		{
			OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(
					httpRequest, ParameterStyle.QUERY);

			accessTokenFromRequest = oauthRequest.getAccessToken();
		}
		catch (OAuthSystemException | OAuthProblemException e)
		{
			log.error(e);
			requestContext.abortWith(oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.invalid_request, "this request needs authentication"));
			return;
		}
		
		try
		{
			Map<String, Object> tokenValues = jwTokenVerifyService.hasValidJwsTokenSignature(accessTokenFromRequest);
			requestContext.getHeaders().add("username", (String)tokenValues.get(TOKEN_PAYLOAD_FIELDS.username.name()));
		}
		catch (SecurityException e)
		{
			log.error(e);
			requestContext.abortWith(oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.invalid_grant, "invalid access_token"));
			return;
		}
	}
}
