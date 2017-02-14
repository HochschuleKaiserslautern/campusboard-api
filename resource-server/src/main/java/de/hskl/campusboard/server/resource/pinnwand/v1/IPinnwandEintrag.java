/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IPinnwandEintrag<AU extends IAuthor, AT extends IAttachment, C extends ICategory>
{
	public String getSubject();

	public void setSubject(String subject);

	public String getText();

	public void setText(String text);

	public AU getAuthor();

	public void setAuthor(AU author);

	public String getEndDate();

	public void setEndDate(LocalDate endDate);

	public String getDateOfCreation();

	public void setDateOfCreation(LocalDate dateOfCreation);

	public List<AT> getAttachments();

	public void setAttachments(List<AT> attachments);

	public String getNachrichtId();

	public void setNachrichtId(String nachrichtId);

	public int getVisitors();

	public void setVisitors(int visitors);

	public List<C> getCategories();

	public void setCategories(List<C> categories);
}
