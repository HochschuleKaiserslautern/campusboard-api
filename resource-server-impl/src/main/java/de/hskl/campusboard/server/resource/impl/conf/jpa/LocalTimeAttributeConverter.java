/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.resource.impl.conf.jpa;

import java.sql.Time;
import java.time.LocalTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
@Converter(autoApply = true)
public class LocalTimeAttributeConverter implements AttributeConverter<LocalTime, Time>
{
    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
    	return (localTime == null ? null : Time.valueOf(localTime));
    }

    @Override
    public LocalTime convertToEntityAttribute(Time sqlTime) {
        
        System.out.println("FROM-DB: " + sqlTime.toString());
        
    	return (sqlTime == null ? null : sqlTime.toLocalTime());
    }
}
