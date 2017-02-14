/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.timetable.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hskl.campusboard.server.resource.timetable.v1.ATimetableResource;
import de.hskl.campusboard.server.resource.timetable.v1.ITimetableManager;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Stateless
@Path("v1/timetable")
@Produces(
{ MediaType.APPLICATION_JSON })
public class TimetableResource extends ATimetableResource<Docent,Room, TimetableEntry>
{
    @Inject
    private ITimetableManager<Docent,Room, TimetableEntry> timetableManager;

    @Override
    protected ITimetableManager<Docent, Room, TimetableEntry> getTimetableManager()
    {
        return this.timetableManager;
    }
}
