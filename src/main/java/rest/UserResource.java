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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.exceptions.UniqueConstraintViolationException;
import domain.Tweet;
import domain.User;
import domain.enums.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import rest.dto.TweetDTO;
import rest.dto.UserDTO;
import rest.utils.RoleSecured;
import service.KwetterService;
import service.UserService;

/**
 *
 * @author Yannick
 */
@Path("users")

@Produces({MediaType.APPLICATION_JSON})
@SecurityRequirement(name = "bearerAuth")
@RoleSecured({Role.USER, Role.MODERATOR, Role.ADMINISTRATOR})

@Stateless
public class UserResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserService userService;

    @Inject
    private KwetterService tweetService;

    @GET
    @Operation
    public Response getUsers() {
        List<User> users = this.userService.getUsers();

        List<UserDTO> usersDTO = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        
        List<UserDTO> userDTOS = userService.addSelfLinks(usersDTO, uriInfo);

        return Response.ok(userDTOS).build();
    }

    @GET
    @Path("{id}")
    @Operation
    public Response getUser(@PathParam("id") String uuid) {
        User user = userService.getUser(uuid);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UserDTO userDTO = userService.addSelfLink(new UserDTO(user, true), uriInfo);
        userDTO.setFollowing(userService.addSelfLinks(userDTO.getFollowing(),uriInfo));

        return Response.ok(userDTO).build();
    }

    @GET
    @Path("find/{username}")
    @Operation
    public Response getUserByName(@PathParam("username") String username) {
        User user = this.userService.findByName(username);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UserDTO userDTO = userService.addSelfLink(new UserDTO(user, true), uriInfo);
        userDTO.setFollowing(userService.addSelfLinks(userDTO.getFollowing(),uriInfo));

        return Response.ok(userDTO).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation
    public Response editUser(User user) {
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.editUser(user);
        URI id = URI.create(user.getUuid());
        return Response.created(id).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation
    public Response addUser(User user) {
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        try {
            userService.addUser(user);
            URI id = URI.create(user.getUuid());
            return Response.created(id).build();
        } catch (UniqueConstraintViolationException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{uuid}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation
    public Response getUserTweets(@PathParam("uuid") String uuid) {
        User user = userService.getUser(uuid);
        List<TweetDTO> tweetDTO = user.getTweets().stream()
                .map(TweetDTO::new)
                .collect(Collectors.toList());

        GenericEntity tweets = new GenericEntity<List<TweetDTO>>(tweetDTO) {};
        
        
        return Response.ok(tweets).build();
    }

    @POST
    @Path("{uuid}/tweets")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation
    public Response sendTweet(String body) {
        JsonObject jsonBody = new JsonParser().parse(body).getAsJsonObject();
            
        JsonObject jsonUser = jsonBody.getAsJsonObject("user");
        String userUuid = jsonUser.get("uuid").getAsString();
      
        if (userUuid == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        User poster = userService.getUser(userUuid);

        if (poster == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Tweet tweet = new Tweet(jsonBody.get("text").getAsString(), poster);
        this.tweetService.tweet(tweet);

        // NOTE: Auto generated fields cannot be returned this way
        TweetDTO kweetDTO = new TweetDTO(tweet);
        return Response.created(this.uriInfo.getAbsolutePathBuilder().path(kweetDTO.getUuid()).build()).entity(kweetDTO).build();
    }
}
