/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.resource.timetable.v1;

import java.time.LocalDate;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public interface ITimetableEntry<D extends IDocent, R extends IRoom>
{
    public String getName();
    public void setName(String name);
    
    public String getShort_name();
    public void setShort_name(String shortName);
    
    public int getDay_of_the_week();
    public void setDay_of_the_week(int day_of_the_week);
    
    public LocalDate getStart_date();
    public void setStart_date(LocalDate startDate);
    
    public LocalDate getEnd_date(); 
    public void setEnd_date(LocalDate endDate);
    
    public int getRotation();   
    public void setRotation(int rotation);   
    
    public String getGroup_name(); 
    public void setGroup_name(String groupName);
    
    public String getNote();
    public void setNote(String note);
    
    public String getType();
    public void setType(String type);

    public D getDocent();
    public void setDocent(D docent);
    
    public R getRoom();
    public void setRoom(R room);
}
