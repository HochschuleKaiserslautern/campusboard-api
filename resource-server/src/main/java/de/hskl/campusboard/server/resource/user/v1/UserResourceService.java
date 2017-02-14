/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.resource.user.v1;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface UserResourceService<U extends IUser>
{
	public U getUserByUsername(String id);
}
