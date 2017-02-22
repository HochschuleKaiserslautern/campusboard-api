/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resrouce.security;

import com.auth0.jwt.JWTVerifyException;
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
import java.lang.reflect.Method;
import javax.ws.rs.container.ResourceInfo;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@BasisAuthorization
@Provider
@Stateless
public class BasicAuthorizationFilter implements ContainerRequestFilter
{

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private JwtTokenVerifyService jwTokenVerifyService;

    @Inject
    private Logger log;

    @Inject
    private OAuthErrorBuilder oAuthErrorBuilder;

    @Override
    public void filter(ContainerRequestContext requestContext)
    {
        if(handleMethod())
        {
            doFilter(requestContext);                    
        }       
    }
    
    private void doFilter(ContainerRequestContext requestContext)
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
            requestContext.getHeaders().add("username", (String) tokenValues.get(TOKEN_PAYLOAD_FIELDS.username.name()));
        }
        catch (JWTVerifyException e)
        {
            log.error(e);
            requestContext.abortWith(oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.invalid_grant, "invalid access_token"));
            return;
        }
    }
    
    private boolean handleMethod()
    {
        Method method = resourceInfo.getResourceMethod();

        if (method != null) {
           return  method.isAnnotationPresent(BasisAuthorization.class);
        }
        else
        {
            throw new IllegalStateException("Method to handle was null -> this should not happen");
        }
    }
}
