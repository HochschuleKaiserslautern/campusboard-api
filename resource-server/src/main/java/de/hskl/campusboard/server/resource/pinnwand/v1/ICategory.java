/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface ICategory {
	
  public String getId();
  public void setId(String id);
  
  public String getName();
  public void setName(String name);
}
