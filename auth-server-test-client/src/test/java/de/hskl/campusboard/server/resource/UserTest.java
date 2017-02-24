/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.hskl.campusboard.server.resource;

import de.hskl.campusboard.server.ApiTest;
import de.hskl.campusboard.server.PrepareDatabase;
import de.hskl.campusboard.server.TestConstants;
import de.hskl.campusboard.server.Util;
import java.sql.SQLException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.json.JSONObject;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author neuhaus.julian@gmail.com
 */
public class UserTest extends ApiTest
{
    @Test
    public void userAuthMissing() throws OAuthSystemException, OAuthProblemException
    {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_USER_ENDPOINT_V1)
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 400);
        assertTrue(resourceResponse.getBody() != null);

        JSONObject errorMessage = new JSONObject(resourceResponse.getBody());
        assertTrue("this request needs authentication".equals(errorMessage.get("error_description")));
        assertTrue("invalid_request".equals(errorMessage.get("error")));
    }
    
    @Test
    public void getUserData() throws OAuthSystemException, OAuthProblemException
    {        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                TestConstants.TEST_RESOURCE_USER_ENDPOINT_V1)
                        .setAccessToken(Util.getValidAccessToken())
                        .buildQueryMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(
                bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        assertTrue(resourceResponse.getResponseCode() == 200);
        assertTrue(resourceResponse.getBody() != null);
        
        JSONObject userJson = new JSONObject(resourceResponse.getBody());        
        
        assertThat(userJson.length(), is(13));
        
        String anrede = userJson.getString("anrede");
        String email = userJson.getString("email");
        String faxnr = userJson.getString("faxnr");
        String telnr = userJson.getString("telnr");
        String fotourl = userJson.getString("fotourl");        
        String homepage = userJson.getString("homepage");
        boolean isMitarbeiter = userJson.getBoolean("mitarbeiter");
        boolean isStudent = userJson.getBoolean("student");
        String firstname = userJson.getString("vorname");
        String lastname = userJson.getString("nachname");
        String roomNumber = userJson.getString("raum");
        String title = userJson.getString("title");
        String location = userJson.getString("standort");
 
        assertThat(anrede, is("Herr"));
        assertThat(email, is("test@test.de"));
        assertThat(faxnr, is("1234"));
        assertThat(telnr, is("4321"));
        assertThat(fotourl, is("foto.de"));
        assertThat(homepage, is("homepage.de"));
        assertThat(isMitarbeiter, is(true));
        assertThat(isStudent, is(false));
        assertThat(firstname, is("TEST_VORNAME"));
        assertThat(lastname, is("TEST_NACHNAME"));
        assertThat(roomNumber, is("A123"));
        assertThat(title, is("Prof"));
        assertThat(location, is("DEUTSCHLAND"));
        
    }
    
    @Test
    public void unknownUserInResourceServer() throws OAuthSystemException, OAuthProblemException, SQLException
    {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(TestConstants.TEST_RESOURCE_USER_ENDPOINT_V1);
        
        String validAccessToken = Util.getValidAccessToken();
        
        PrepareDatabase.deleteUserFromDb(TestConstants.TEST_USERNAME);
        
        Response response = target.queryParam("access_token", validAccessToken).request(MediaType.APPLICATION_JSON).get();      
        assertThat(response.getStatus(), is(404));
        assertThat(response.getEntity(), notNullValue());
 
        JSONObject errorMessage = new JSONObject(response.readEntity(String.class));
        assertThat(errorMessage, notNullValue());
        assertThat(("the user with the username: '" + TestConstants.TEST_USERNAME + "' could not be found"), is(errorMessage.get("error_description")));
        assertThat("not_found", is(errorMessage.get("error")));
    }
}
