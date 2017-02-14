/*
/* *
/* * Copyright 2016 Hochschule Kaiserslautern
/* *
/* * This file is part of de.hskl.campusboard.server.auth-server.
/* *
/* * de.hskl.campusboard.server.auth-server is free software: you can redistribute it and/or modify
/* * it under the terms of the GNU General Public License as published by
/* * the Free Software Foundation, either version 3 of the License, or
/* * (at your option) any later version.
/* *
/* * de.hskl.campusboard.server.auth-server is distributed in the hope that it will be useful,
/* * but WITHOUT ANY WARRANTY; without even the implied warranty of
/* * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/* * GNU General Public License for more details.
/* *
/* * You should have received a copy of the GNU General Public License
/* * along with de.hskl.campusboard.server.auth-server.  If not, see <http://www.gnu.org/licenses/>.
/* *
 */

package de.hskl.campusboard.server.auth.entity;

import java.time.LocalDateTime;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IRefreshToken
{
	public int getTokenId();
	public void setTokenId(int token);
	
	public String getClientId();
	public void setClientId(String clientId);

	public boolean isRevoked();
	public void setRevoked(boolean revoked);	

	public LocalDateTime getIssuedDateTime();
	public void setIssuedDateTime(LocalDateTime issuedDateTime);

	public String getUsername();
	public void setUsername(String username);
}
