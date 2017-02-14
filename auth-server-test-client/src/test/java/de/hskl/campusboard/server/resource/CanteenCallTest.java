/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource;

import static org.junit.Assert.assertTrue;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import de.hskl.campusboard.server.ApiTest;
import de.hskl.campusboard.server.TestConstants;
import de.hskl.campusboard.server.Util;
import java.time.LocalTime;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class CanteenCallTest extends ApiTest
{
    @Test
    public void canteeneAuthMissing() throws OAuthSystemException, OAuthProblemException
    {
        System.out.println(LocalTime.parse("13:15:15").toString());
        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_CANTEEN_ENDPOINT_V1)
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 400);
        assertTrue(resourceResponse.getBody() != null);

        JSONObject errorMessage = new JSONObject(resourceResponse.getBody());
        assertTrue(errorMessage != null);
        assertTrue("this request needs authentication".equals(errorMessage.get("error_description")));
        assertTrue("invalid_request".equals(errorMessage.get("error")));
    }
    
    @Test
    public void getAllCanteens() throws OAuthSystemException, OAuthProblemException
    {        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_CANTEEN_ENDPOINT_V1)
                        .setAccessToken(Util.getValidAccessToken())
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 200);
        assertTrue(resourceResponse.getBody() != null);
        
        JSONArray jsonArray = new JSONArray(resourceResponse.getBody());
        
        assertTrue(jsonArray != null);
        assertTrue(jsonArray.length() == 2);        
        
        for(int i =0; i<jsonArray.length(); i++)
        {
            JSONObject canteenObject = jsonArray.getJSONObject(i);
            assertTrue(canteenObject != null);
            
            String canteenId = canteenObject.getString("id");
            String canteenName = canteenObject.getString("name");
            
            assertTrue(canteenId != null);
            assertTrue(canteenName != null);
            
            if(i == 0)
            {
                assertEquals(canteenId,"1");
                assertEquals(canteenName,"Canteen_One");
            }
            else if(i == 1)
            {
                assertEquals(canteenId,"2");
                assertEquals(canteenName,"Canteen_Two");
            }            
        }
    }
    
    @Test
    public void getAllDishes() throws OAuthSystemException, OAuthProblemException
    {        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_CANTEEN_ENDPOINT_V1)
                        .setAccessToken(Util.getValidAccessToken())
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 200);
        assertTrue(resourceResponse.getBody() != null);
        
        JSONArray jsonArray = new JSONArray(resourceResponse.getBody());
        
        assertTrue(jsonArray != null);
        assertTrue(jsonArray.length() == 2);        
        
        for(int i =0; i<jsonArray.length(); i++)
        {
            JSONObject canteenObject = jsonArray.getJSONObject(i);
            assertTrue(canteenObject != null);
            
            String canteenId = canteenObject.getString("id");
            String canteenName = canteenObject.getString("name");
            
            assertTrue(canteenId != null);
            assertTrue(canteenName != null);
            
            if(i == 0)
            {
                assertEquals(canteenId,"1");
                assertEquals(canteenName,"Canteen_One");
            }
            else if(i == 1)
            {
                assertEquals(canteenId,"2");
                assertEquals(canteenName,"Canteen_Two");
            }            
        }
    }
}
