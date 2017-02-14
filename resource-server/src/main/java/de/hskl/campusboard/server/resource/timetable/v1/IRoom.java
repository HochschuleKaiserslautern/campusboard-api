/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.timetable.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface IRoom
{
    public String getRoom_id();
    public void setRoom_id(String id);
    
    public String getRoom_name();
    public void setRoom_name(String name);
}
