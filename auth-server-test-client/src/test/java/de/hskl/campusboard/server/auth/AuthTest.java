/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.auth;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.junit.Test;

import de.hskl.campusboard.server.ApiTest;
import de.hskl.campusboard.server.TestConstants;
import de.hskl.campusboard.server.Util;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class AuthTest extends ApiTest
{
	@Test
	public void getValidAccessTokenByCredentialsTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClientRequest request = Util.createPasswordRequest();
		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		OAuthJSONAccessTokenResponse jsonAccessTokenResponse = oAuthClient.accessToken(request);
		
		assert(jsonAccessTokenResponse != null);
		assert(jsonAccessTokenResponse.getResponseCode() == 200);
		assert(jsonAccessTokenResponse.getAccessToken() != null);	
		assert(jsonAccessTokenResponse.getRefreshToken() != null);	
	}
	
	@Test
	public void getValidAccessTokenByRefreshTokenTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClientRequest getRefreshTokenByCredentialRequest = Util.createPasswordRequest();		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());		
		OAuthJSONAccessTokenResponse getRefreshTokenResponse = oAuthClient.accessToken(getRefreshTokenByCredentialRequest);	
		
		String refreshToken = getRefreshTokenResponse.getRefreshToken();
		
		OAuthClientRequest getAccesTokenByRefershTokenRequest = Util.createRefreshTokenRequest(refreshToken,
				TestConstants.TEST_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
		
		
		OAuthJSONAccessTokenResponse renewAccessTokenResponse = oAuthClient.accessToken(getAccesTokenByRefershTokenRequest);	
				
		assert(renewAccessTokenResponse != null);
		assert(renewAccessTokenResponse.getResponseCode() == 200);
		assert(renewAccessTokenResponse.getAccessToken() != null);	
		assert(renewAccessTokenResponse.getRefreshToken() != null);		
		
		//try to use same refresh token which should fail because the new one revoked the old
		OAuthClientRequest invalidGetAccesTokenByRefershTokenRequest = Util.createRefreshTokenRequest(refreshToken,
				TestConstants.TEST_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
		
		try
		{
			oAuthClient.accessToken(invalidGetAccesTokenByRefershTokenRequest);	
			fail("request should fail because the old refresh_token should be revoked");
		}
		catch(OAuthProblemException e)
		{
			assertTrue("Response-State should be 400 but was " + e.getResponseStatus(), e.getResponseStatus() == 400);
			assertEquals(e.getError(), "invalid_grant");
			assertEquals(e.getDescription(), "invalid refresh_token");
		}
	    
	}
	
	@Test
	public void invalidRefreshTokenRequestTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		try
		{
			oAuthClient.accessToken(Util.createRefreshTokenRequest("123", TestConstants.TEST_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET));
			fail("missing exception for invalid refresh_token");
		}
		catch(OAuthProblemException e)
		{				
		    assertTrue(400 == e.getResponseStatus());
		    assertEquals(e.getError(), "invalid_grant");
		    assertEquals(e.getDescription(), "invalid token signature");
		}
	}
	
	@Test
	public void refreshTokeRequestWithWrongClientCredentialsTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClientRequest getRefreshTokenByCredentialRequest = Util.createPasswordRequest();		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());		
		OAuthJSONAccessTokenResponse getRefreshTokenResponse = oAuthClient.accessToken(getRefreshTokenByCredentialRequest);	
		
		String validRefreshToken = getRefreshTokenResponse.getRefreshToken();
		
		OAuthClientRequest[] invalidRequests = new OAuthClientRequest[2];
		invalidRequests[0] = Util.createRefreshTokenRequest(validRefreshToken, TestConstants.TEST_CLIENT_ID, TestConstants.INVALID_CLIENT_SECRET);
		invalidRequests[1] = Util.createRefreshTokenRequest(validRefreshToken, TestConstants.INVALID_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
		
		for(OAuthClientRequest invalidRequest : invalidRequests)
		{
			try
			{
				oAuthClient.accessToken(invalidRequest);
				fail("missing exception for invalid client credentials");
			}
			catch(OAuthProblemException e)
			{				
			    assertTrue(400 == e.getResponseStatus());
			    assertEquals(e.getError(), "unauthorized_client");
			    assertEquals(e.getDescription(), "invalid client_id / client_secrect combination");
			}
		}
	}
	
	@Test
	public void invalidUsernameOrPasswordTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClientRequest[] invalidRequests = new OAuthClientRequest[3];
		invalidRequests[0] = Util.createPasswordRequestForUser(TestConstants.INVALID_USERNAME, TestConstants.INVALID_USER_PASSWORD);
		invalidRequests[1] = Util.createPasswordRequestForUser(TestConstants.INVALID_USERNAME, TestConstants.TEST_USER_PASSWORD);
		invalidRequests[2] = Util.createPasswordRequestForUser(TestConstants.TEST_USERNAME, TestConstants.INVALID_USER_PASSWORD);
		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		for(OAuthClientRequest invalidRequest : invalidRequests)
		{
			try
			{
				oAuthClient.accessToken(invalidRequest);
				fail("missing exception for invalid user credentials");
			}
			catch(OAuthProblemException e)
			{				
			    assertTrue(400 == e.getResponseStatus());
			    assertEquals(e.getError(), "invalid_grant");
			    assertEquals(e.getDescription(), "invalid username / password combination");
			}
		}
	}
	
	@Test
	public void invalidClientCredentialsTest() throws OAuthSystemException, OAuthProblemException
	{
		OAuthClientRequest[] invalidRequests = new OAuthClientRequest[3];
		invalidRequests[0] = Util.createPasswordRequestForClient(TestConstants.INVALID_CLIENT_ID, TestConstants.INVALID_CLIENT_SECRET);
		invalidRequests[1] = Util.createPasswordRequestForClient(TestConstants.INVALID_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
		invalidRequests[2] = Util.createPasswordRequestForClient(TestConstants.TEST_CLIENT_ID, TestConstants.INVALID_CLIENT_SECRET);
		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		for(OAuthClientRequest invalidRequest : invalidRequests)
		{
			try
			{
				oAuthClient.accessToken(invalidRequest);
				fail("missing exception for invalid client credentials");
			}
			catch(OAuthProblemException e)
			{				
			    assertTrue(400 == e.getResponseStatus());
			    assertEquals(e.getError(), "unauthorized_client");
			    assertEquals(e.getDescription(), "invalid client_id / client_secrect combination");
			}
		}
	}	
}
