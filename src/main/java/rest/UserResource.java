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

import common.exceptions.UniqueConstraintViolationException;
import domain.Tweet;
import domain.User;
import io.swagger.annotations.Api;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.dto.TweetDTO;
import rest.dto.UserDTO;
import service.KwetterService;
import service.UserService;

/**
 *
 * @author Yannick
 */
@Path("users")
@Produces({MediaType.APPLICATION_JSON})
@Api
@Stateless
public class UserResource {
    
    @Inject
    private UserService userService;
    
    @Inject
    private KwetterService tweetService;
    
    @GET
    public Response getUsers() {
        List<User> users = this.userService.getUsers();
        
        List<UserDTO> usersDTO = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return Response.ok(usersDTO).build();
    }
    
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") String uuid) {
        User user = userService.getUser(uuid);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        UserDTO userDTO = new UserDTO(user, true);
        return Response.ok(userDTO).build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(User user) {
        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.editUser(user);
        URI id = URI.create(user.getUuid());
        return Response.created(id).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        try {
            userService.addUser(user);            
            URI id = URI.create(user.getUuid());
            return Response.created(id).build();
        } catch (UniqueConstraintViolationException ex ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
    @GET
    @Path("{uuid}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendTweet(Tweet tweet) {
        if(tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        tweetService.tweet(tweet);
        URI id = URI.create(tweet.getUser().getUuid());
        return Response.created(id).build();
    }
}
