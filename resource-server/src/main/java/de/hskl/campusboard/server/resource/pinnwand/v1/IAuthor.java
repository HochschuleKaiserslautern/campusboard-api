/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IAuthor {
  
  public String getFirstname();
  public void setFirstname(String firstname);
  
  public String getLastname();
  public void setLastname(String lastname); 
  
  public String getEmail();
  public void setEmail(String email);
}
