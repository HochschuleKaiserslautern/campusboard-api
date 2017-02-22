    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.conf;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.inject.Produces;

/**
 *
 * @author julian.neuhaus@brain-activit.com
 */
public class PropertyInitializer 
{
    @Produces
    public Map<String, String> getInitialConfiguration() {
    	Map<String, String> properties = new HashMap<>();
        properties.put("JWT_PRIVATE_KEY", this.getClass().getResource("/private_key.pem").getFile());
        properties.put("JWT_PUBLIC_KEY", this.getClass().getResource("/public_key.pem").getFile());
        properties.put("ACCESS_TOKEN_DEFAULT_EXPIRES", "3600");
        properties.put("DEFAULT_TOKEN_ISSUER", "campusboard_auth_server");
        
        return properties;
    }
}
