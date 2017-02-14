/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.timetable.v1;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resource.timetable.v1.ITimetableManager;
import javax.persistence.TypedQuery;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Stateless
public class TimetableManager implements ITimetableManager<Docent, Room, TimetableEntry>
{
    @Inject
    private Logger log;
    
    @PersistenceContext(unitName="personal")
    private EntityManager em;

    @Override
    public List<TimetableEntry> getCurrentTimetable()
    {
        log.trace("getCurrentTimetable()");
        
        TypedQuery<TimetableEntry> timeTableEntrieQuery = 
                this.em.createNamedQuery(TimetableEntry.GET_CURRENT_ENTRIES_BY_USER, TimetableEntry.class);
        
        List<TimetableEntry> entries = timeTableEntrieQuery.getResultList();
        
        for(TimetableEntry e : entries)
            System.out.println("E: " + e.getShort_name() + " - TIME: " + e.getEnd_time());
        
        log.trace("getCurrentTimetable() {}", () -> "found " + entries.size() + " entries");
        return entries;
    }
}
