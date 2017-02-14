/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Embeddable
public class ClientSecretId implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Column(name = "client_secret", insertable = false, updatable = false)
	private String clientSecret;

	@Column(name = "client_id", insertable = false, updatable = false)
	private String clientId;

	public ClientSecretId()
	{
		super();
	}

	public ClientSecretId(final String clientSecret, final String clientId)
	{
		super();
		this.clientSecret = clientSecret;
		this.clientId = clientId;
	}

	public String getClientId()
	{
		return clientId;
	}

	public String getClientSecret()
	{
		return clientSecret;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if((o instanceof ClientSecretId) == false)
			return false;
		
		ClientSecretId eq = (ClientSecretId) o;
		
		boolean equals = true;
		
		equals = this.clientId == null ? (eq.clientId == null) : this.clientId.equals(eq.clientId);
		equals = equals && this.clientSecret == null ? (eq.clientSecret == null) : this.clientSecret.equals(eq.clientSecret);
		
	    return equals;
	}
	
	@Override
	public int hashCode()
	{
		//TODO IMPROVE
		return (clientId + clientSecret).hashCode();
	}
}
