/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource;

import de.hskl.campusboard.server.ApiTest;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class ResourceTest extends ApiTest
{
//    @Ignore
//	@Test
//	public void getUserDataTest()
//			throws OAuthSystemException, OAuthProblemException
//	{
//		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
//				TestConstants.TEST_RESOURCE_USER_ENDPOINT).buildQueryMessage();
//
//		OAuthResourceResponse resourceResponse = oAuthClient.resource(
//				bearerClientRequest, OAuth.HttpMethod.GET,
//				OAuthResourceResponse.class);
//
//		assertTrue(resourceResponse.getBody() != null);
//
//		JSONObject userData = new JSONObject(resourceResponse.getBody());
//
//		assertTrue(userData != null);
//
//		assertTrue("Julian_impl".equals(userData.get("firstname")));
//		assertTrue("Neuhaus_impl".equals(userData.get("lastname")));
//	}
//
//    @Ignore
//	@Test
//	public void pinnwandAuthMissing() throws OAuthSystemException, OAuthProblemException
//	{
//		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//
//		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
//				TestConstants.TEST_RESOURCE_PINNWAND_USER_ENDPOINT)
//						.buildQueryMessage();
//
//		OAuthResourceResponse resourceResponse = oAuthClient.resource(
//				bearerClientRequest, OAuth.HttpMethod.GET,
//				OAuthResourceResponse.class);
//
//		assertTrue(resourceResponse.getResponseCode() == 400);
//		assertTrue(resourceResponse.getBody() != null);
//
//		JSONObject errorMessage = new JSONObject(resourceResponse.getBody());
//		assertTrue(errorMessage != null);
//		assertTrue("this request needs authentication".equals(errorMessage.get("error_description")));
//		assertTrue("invalid_request".equals(errorMessage.get("error")));
//	}
//
//    @Ignore
//	@Test
//	public void pinnwandInvalidToken() throws OAuthSystemException, OAuthProblemException
//	{
//		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//
//		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
//				TestConstants.TEST_RESOURCE_PINNWAND_USER_ENDPOINT)
//						.setAccessToken(TestConstants.INVALID_ACCESS_TOKEN)
//						.buildQueryMessage();
//
//		OAuthResourceResponse resourceResponse = oAuthClient.resource(
//				bearerClientRequest, OAuth.HttpMethod.GET,
//				OAuthResourceResponse.class);
//
//		assertTrue(resourceResponse.getResponseCode() == 400);
//		assertTrue(resourceResponse.getBody() != null);
//
//		JSONObject errorMessage = new JSONObject(resourceResponse.getBody());
//		assertTrue(errorMessage != null);
//		assertTrue("invalid access_token".equals(errorMessage.get("error_description")));
//		assertTrue("invalid_grant".equals(errorMessage.get("error")));
//	}
//
//    @Ignore
//	@Test
//	public void getPinnwandUserDataTest()
//			throws OAuthSystemException, OAuthProblemException
//	{
//		OAuthClientRequest request = Util.createPasswordRequest();
//		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//		OAuthJSONAccessTokenResponse jsonAccessTokenResponse = oAuthClient
//				.accessToken(request);
//
//		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
//				TestConstants.TEST_RESOURCE_PINNWAND_USER_ENDPOINT)
//						.setAccessToken(
//								jsonAccessTokenResponse.getAccessToken())
//						.buildQueryMessage();
//
//		OAuthResourceResponse resourceResponse = oAuthClient.resource(
//				bearerClientRequest, OAuth.HttpMethod.GET,
//				OAuthResourceResponse.class);
//
//		assertTrue(resourceResponse.getBody() != null);
//		
//		System.out.println(resourceResponse.getBody());
//
//		JSONArray pinnwandData = new JSONArray(resourceResponse.getBody());
//
//    	assertTrue(pinnwandData != null);
//    	
//    	assertTrue(pinnwandData.length() == 2);
//    	
//    	JSONObject data1 = pinnwandData.getJSONObject(0);
//    	JSONObject data2 = pinnwandData.getJSONObject(1);
//    	
//    	System.out.println(data1.length());
//    	
//    	assertTrue(0 == data1.getInt("visitors"));
//    	assertTrue("TEST3".equals(data1.get("subject")));
//    	
//    	assertTrue(0  == data2.getInt("visitors"));
//    	assertTrue("TEST4".equals(data2.get("subject")));
//	}
}
