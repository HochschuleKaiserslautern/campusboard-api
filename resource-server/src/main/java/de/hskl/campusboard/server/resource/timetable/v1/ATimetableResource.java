/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.timetable.v1;

import de.hskl.campusboard.server.resource.canteen.v1.*;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resrouce.security.BasisAuthorization;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @param <D> Docent implementation used by implementation of ITimetableEntry
 * @param <R> Room implementation used by implementation of ITimetableEntry
 * @param <E> Implementation of ITimetableEntry used by this resource
 * @sine 1.0.0
 */
public abstract class ATimetableResource<D extends IDocent, R extends IRoom, E extends ITimetableEntry<D,R>>
{
    @Inject
    private Logger log;
    
    protected abstract ITimetableManager<D,R,E> getTimetableManager();
    
    @GET
    @BasisAuthorization 
    public List<E> getCurrentTimetableEntries()
    {
        log.trace("getCurrentTimetableEntries()");
        return this.getTimetableManager().getCurrentTimetable();
    }
}
