/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import java.time.LocalDate;
import java.util.List;

import de.hskl.campusboard.server.resource.pinnwand.v1.IAuthor;
import de.hskl.campusboard.server.resource.pinnwand.v1.IPinnwandEintragPreview;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class PinnwandEntryPreview implements IPinnwandEintragPreview<Category>
{
	private String nachrichtId;
	private String subject;
	private String dateOfCreation;
	private int visitors;

	private IAuthor author;
	private List<Category> categories;

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public IAuthor getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public String getDateOfCreation()
	{
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDate dateOfCreation)
	{
		this.dateOfCreation = dateOfCreation.toString();
	}

	public int getVisitors()
	{
		return visitors;
	}

	public void setVisitors(int visitors)
	{
		this.visitors = visitors;
	}

	public String getNachrichtId()
	{
		return nachrichtId;
	}

	public void setNachrichtId(String nachrichtId)
	{
		this.nachrichtId = nachrichtId;
	}

	public List<Category> getCategories()
	{
		return categories;
	}

	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}

	@Override
	public void setAuthor(IAuthor author)
	{
		this.author = author;
	}
}
