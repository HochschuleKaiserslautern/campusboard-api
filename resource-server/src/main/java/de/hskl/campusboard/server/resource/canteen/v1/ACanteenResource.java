/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.canteen.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resrouce.security.BasisAuthorization;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public abstract class ACanteenResource<C extends ICanteen, D extends IDish>
{
    @Inject
    private Logger log;
    
    protected abstract ICanteenManager<C,D> getCanteenManager();
    
    @GET
    @BasisAuthorization //TODO maybe not needed, could be public
    public List<C> getAvailableCanteens()
    {
        log.trace("getAvailbaleCanteens()");
        return this.getCanteenManager().getAllCanteens();
    }

    @GET
    @Path("{id}")
    @BasisAuthorization //TODO maybe not needed, could be public
    public List<D> getDishesByCanteen(@PathParam("id") String id)
    {
        log.trace("getCanteenMenu() {}", () -> "id: " + id);
        return this.getCanteenManager().getDishesByCanteenId(id);
    }
}
