/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.dto.TweetDTO;
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
        List<TweetDTO> tweetDTO = tweetService.getTweets().stream()
                .map(TweetDTO::new)
                .collect(Collectors.toList());
        
        return Response.ok(tweetDTO).build();
    }
}
