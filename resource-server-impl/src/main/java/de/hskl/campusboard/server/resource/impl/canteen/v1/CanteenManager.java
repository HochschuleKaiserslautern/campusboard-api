/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.canteen.v1;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resource.canteen.v1.ICanteenManager;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Stateless
public class CanteenManager implements ICanteenManager<Canteen, Dish>
{
    @Inject
    private Logger log;
    
    @PersistenceContext(unitName="personal")
    private EntityManager em;
    
    @Override
    public List<Canteen> getAllCanteens()
    {
        log.trace("getAllCanteens()");
        
        TypedQuery<Canteen> query = em.createNamedQuery(Canteen.GET_ALL, Canteen.class);
        
        List<Canteen> canteens = query.getResultList();
        
        log.trace("getAllCanteens() {}", () -> "found " + canteens.size() + " canteens");
        return canteens;
    }

    @Override
    public List<Dish> getDishesByCanteenId(String canteenId)
    {
        log.trace("getDishesByCanteenId()");
        
        TypedQuery<Dish> query = em.createNamedQuery(Dish.GET_BY_CANTEEN, Dish.class);
        query.setParameter("canteenId", canteenId);
        
        List<Dish> dishes = query.getResultList();
        
        log.trace("getDishesByCanteenId() {}", () -> "found " + dishes.size() + " dishes");
        return dishes;
    }
}
