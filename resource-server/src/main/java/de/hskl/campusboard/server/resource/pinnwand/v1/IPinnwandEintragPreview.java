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
public interface IPinnwandEintragPreview<C extends ICategory>
{
	public String getSubject();

	public void setSubject(String subject);

	public IAuthor getAuthor();

	public void setAuthor(IAuthor author);

	public String getDateOfCreation();

	public void setDateOfCreation(LocalDate dateOfCreation);

	public int getVisitors();

	public void setVisitors(int visitors);

	public String getNachrichtId();

	public void setNachrichtId(String nachrichtId);

	public List<C> getCategories();

	public void setCategories(List<C> categories);
}
