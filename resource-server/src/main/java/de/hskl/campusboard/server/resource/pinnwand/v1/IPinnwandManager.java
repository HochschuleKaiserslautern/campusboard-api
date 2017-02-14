/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

import java.util.List;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IPinnwandManager<C extends ICategory, PP extends IPinnwandEintragPreview<C>, 
                                 AU extends IAuthor,A extends IAttachment,PN extends IPinnwandEintrag<AU,A,C>>
{
	public List<PP> getEntriesByUserTargetGroup();

    public List<PP> getAllEntries();

    public List<PP> getExternMessages();

    public List<C> getCategories();

    public PN getEntryById(String id);
}
