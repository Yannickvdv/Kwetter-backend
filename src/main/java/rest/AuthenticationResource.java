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
package rest;

import common.exceptions.InvalidCredentialsException;
import io.swagger.annotations.Api;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import rest.dto.auth.AccountCreateDTO;
import rest.dto.auth.AccountResponseDTO;
import service.AuthenticationService;
import service.UserService;


/**
 *
 * @author Yannick
 */
@Path("auth")
@Api
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class AuthenticationResource {
    
    @Context
    private UriInfo uriInfo;

    @Inject
    private AuthenticationService authService;
    
    @Inject
    private UserService userService;
    
    @POST
    @Path("register")
    public Response register(AccountCreateDTO dto) {
        AccountResponseDTO createdDTO = this.authService.register(dto);

        UriBuilder builder = this.uriInfo.getAbsolutePathBuilder();

        builder.path(createdDTO.getUuid());

        return Response.created(builder.build()).entity(createdDTO).build();
    }
   
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) throws InvalidCredentialsException {
        return Response.ok(this.authService.login(username, password)).build();
    }

}
