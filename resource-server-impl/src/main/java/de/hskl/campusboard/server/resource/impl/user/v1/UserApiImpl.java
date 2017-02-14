/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.user.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hskl.campusboard.server.resource.user.v1.UserApi;
import de.hskl.campusboard.server.resource.user.v1.UserResourceService;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
@Path("user/v1")
@Produces({MediaType.APPLICATION_JSON})
public class UserApiImpl extends UserApi<ResourceUser>
{
	@Inject
	private UserResourceService<ResourceUser> userResourceService;

	@Override
	protected UserResourceService<ResourceUser> getUserResourceService()
	{
		return userResourceService;
	}
	
    
}
