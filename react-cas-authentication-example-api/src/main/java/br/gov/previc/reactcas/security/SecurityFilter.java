package br.gov.previc.reactcas.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.util.Base64;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;


@Provider
public class SecurityFilter implements ContainerRequestFilter {
	static final Logger logger = LogManager.getLogger();
	@Context
    private HttpServletRequest sr;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.debug(requestContext.getUriInfo().getPath());
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
       
		String secureJwt = headers.getFirst("x-access-token");
		if (secureJwt==null) {
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Unauthorized.")
                    .build());
		}
		logger.debug(secureJwt);
		
		final String signingKey = "..."; // Get from cas service definition
		final String encryptionKey = "..."; // Get from cas service definition

		final Key key = new AesKey(signingKey.getBytes(StandardCharsets.UTF_8));

		final JsonWebSignature jws = new JsonWebSignature();
		try {
			jws.setCompactSerialization(secureJwt);
			jws.setKey(key);
			if (!jws.verifySignature()) {
			    throw new RuntimeException("JWT verification failed");
			}

			final byte[] decodedBytes = Base64.decode(jws.getEncodedPayload().getBytes(StandardCharsets.UTF_8));
			final String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8);

			final JsonWebEncryption jwe = new JsonWebEncryption();
			final JsonWebKey jsonWebKey = JsonWebKey.Factory
			    .newJwk("\n" + "{\"kty\":\"oct\",\n" + " \"k\":\"" + encryptionKey + "\"\n" + "}");

			jwe.setCompactSerialization(decodedPayload);
			jwe.setKey(new AesKey(jsonWebKey.getKey().getEncoded()));
			logger.debug(jwe.getPlaintextString());
		} catch (JoseException | RuntimeException e) {
			logger.debug(e);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Unauthorized.")
                    .build());
		}
		         	
    }

}
	