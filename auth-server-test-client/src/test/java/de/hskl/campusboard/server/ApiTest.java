/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server;

import static org.junit.Assert.assertTrue;
import org.junit.Before;

import org.junit.BeforeClass;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @sine 1.0.0
 */
public abstract class ApiTest
{
	@Before
	public void initDatabase()
	{
		new PrepareDatabase().init();
	}
	
	protected void assertEquals(Object toCheck, Object expectedValue)
	{
		assertTrue("\"" + toCheck + "\" is not equals \"" + expectedValue + "\"", expectedValue.equals(toCheck));
	}
	
	protected void assertNotEquals(Object toCheck, Object expectedValue)
    {
        assertTrue("\"" + toCheck + "\" is equals \"" + expectedValue + "\"", expectedValue.equals(toCheck) == false);
    }
}
