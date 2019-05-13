/*
 * Copyright (C) 2019 Yannick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rest.utils;

import common.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.ext.Provider;
import rest.utils.AuthenticatedUser;
import rest.utils.RoleSecured;
import service.JWTService;
import service.KeyGenerator;


/**
 *
 * @author Yannick
 */
@RoleSecured
@Provider
@ApplicationScoped
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Inject
    @AuthenticatedUser
    private Event<String> userAuthenticatedEvent;

    @Inject
    private JWTService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Get the Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (authorizationHeader == null
            || !authorizationHeader
                        .toLowerCase()
                        .startsWith(AuthenticationFilter.AUTHENTICATION_SCHEME.toLowerCase())) {
            this.abortWithUnauthorized(requestContext);
        } else {
            // Extract the token from the Authorization header
            String token = authorizationHeader.substring(AuthenticationFilter.AUTHENTICATION_SCHEME.length()).trim();

            try {
                // Validate the token
                this.validateToken(token);

                this.userAuthenticatedEvent.fire(token);
            } catch (InvalidTokenException ex) {
                this.abortWithUnauthorized(requestContext);
            }
        }
    }

    /**
     * Abort the filter chain with a 401 status code response
     * <p>
     * The WWW-Authenticate header is sent along with the response
     *
     * @param requestContext
     */
    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(
                Response.status(UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, AuthenticationFilter.AUTHENTICATION_SCHEME)
                        .build());
    }

    /**
     * Check if the token was issued by the server and if it's not expired
     *
     * @param token
     *
     * @throws InvalidTokenException Throw if the token is invalid
     */
    private void validateToken(String token) throws InvalidTokenException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(KeyGenerator.getAPI_KEY_SECRET_BYTES())
                    .parseClaimsJws(token)
                    .getBody();

            claims.getSubject();
        } catch (SignatureException | ExpiredJwtException ex) {
            throw new InvalidTokenException();
        }

        // Check if the JWT exists
        if (!this.jwtService.existsByToken(token))
            throw new InvalidTokenException();
    }
}
