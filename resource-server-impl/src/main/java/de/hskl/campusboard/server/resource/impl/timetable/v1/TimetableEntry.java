/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.resource.impl.timetable.v1;

import de.hskl.campusboard.server.resource.timetable.v1.ITimetableEntry;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
@Entity
@Table(name=TimetableEntry.TABLE_NAME)
@NamedQueries(value =
{ @NamedQuery(name=TimetableEntry.GET_CURRENT_ENTRIES_BY_USER, query="SELECT t FROM TimetableEntry t") })
public class TimetableEntry implements ITimetableEntry<Docent,Room>, Serializable
{
    protected static final String TABLE_NAME = "timetable_entry";
    private static final String QUERY_PREFIX = "TIMETABLE_ENTRY_QUERY_";
    
    public static final String GET_CURRENT_ENTRIES_BY_USER = QUERY_PREFIX + "GET_CURRENT_ENTRIES_BY_USER";
    
    @Id
    private long id;
    
    private String name;
    private String short_name;
    private int day_of_the_week;
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalDate start_date;
    private LocalDate end_date;
    private int rotation;
    private String group_name;
    private String note;
    private String type;
    
    @ManyToOne
    private Docent docent;
    
    @ManyToOne
    private Room room;  

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getShort_name()
    {
        return short_name;
    }

    public void setShort_name(String short_name)
    {
        this.short_name = short_name;
    }

    public int getDay_of_the_week()
    {
        return day_of_the_week;
    }

    public void setDay_of_the_week(int day_of_the_week)
    {
        this.day_of_the_week = day_of_the_week;
    }

    public LocalTime getStart_time()
    {
        return start_time;
    }

    public void setStart_time(LocalTime start_time)
    {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time()
    {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time)
    {
        this.end_time = end_time;
    }

    public LocalDate getStart_date()
    {
        return start_date;
    }

    public void setStart_date(LocalDate start_date)
    {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date)
    {
        this.end_date = end_date;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    public String getGroup_name()
    {
        return group_name;
    }

    public void setGroup_name(String group_name)
    {
        this.group_name = group_name;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Docent getDocent()
    {
        return docent;
    }

    public void setDocent(Docent docent)
    {
        this.docent = docent;
    }

    public Room getRoom()
    {
        return room;
    }

    public void setRoom(Room room)
    {
        this.room = room;
    }
}
