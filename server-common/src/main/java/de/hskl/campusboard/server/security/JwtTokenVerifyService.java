/**
 * SOME GPLv3-License-Text
 *
 */
package de.hskl.campusboard.server.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.pem.PemReader;
import de.hskl.campusboard.server.conf.PropertyName;

/**
 * @author Julian Neuhaus <neuhaus.julian@gmail.com>
 * @since 1.0.0
 */
@Singleton
public class JwtTokenVerifyService
{
    @Inject
    @PropertyName("JWT_PUBLIC_KEY")
    private String jwtPublicKeyPath;

    public enum TOKEN_PAYLOAD_FIELDS  //TODO -> shorten fields
    {
        iss, //issuer-name		
        iat, //issued timestamp in seconds
        exp, //valid time in seconds after issued -> issued_time + 60seconds = 60seconds valid
        type,
        username,
        clientid,
        refreshTokenId
    }

    private PublicKey publicKey = null;

    @Inject
    private Logger log;

    @PostConstruct
    protected void loadKeys()
    {
        log.trace("loadKeys()");

        try
        {
            //TODO Should be loaded from property-file
            publicKey = PemReader.readPublicKey(this.jwtPublicKeyPath);
        }
        catch (NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | IOException e)
        {
            log.error(e);
        }
    }

    public Map<String, Object> hasValidJwsTokenSignature(String token) throws SecurityException
    {
        log.trace("hasValidJwsTokenSignature(): {}", () -> "token: " + token);

        if (publicKey == null)
        {
            throw new IllegalStateException("The publicKey has not been loaded -> abort jwt-verifying");
        }

        final JWTVerifier verifier = new JWTVerifier(this.publicKey);
        try
        {
            return verifier.verify(token);
        }
        catch (JWTExpiredException expiredException)
        {
            throw new SecurityException("the refresh_token is expired");
        }
        catch (InvalidKeyException | SignatureException | JWTVerifyException | IllegalStateException e)
        {
            throw new SecurityException("invalid token signature");
        }
        catch (NoSuchAlgorithmException | IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
