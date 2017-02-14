/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.user.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IUser
{
	public String getAnrede();

	public void setAnrede(String anrede);

	public String getTitle();

	public void setTitle(String title);

	public String getVorname();

	public void setVorname(String vorname);

	public String getNachname() ;

	public void setNachname(String nachname);

	public String getEmail();

	public void setEmail(String email);

	public String getHomepage();

	public void setHomepage(String homepage) ;

	public String getFaxnr();

	public void setFaxnr(String faxnr);

	public String getTelnr() ;

	public void setTelnr(String telnr) ;

	public String getRaum();

	public void setRaum(String raum);

	public String getStandort() ;

	public void setStandort(String standort);

	public String getFotourl() ;

	public void setFotourl(String fotourl) ;

	public boolean isMitarbeiter() ;

	public void setMitarbeiter(boolean mitarbeiter) ;

	public boolean isStudent() ;

	public void setStudent(boolean student) ;
}