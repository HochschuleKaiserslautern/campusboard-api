/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.user.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resrouce.security.BasisAuthorization;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public abstract class UserApi<U extends IUser>
{
	@Inject
	private Logger log;
	
	protected abstract UserResourceService<U> getUserResourceService();
	
    @GET
    @BasisAuthorization
    public U getUserData(@HeaderParam(value="username") String username)
    {
		log.trace("getUserData(): {}", () -> "username: " + username);
    	return this.getUserResourceService().getUserByUsername(username);
    }
}
