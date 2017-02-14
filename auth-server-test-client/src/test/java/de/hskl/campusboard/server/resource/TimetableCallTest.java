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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class TimetableCallTest extends ApiTest
{
    @Test
    public void getTimetable() throws OAuthSystemException, OAuthProblemException
    {        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_TIMETABLE_ENDPOINT_V1)
                        .setAccessToken(Util.getValidAccessToken())
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 200);
        assertTrue(resourceResponse.getBody() != null);
        
        JSONArray jsonArray = new JSONArray(resourceResponse.getBody());
        
        assertTrue(jsonArray.length() == 5);  
        
        for(int i =0; i<jsonArray.length(); i++)
        {
            JSONObject entryObject = jsonArray.getJSONObject(i);
            assertTrue(entryObject != null);
            
            String shortName = entryObject.getString("short_name");
            
            assertNotNull(shortName);
            assertThat(shortName, is("A"));
        }
    }
}
