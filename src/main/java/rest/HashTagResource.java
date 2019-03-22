/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.HashTag;
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
import service.HashTagService;

/**
 *
 * @author Yannick
 */
@Path("hashtags")
@Api
@Stateless
public class HashTagResource {
    
    @Inject
    private HashTagService hashTagService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHashTags() {
        GenericEntity entity = new GenericEntity<List<HashTag>> (hashTagService.getHashTags()) {};
        return Response.ok(entity).build();
    }
}
