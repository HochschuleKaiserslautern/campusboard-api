/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.grade.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface ICourse
{
    public String getCourse_id();
    public void setCourse_id(String course_id);
    
    public String getCoursename();
    public void setCoursename(String coursename);
}
