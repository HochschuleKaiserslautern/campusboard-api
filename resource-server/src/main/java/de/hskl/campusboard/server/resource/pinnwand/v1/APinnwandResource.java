/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.pinnwand.v1;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resrouce.security.BasisAuthorization;


/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @param <C>
 * @param <PP>
 * @param <AU>
 * @param <A>
 * @param <PN>
 * @since 1.0.0
 */
public abstract class APinnwandResource<C extends ICategory, PP extends IPinnwandEintragPreview<C>, 
							AU extends IAuthor, A extends IAttachment,PN extends IPinnwandEintrag<AU, A, C>>
{
	@Inject
	private Logger log;
	
	protected abstract IPinnwandManager<C,PP,AU,A,PN> getPinnwandResourceService();
	
	@GET	
	@BasisAuthorization
	public List<PP> getMessagesByUser(
			@PathParam(value = "categoryIds") String categoryIdsWithSpaceDelimiter)
	{
		log.trace("getNachrichtenOfUser(): {}", () -> "categoryIdsDelimiterString: " + categoryIdsWithSpaceDelimiter);

		return this.getPinnwandResourceService().getEntriesByUserTargetGroup();
	}

	@GET
	@Path("all")
	@BasisAuthorization
	public List<PP> getAllMessages(
			@PathParam(value = "categoryIds") String categoryIdsWithSpaceDelimiter)
	{
		log.trace("getAllMessages(): {}", () -> "categoryIdsDelimiterString: " + categoryIdsWithSpaceDelimiter);
		return this.getPinnwandResourceService().getAllEntries();
	}

	@GET
	@Path("extern")
	public List<PP> getExternMessages()
	{
		log.trace("getExternMessages()");
		return this.getPinnwandResourceService().getExternMessages();
	}

	@GET
	@Path("categories")
	@BasisAuthorization
	public List<C> getAllCategories()
	{

		log.trace("getAllCategories()");
		return this.getPinnwandResourceService().getCategories();

	}

	@GET
	@Path("{id}")
	@BasisAuthorization
	public PN getEntryById(@PathParam("id") String id)
	{
		log.trace("getEntryById(): {}", () -> "id: " + id);
		return this.getPinnwandResourceService().getEntryById(id);
	}
}
