/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.impl.auth.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;

import de.hskl.campusboard.server.auth.service.OAuthService;
import de.hskl.campusboard.server.impl.auth.entity.Client;
import de.hskl.campusboard.server.impl.auth.entity.ClientSecret;
import de.hskl.campusboard.server.impl.auth.entity.RefreshToken;
import de.hskl.campusboard.server.impl.auth.entity.User;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Stateless
public class OAuthServiceImpl extends OAuthService<ClientSecret, Client, RefreshToken>
{
    @PersistenceContext(unitName = "auth_personal")
    private EntityManager em;

    @Inject
    private Logger log;
    
    public OAuthServiceImpl()
    {
        super();
    }

    @Override
    public Client findClientByIdAndSecret(final String clientId, final String clientSecret)
    {
        log.trace("findClientById(): {}", () -> "clientId: " + clientId + ", clientSecret: HIDDEN_FROM_LOG");

        TypedQuery<Client> query = em.createNamedQuery(Client.QUERY_GET_CLIENT_BY_ID_AND_CREDENTIALS, Client.class);
        query.setParameter("clientId", clientId);
        query.setParameter("clientSecret", clientSecret);

        try
        {
            Client client = query.getSingleResult();
            log.trace("findClientById(): {}", () -> "found clientId by credentials: " + clientId);
            return client;
        }
        catch (NoResultException nre)
        {
            log.trace("findClientById(): {}", () -> "couldn't find clientId by credentials: " + clientId);
            return null;
        }
    }

    @Override
    public RefreshToken findRefreshToken(final int refreshTokenId)
    {
        log.trace("findRefreshToken(): {}", () -> "refreshTokenId: " + refreshTokenId);

        RefreshToken refreshToken = em.find(RefreshToken.class, refreshTokenId);

        log.trace("findRefreshToken(): {}", () -> (refreshToken == null ? "couldn't find" : "found") + " refresh_token for refreshTokenId: " + refreshTokenId);

        return refreshToken;
    }

    @Override
    public RefreshToken insertNewRefreshToken(final RefreshToken newRefreshToken)
    {
        log.trace("insertNewRefreshToken(): {}", () -> "newRefreshToken: " + newRefreshToken);
        this.em.persist(newRefreshToken);
        this.em.flush();
        return newRefreshToken;

    }

    @Override
    public RefreshToken getNewInstanceOfRefreshToken()
    {
        log.trace("getNewInstanceOfRefreshToken()");

        return new RefreshToken();
    }

    @Override
    public boolean validateCredentials(String username, String password)
    {
        log.trace("validateCredentials(): {}", () -> "username: " + username + ", password: HIDDEN_FROM_LOG");

        if (username == null)
        {
            throw new NullPointerException("username id can't be null");
        }
        if (password == null)
        {
            throw new NullPointerException("password can't be null");
        }

        User user = findUserByCredentials(username, password);

        boolean validCredentials = user != null;

        log.trace("validateCredentials(): {}", () -> (validCredentials ? "found" : "couldn't find") + " valid credentials for username: " + username);

        return validCredentials;
    }

    private User findUserByCredentials(String username, String plainPassword)
    {
        log.trace("findClientById(): {}", () -> "username: " + username + ", plainPassword: HIDDEN_FROM_LOG");

        TypedQuery<User> query = em.createNamedQuery(User.QUERY_GET_USER_BY_CREDENTIALS, User.class);
        query.setParameter("username", username);
        query.setParameter("hashedPassword", plainPassword); //TODO -> hash password

        try
        {
            User user = query.getSingleResult();
            log.trace("findUserByCredentials(): {}", () -> "found username by credentials: " + username);
            return user;
        }
        catch (NoResultException nre)
        {
            log.trace("findUserByCredentials(): {}", () -> "couldn't find username by credentials: " + username);
            return null;
        }
    }

    @Override
    public void invalidateToken(RefreshToken token)
    {
        RefreshToken merged = this.em.merge(token);
        merged.setRevoked(true);
    }
}
