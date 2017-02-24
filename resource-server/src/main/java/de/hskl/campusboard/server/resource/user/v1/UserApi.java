/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.user.v1;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resrouce.security.BasisAuthorization;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @param <U>
 * @since 1.0.0
 */
public abstract class UserApi<U extends IUser>
{
    @Inject
    private Logger log;

    protected abstract UserResourceService<U> getUserResourceService();

    @GET
    @BasisAuthorization
    public Response getUserData(@HeaderParam(value = "username") String username)
    {
        log.trace("getUserData(): {}", () -> "username: " + username);
        U user = this.getUserResourceService().getUserByUsername(username);
        
        if(user == null)
        {
            JSONObject userNotFoundMessage = new JSONObject();
            userNotFoundMessage.put("error_description", "the user with the username: '" + username + "' could not be found");
            userNotFoundMessage.put("error", "not_found");            
            return Response.status(Response.Status.NOT_FOUND).entity(userNotFoundMessage.toString()).build();
        }
        
        return Response.ok(user).build();
    }
}
