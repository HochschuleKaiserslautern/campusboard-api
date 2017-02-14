/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.impl.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
@Entity
@Table(name = User.TABLE_NAME)
@NamedQueries(value =
{ @NamedQuery(name=User.QUERY_GET_USER_BY_CREDENTIALS, query=User.BASIC_SELECT + "WHERE u.username = :username AND u.hashedPassword = :hashedPassword") })
public class User
{
	protected static final String TABLE_NAME = "api_user";
	private static final String QUERY_PREFIX = "USER_QUERY_";
	public static final String QUERY_GET_USER_BY_CREDENTIALS = QUERY_PREFIX + "GET_USER_BY_CREDENTIALS";
	protected static final String BASIC_SELECT = "SELECT u FROM User u ";
	
	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "username")
	private String username;
	@Column(name = "hashed_password")
	private String hashedPassword;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getHashedPassword()
	{
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword)
	{
		this.hashedPassword = hashedPassword;
	}
}
