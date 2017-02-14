/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import de.hskl.campusboard.server.resource.pinnwand.v1.IAuthor;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class Author implements IAuthor
{
	private String firstname;
	private String lastname;
	private String email;

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
