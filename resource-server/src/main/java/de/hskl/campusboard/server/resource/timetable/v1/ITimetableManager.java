/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.timetable.v1;

import java.util.List;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @param <D> Docent implementation used by implementation of ITimetableEntry
 * @param <R> Room implementation used by implementation of ITimetableEntry
 * @param <E> Implementation of ITimetableEntry used by this manager
 * @sine 1.0.0
 */
public interface ITimetableManager<D extends IDocent, R extends IRoom, E extends ITimetableEntry<D,R>>
{
    public List<E> getCurrentTimetable();
}
