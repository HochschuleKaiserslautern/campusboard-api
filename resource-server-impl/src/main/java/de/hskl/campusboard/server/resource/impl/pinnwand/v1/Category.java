/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import de.hskl.campusboard.server.resource.pinnwand.v1.ICategory;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class Category implements ICategory
{
	private String name;
	private String id;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
