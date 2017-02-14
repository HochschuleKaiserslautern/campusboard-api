/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.hskl.campusboard.server.auth.entity.IRefreshToken;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Entity
@Table(name = "refresh_token")
public class RefreshToken implements IRefreshToken
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="token_id")
    private int tokenId;
	
	@Column(name = "client_id")
	private String clientId;
	@Column(name = "username")
	private String username;
	@Column(name = "revoked")
	private boolean revoked;
	@Column(name = "issud_date_time")
	private LocalDateTime issuedDateTime;

	@Override
	public String getClientId()
	{
		return this.clientId;
	}

	@Override
	public boolean isRevoked()
	{
		return revoked;
	}

	@Override
	public LocalDateTime getIssuedDateTime()
	{
		return issuedDateTime;
	}

	@Override
	public String getUsername()
	{
		return username;
	}

	@Override
	public void setRevoked(boolean revoked)
	{
		this.revoked = revoked;
	}	

	@Override
	public void setIssuedDateTime(LocalDateTime issuedDateTime)
	{
		this.issuedDateTime = issuedDateTime;
	}

	@Override
	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	@Override
	public int getTokenId()
	{
		return this.tokenId;
	}

	@Override
	public void setTokenId(int tokenId)
	{
		this.tokenId = tokenId;
	}
}
