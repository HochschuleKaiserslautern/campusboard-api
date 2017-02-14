/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.conf;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
@Singleton
public class PropertieLoader 
{
    @Inject
    Instance<Map<String, String>> initialValues;
    
    private Map<String, String> store;

    @PostConstruct
    public void init() {
        this.store = new HashMap<>();
        for (Map<String, String> initial : initialValues) {
            this.store.putAll(initial);
        }
    }

    @Produces
    public String getString(InjectionPoint ip) {
        String key = ip.getMember().getName();
        String fieldName = computeKeyName(ip.getAnnotated(), key);
        return this.store.get(fieldName);
    }
    
    @Produces
    public int getInteger(InjectionPoint ip) {
        String stringValue = getString(ip);
        if (stringValue == null) {
            return 0;
        }
        return Integer.parseInt(stringValue);
    }

    private String computeKeyName(Annotated annotated, String key) {
        PropertyName annotation = annotated.getAnnotation(PropertyName.class);
        return annotation == null ? key : annotation.value();

    }
}
