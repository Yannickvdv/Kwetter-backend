/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import domain.User;
import io.swagger.annotations.Api;
import java.net.URI;
import java.util.List;
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
        System.out.println(userService.getUsers());
        GenericEntity entity = new GenericEntity<List<User>> (userService.getUsers()) {};
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("{name}")
    public Response getUser(@PathParam("name") String name) {
        User user = userService.getUser(name);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(user).build();
    }
    
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUser(User user) {
        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.editUser(user);
        URI id = URI.create(user.getName());
        return Response.created(id).build();
    }
    
    @POST
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.addUser(user);
        URI id = URI.create(user.getName());
        return Response.created(id).build();
    }
    
    @GET
    @Path("{name}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTweets(@PathParam("name") String name) {
        //TODO: Might have to use tweetservice?
        User user = userService.getUser(name);
        GenericEntity tweets = new GenericEntity<List<Tweet>>(user.getTweets()) {};
        return Response.ok(tweets).build();
    }
    
    @POST
    @Path("{name}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendTweet(Tweet tweet) {
        if(tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        tweetService.tweet(tweet);
        URI id = URI.create(tweet.getUser().getName());
        return Response.created(id).build();
    }
}
