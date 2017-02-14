/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class Util
{
	public static OAuthClientRequest createPasswordRequest() throws OAuthSystemException
	{
		return createGenericPasswordRequest(TestConstants.TEST_USERNAME, TestConstants.TEST_USER_PASSWORD, TestConstants.TEST_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
	}
	
	public static OAuthClientRequest createPasswordRequestForUser(String username, String password) throws OAuthSystemException
	{
		return createGenericPasswordRequest(username, password, TestConstants.TEST_CLIENT_ID, TestConstants.TEST_CLIENT_SECRET);
	}
	
	public static OAuthClientRequest createPasswordRequestForClient(String client_id, String client_secret) throws OAuthSystemException
	{
		return createGenericPasswordRequest(TestConstants.TEST_USERNAME, TestConstants.TEST_USER_PASSWORD, client_id, client_secret);
	}
	
	public static OAuthClientRequest createGenericPasswordRequest(String username, String password, String client_id, String client_secret) throws OAuthSystemException
	{
		OAuthClientRequest request = OAuthClientRequest
                .tokenLocation(TestConstants.TEST_TOKEN_ENDPOINT)
                .setGrantType(GrantType.PASSWORD)
                .setPassword(password)
                .setUsername(username)
                .setClientId(client_id)
                .setClientSecret(client_secret)
                .buildQueryMessage();
		
		return request;
	}
	
	public static OAuthClientRequest createRefreshTokenRequest(String refreshToken, String client_id, String client_secret) throws OAuthSystemException
	{
		OAuthClientRequest request = OAuthClientRequest
                .tokenLocation(TestConstants.TEST_TOKEN_ENDPOINT)
                .setGrantType(GrantType.REFRESH_TOKEN)
                .setRefreshToken(refreshToken)
                .setClientId(client_id)
                .setClientSecret(client_secret)
                .buildQueryMessage();
		
		return request;
	}
	
	public static String getValidAccessToken() throws OAuthSystemException, OAuthProblemException
	{
	    OAuthClientRequest request = Util.createPasswordRequest();
        
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        
        OAuthJSONAccessTokenResponse jsonAccessTokenResponse = oAuthClient.accessToken(request);
        
        return jsonAccessTokenResponse.getAccessToken();
	}
}
