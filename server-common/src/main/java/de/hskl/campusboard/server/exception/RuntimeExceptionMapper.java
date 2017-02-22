/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.exception;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException>{
	
	@Inject
	private Logger log;
	
	@Inject
	private OAuthErrorBuilder oAuthErrorBuilder;
	
	@Override
	public Response toResponse(RuntimeException e)
	{
		log.error("{}", () -> "ERROR - Class: " + e.getClass().getSimpleName() + ", Message: " + e.getMessage());
		e.printStackTrace();
		
		Throwable cause = e.getCause();
		
		if(cause == null)
			return getUnknowErrorResponse(e);
		
		return getUnknowErrorResponse(cause);
	}	
	
	protected Response getUnknowErrorResponse(Throwable e)
	{
		log.error("{}", () -> "UNKOWN-ERROR - Class: " + e.getClass().getSimpleName() + ", Message: " + e.getMessage());
		
		return oAuthErrorBuilder.createOauthErrorResponse(ERROR_TYPES.internal_error, 
				"we are sorry. there was an internal error. if this problem is permanent -> pls contact us");
	}
}