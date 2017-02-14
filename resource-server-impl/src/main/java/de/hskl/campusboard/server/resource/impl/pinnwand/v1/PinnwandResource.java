/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.pinnwand.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hskl.campusboard.server.resource.pinnwand.v1.APinnwandResource;
import de.hskl.campusboard.server.resource.pinnwand.v1.IPinnwandManager;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
@Path("v1/pinnwand")
@Produces(
{ MediaType.APPLICATION_JSON })
public class PinnwandResource extends APinnwandResource<Category, PinnwandEntryPreview, Author, Attachment, PinnwandEntry>
{
    @Inject
    private IPinnwandManager<Category,PinnwandEntryPreview,Author,Attachment,PinnwandEntry> pinnwandResourceService;
	
    @Override
    protected IPinnwandManager<Category, PinnwandEntryPreview, Author, Attachment, PinnwandEntry> getPinnwandResourceService()
    {
        return this.pinnwandResourceService;
    }	
}
