/**
  SOME GPLv3-License-Text
**/
package de.hskl.campusboard.server.exception;

import de.hskl.campusboard.server.exception.OAuthErrorBuilder.ERROR_TYPES;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public class ErrorResponse
{
	private ERROR_TYPES error;
	private String error_description;
	private String error_uri;

	public ERROR_TYPES getError()
	{
		return error;
	}

	public void setError(ERROR_TYPES error)
	{
		this.error = error;
	}

	public String getError_description()
	{
		return error_description;
	}

	public void setError_description(String error_description)
	{
		this.error_description = error_description;
	}

	public String getError_uri()
	{
		return error_uri;
	}

	public void setError_uri(String error_uri)
	{
		this.error_uri = error_uri;
	}
}
