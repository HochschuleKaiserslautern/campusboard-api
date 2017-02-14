/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import de.hskl.campusboard.server.auth.entity.IClient;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Entity
@Table(name = Client.TABLE_NAME)
@NamedQueries(value =
{ @NamedQuery(name=Client.QUERY_GET_CLIENT_BY_ID_AND_CREDENTIALS, query=Client.BASIC_SELECT + "JOIN c.clientSecret cs WHERE c.clientId = :clientId AND cs.clientSecret = :clientSecret") })
public class Client implements IClient<ClientSecret>
{
	protected static final String TABLE_NAME = "known_client";
	private static final String QUERY_PREFIX = "CLIENT_QUERY_";
	public static final String QUERY_GET_CLIENT_BY_ID_AND_CREDENTIALS = QUERY_PREFIX + "GET_CLIENT_BY_ID_AND_CREDENTIALS";
	protected static final String BASIC_SELECT = "SELECT c FROM Client c ";
	
	@Id
	@Column(name = "client_id")
	private String clientId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "client_id")
	private List<ClientSecret> clientSecret;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "client_id")
	private List<RefreshToken> refreshTokens;

	@ElementCollection
	@CollectionTable(name = "client_redirect_uri", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "redirect_uri")
	private List<String> validRedirectUris;

	@Override
	public String getClientId()
	{
		return clientId;
	}

	@Override
	public List<String> getValidRedirectUris()
	{
		return validRedirectUris;
	}

	@Override
	public List<ClientSecret> getClientSecret()
	{
		return clientSecret;
	}

	public void setValidRedirectUris(List<String> validRedirectUris)
	{
		this.validRedirectUris = validRedirectUris;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public void setClientSecret(List<ClientSecret> clientSecret)
	{
		this.clientSecret = clientSecret;
	}
}
