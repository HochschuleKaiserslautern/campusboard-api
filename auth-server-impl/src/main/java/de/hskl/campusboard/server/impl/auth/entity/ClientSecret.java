/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.hskl.campusboard.server.auth.entity.IClientSecret;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Entity
@Table(name = "client_secrect")
public class ClientSecret implements IClientSecret
{
	@EmbeddedId
	private ClientSecretId clientSecretId;

	@Column(name = "client_id")
	private String clientId;
	@Column(name = "client_secret")
	private String clientSecret;
	@Column(name = "active")
	private boolean active;
	@Column(name = "issued_date_time")
	private LocalDateTime issuedDateTime;

	@Override
	public String getClientId()
	{
		return this.clientId;
	}

	@Override
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	@Override
	public String getSecrect()
	{
		return this.clientSecret;
	}

	@Override
	public void setSecrect(String clientSecret)
	{
		this.clientSecret = clientSecret;
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public void setIssuedDateTime(LocalDateTime issuedDateTime)
	{
		this.issuedDateTime = issuedDateTime;
	}

	@Override
	public LocalDateTime getIssuedDateTime()
	{
		return this.issuedDateTime;
	}

	public ClientSecretId getClientSecretId()
	{
		return clientSecretId;
	}
}
