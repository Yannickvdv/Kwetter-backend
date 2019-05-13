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

import domain.Tweet;
import domain.enums.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.dto.TweetDTO;
import rest.utils.RoleSecured;
import service.TweetService;

/**
 *
 * @author Yannick
 */
@Path("tweets")
@SecurityRequirement(name = "bearerAuth")
@Stateless
public class TweetResource {
    
    @Inject
    private TweetService tweetService;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RoleSecured({Role.USER, Role.MODERATOR, Role.ADMINISTRATOR})
    public Response getTweets() {
        List<TweetDTO> tweetDTO = tweetService.getTweets().stream()
                .map(TweetDTO::new)
                .collect(Collectors.toList());
        
        return Response.ok(tweetDTO).build();
    }
    
    @DELETE
    @Path("{uuid}")
    @RoleSecured({Role.MODERATOR, Role.ADMINISTRATOR})
    public Response removeTweet(@PathParam("uuid") String uuid) {
        Tweet tweet = this.tweetService.getTweet(uuid);
        if(tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        this.tweetService.remove(tweet);
        return Response.ok().build();
    }
}
