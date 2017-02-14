/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.resource.impl.timetable.v1;

import de.hskl.campusboard.server.resource.timetable.v1.IRoom;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
@Entity
@Table(name=Room.TABLE_NAME)
public class Room implements IRoom, Serializable
{
    protected static final String TABLE_NAME = "room";
        
    @Id
    private long room_id;
    private String room_name; 

    public String getRoom_id()
    {
        return String.valueOf(room_id);
    }

    public void setRoom_id(String room_id)
    {
        this.room_id = Long.valueOf(room_id);
    }

    public String getRoom_name()
    {
        return room_name;
    }

    public void setRoom_name(String room_name)
    {
        this.room_name = room_name;
    }
}
