/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resource.pinnwand.v1.IPinnwandManager;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
public class PinnwandManager implements IPinnwandManager<Category,PinnwandEntryPreview,Author,Attachment,PinnwandEntry>
{
	@Inject
	private Logger log;
	
	@Override
	public List<PinnwandEntryPreview> getEntriesByUserTargetGroup()
	{
		log.trace("getNachrichtenOfUser()");

		List<PinnwandEntryPreview> previews = new ArrayList<>();
		PinnwandEntryPreview p = new PinnwandEntryPreview();
		p.setSubject("TEST3");
		previews.add(p);
		
		PinnwandEntryPreview p2 = new PinnwandEntryPreview();
		p2.setSubject("TEST4");
		previews.add(p2);
		
		return previews;
	}

    @Override
    public List<PinnwandEntryPreview> getAllEntries()
    {
        // TODO Auto-generated 
        throw new UnsupportedOperationException("Method getAllEntries() is not supported yet.");
    }

    @Override
    public List<PinnwandEntryPreview> getExternMessages()
    {
        // TODO Auto-generated 
        throw new UnsupportedOperationException("Method getExternMessages() is not supported yet.");
    }

    @Override
    public List<Category> getCategories()
    {
        // TODO Auto-generated 
        throw new UnsupportedOperationException("Method getCategories() is not supported yet.");
    }

    @Override
    public PinnwandEntry getEntryById(String id)
    {
        // TODO Auto-generated 
        throw new UnsupportedOperationException("Method getEntryById() is not supported yet.");
    }	
}
