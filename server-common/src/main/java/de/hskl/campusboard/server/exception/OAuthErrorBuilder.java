/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.exception;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
public class OAuthErrorBuilder
{
	public enum ERROR_TYPES
	{
		invalid_request, invalid_client, invalid_grant, unauthorized_client, unsupported_grant_type, internal_error
	}
	
	public Response createOauthErrorResponse(ERROR_TYPES errorType)
	{
		return createOauthErrorResponse(errorType, null, null);
	}
	
	public Response createOauthErrorResponse(ERROR_TYPES errorType, String errorDescription)
	{
		return createOauthErrorResponse(errorType, errorDescription, null);
	}
	
	public Response createOauthErrorResponse(ERROR_TYPES errorType, String errorDescription, String errorUri)
	{
		if(errorType == null)
			throw new NullPointerException("error_type can't be null");
		
		ErrorResponse newError = new ErrorResponse();
		newError.setError(errorType);
		newError.setError_description(errorDescription);
		newError.setError_uri(errorUri);
		
		Status status = errorType == ERROR_TYPES.internal_error ? Status.INTERNAL_SERVER_ERROR : Status.BAD_REQUEST;
		
		return Response.status(status).entity(newError).build();
	}
}
