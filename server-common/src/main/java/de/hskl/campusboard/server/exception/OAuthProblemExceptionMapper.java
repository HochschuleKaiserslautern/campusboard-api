/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.exception;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.oltu.oauth2.common.exception.OAuthProblemException;

import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Provider
public class OAuthProblemExceptionMapper implements ExceptionMapper<OAuthProblemException>
{
	@Inject
	private OAuthErrorBuilder oAuthErrorBuilder;
	
	@Override
	public Response toResponse(OAuthProblemException exception)
	{
		return oAuthErrorBuilder.createOauthErrorResponse(
				ERROR_TYPES.invalid_request, exception.getMessage());
	}
}
