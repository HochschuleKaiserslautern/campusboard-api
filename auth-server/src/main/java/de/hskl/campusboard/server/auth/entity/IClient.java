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

import java.util.List;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
public interface IClient<CS extends IClientSecret>
{
	public List<String> getValidRedirectUris();
	public void setValidRedirectUris(List<String> validRedirectUris);
	
	public String getClientId();
	public void setClientId(String clientId);
	
	public List<CS> getClientSecret();
	public void setClientSecret(List<CS> clientSecret);
}
