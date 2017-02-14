/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.timetable.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface IDocent
{
    public String getDocent_id();
    public void setDocent_id(String id);
    
    public String getName_with_title();
    public void setName_with_title(String name_with_title);
}
