/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.impl.user.v1;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.resource.user.v1.UserResourceService;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
public class UserResourceServiceImpl implements UserResourceService<ResourceUser>
{
	@PersistenceContext(unitName="personal")
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	@Override
	public ResourceUser getUserByUsername(String id)
	{
		log.trace("getUserById: {}", () -> "looking for user with id: " + id);
		
		ResourceUser user = em.find(ResourceUser.class, id);
		
		log.trace("getUserById: {}", () -> (user == null ? "found no user with id: " + id : "found user for id: " + id));
		
		return user;
	}
}
