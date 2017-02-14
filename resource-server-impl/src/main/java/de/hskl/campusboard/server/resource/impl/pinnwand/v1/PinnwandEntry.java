/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import java.time.LocalDate;
import java.util.List;

import de.hskl.campusboard.server.resource.pinnwand.v1.IPinnwandEintrag;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class PinnwandEntry implements IPinnwandEintrag<Author, Attachment, Category>
{
	private String nachrichtId;
	private String subject;
	private String text;
	private String dateOfCreation;
	private String endDate;
	private int visitors;

	private List<Attachment> attachments;
	private List<Category> categories;

	private Author author;

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public String getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate.toString();
	}

	public String getDateOfCreation()
	{
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDate dateOfCreation)
	{
		this.dateOfCreation = dateOfCreation.toString();
	}

	public List<Attachment> getAttachments()
	{
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments)
	{
		this.attachments = attachments;
	}

	public String getNachrichtId()
	{
		return nachrichtId;
	}

	public void setNachrichtId(String nachrichtId)
	{
		this.nachrichtId = nachrichtId;
	}

	public int getVisitors()
	{
		return visitors;
	}

	public void setVisitors(int visitors)
	{
		this.visitors = visitors;
	}

	public List<Category> getCategories()
	{
		return categories;
	}

	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}
}
