/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IAttachment
{
	public String getUrl();
	public void setUrl(String url);

	public String getName();
	public void setName(String name);

	public String getSize_in_kb();
	public void setSize_in_kb(String size_in_kb);
}
