/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hskl.campusboard.server.auth.ATokenApi;
import de.hskl.campusboard.server.auth.service.OAuthService;
import de.hskl.campusboard.server.impl.auth.entity.Client;
import de.hskl.campusboard.server.impl.auth.entity.ClientSecret;
import de.hskl.campusboard.server.impl.auth.entity.RefreshToken;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
@Path("token")
@Produces({MediaType.APPLICATION_JSON})
public class TokenApiImpl extends ATokenApi<ClientSecret, Client, RefreshToken>
{
	@Inject
	private OAuthService<ClientSecret, Client, RefreshToken> oauthTokenService;
	
	@Override
	protected OAuthService<ClientSecret, Client, RefreshToken> getOAuthServiceImpl()
	{
		return oauthTokenService;
	}
}
