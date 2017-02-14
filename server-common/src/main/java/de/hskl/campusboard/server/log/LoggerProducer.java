/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.log;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public class LoggerProducer
{
	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint)
	{
		return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
}
