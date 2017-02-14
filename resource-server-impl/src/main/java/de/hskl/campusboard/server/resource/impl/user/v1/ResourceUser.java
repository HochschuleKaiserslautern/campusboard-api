/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.user.v1;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import de.hskl.campusboard.server.resource.user.v1.IUser;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Entity
@Table(name="api_user")
public class ResourceUser implements IUser
{
	@Id
	private String username;
	private String anrede;
	private String title;
	private String vorname;
	private String nachname;

	private String email;
	private String homepage;
	private String faxnr;
	private String telnr;
	private String raum;
	private String standort;

	private String fotourl;

	private boolean mitarbeiter;
	private boolean student;

	public String getAnrede()
	{
		return anrede;
	}

	public void setAnrede(String anrede)
	{
		this.anrede = anrede;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getVorname()
	{
		return vorname;
	}

	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}

	public String getNachname()
	{
		return nachname;
	}

	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getFaxnr()
	{
		return faxnr;
	}

	public void setFaxnr(String faxnr)
	{
		this.faxnr = faxnr;
	}

	public String getTelnr()
	{
		return telnr;
	}

	public void setTelnr(String telnr)
	{
		this.telnr = telnr;
	}

	public String getRaum()
	{
		return raum;
	}

	public void setRaum(String raum)
	{
		this.raum = raum;
	}

	public String getStandort()
	{
		return standort;
	}

	public void setStandort(String standort)
	{
		this.standort = standort;
	}

	public String getFotourl()
	{
		return fotourl;
	}

	public void setFotourl(String fotourl)
	{
		this.fotourl = fotourl;
	}

	public boolean isMitarbeiter()
	{
		return mitarbeiter;
	}

	public void setMitarbeiter(boolean mitarbeiter)
	{
		this.mitarbeiter = mitarbeiter;
	}

	public boolean isStudent()
	{
		return student;
	}

	public void setStudent(boolean student)
	{
		this.student = student;
	}
}
