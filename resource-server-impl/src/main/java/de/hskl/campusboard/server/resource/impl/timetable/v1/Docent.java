/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.resource.impl.timetable.v1;

import de.hskl.campusboard.server.resource.timetable.v1.IDocent;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
@Entity
@Table(name=Docent.TABLE_NAME)
public class Docent implements IDocent, Serializable
{
    protected static final String TABLE_NAME = "docent";
    
    @Id
    private long docent_id;
    private String name_with_title;

    @Override
    public String getDocent_id()
    {
        return String.valueOf(this.docent_id);
    }

    @Override
    public void setDocent_id(String id)
    {
        this.docent_id = Long.valueOf(id);
    }

    @Override
    public String getName_with_title()
    {
        return this.name_with_title;
    }

    @Override
    public void setName_with_title(String name_with_title)
    {
        this.name_with_title = name_with_title;
    }
}
