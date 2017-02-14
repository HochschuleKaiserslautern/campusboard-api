/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.canteen.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hskl.campusboard.server.resource.canteen.v1.ACanteenResource;
import de.hskl.campusboard.server.resource.canteen.v1.ICanteenManager;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Stateless
@Path("v1/canteen")
@Produces(
{ MediaType.APPLICATION_JSON })
public class CanteenResource extends ACanteenResource<Canteen, Dish>
{
    @Inject
    private ICanteenManager<Canteen, Dish> canteenManager;
    
    @Override
    protected ICanteenManager<Canteen, Dish> getCanteenManager()
    {
        return this.canteenManager;
    }   
}
