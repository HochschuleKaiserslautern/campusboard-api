/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import de.hskl.campusboard.server.resource.pinnwand.v1.IAttachment;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class Attachment implements IAttachment
{
    
	private String name;
	private String url;
	private String size_in_kb;

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSize_in_kb()
	{
		return size_in_kb;
	}

	public void setSize_in_kb(String size_in_kb)
	{
		this.size_in_kb = size_in_kb;
	}
}
