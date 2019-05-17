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

import domain.enums.Role;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rest.dto.HashTagDTO;
import rest.utils.RoleSecured;
import service.HashTagService;

/**
 *
 * @author Yannick
 */
@Path("hashtags")
@SecurityRequirement(name = "bearerAuth")
@Stateless
public class HashTagResource {
    
    @Inject
    private HashTagService hashTagService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @RoleSecured({Role.USER, Role.MODERATOR, Role.ADMINISTRATOR})
    public Response getHashTags() {
        List<HashTagDTO> hashTagDTO = hashTagService.getHashTags().stream()
                .map((hashTag) -> new HashTagDTO(hashTag, true))
                .collect(Collectors.toList());
        return Response.ok(hashTagDTO).build();
    }
}
