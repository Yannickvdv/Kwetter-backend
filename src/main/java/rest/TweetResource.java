/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import io.swagger.annotations.Api;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.TweetService;

/**
 *
 * @author Yannick
 */
@Path("tweets")
@Api
@Stateless
public class TweetResource {
    
    @Inject
    private TweetService tweetService;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTweets() {
        GenericEntity entity = new GenericEntity<List<Tweet>> (tweetService.getTweets()) {};
        return Response.ok(entity).build();
    }
}
