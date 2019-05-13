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

import common.exceptions.UnauthorizedException;
import domain.User;
import domain.enums.Role;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author Yannick
 */
@RoleSecured
@Provider
@ApplicationScoped
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    @AuthenticatedUser
    private User authenticatedUser;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Get the data class which matches with the requested URL
        // Extract the roles declared by it
        Class<?> resourceClass = this.resourceInfo.getResourceClass();
        Set<Role> classRoles = this.extractRoles(resourceClass);

        // Get the data method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = this.resourceInfo.getResourceMethod();
        Set<Role> methodRoles = this.extractRoles(resourceMethod);

        try {
            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty())
                this.checkPermissions(classRoles);
            else
                this.checkPermissions(methodRoles);
        } catch (UnauthorizedException ex) {
            requestContext.abortWith(Response.status(FORBIDDEN).build());
        }
    }

    /**
     * Extract the roles from the annotated element
     *
     * @param annotatedElement
     *
     * @return
     */
    private Set<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null)
            return new HashSet<>();

        RoleSecured secured = annotatedElement.getAnnotation(RoleSecured.class);

        if (secured == null)
            return new HashSet<>();

        Role[] allowedRoles = secured.value();

        return new HashSet<>(Arrays.asList(allowedRoles));
    }

    /**
     * Check if the user contains one of the allowed roles Throw an Exception if
     * the user has not permission to execute the method
     *
     * @param allowedRoles
     *
     * @throws Exception
     */
    private void checkPermissions(Set<Role> allowedRoles) throws UnauthorizedException {
        if (!allowedRoles.contains(this.authenticatedUser.getRole()))
            throw new UnauthorizedException();
    }
}
