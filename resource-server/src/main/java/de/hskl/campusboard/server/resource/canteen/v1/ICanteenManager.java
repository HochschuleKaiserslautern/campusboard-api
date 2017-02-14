/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.canteen.v1;

import java.util.List;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface ICanteenManager<C extends ICanteen, D extends IDish>
{
    public List<C> getAllCanteens();

    public List<D> getDishesByCanteenId(String canteenId);
}
