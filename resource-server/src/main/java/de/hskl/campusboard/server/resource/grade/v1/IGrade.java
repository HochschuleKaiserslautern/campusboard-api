/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.grade.v1;

import java.time.LocalDate;
import de.hskl.campusboard.server.resource.timetable.v1.IDocent;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface IGrade
{
    public String getExam_id();
    public String getExam_title();
    public String getType();
    public String getCreditpoints();
    public String getSemester();
    public LocalDate getDate();
    public double getGrade();
    public String getState();
    public IDocent getDocent();
    public String getNote();
    public String getCourse_id();
}
