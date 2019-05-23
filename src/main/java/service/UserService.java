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
package service;

import common.exceptions.UniqueConstraintViolationException;
import dao.user.UserDao;
import domain.User;
import domain.enums.Role;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import rest.UserResource;
import rest.dto.UserDTO;

/**
 *
 * @author Yannick
 */
@Stateless
public class UserService {
    
    @Inject
    private UserDao userDao;
    
    public void addUser(User user) throws UniqueConstraintViolationException {
        this.userDao.addUser(user);
    }

    public User getUser(String uuid) {
        return this.userDao.getUser(uuid);
    }
    
    public User findByName(String name) {
        return this.userDao.findByName(name);
    }
    
    public User findByJWT(String jwtToken) {
        return this.userDao.findByJWT(jwtToken);
    }

    public List<User> getUsers() {
        return this.userDao.getUsers();
    }

    public void setUserRole(User user, Role role) {
        this.userDao.setUserRole(user, role);
    }
        
    public void follow(User follower, User user) {
        this.userDao.follow(follower, user);
    }

    public void unfollow(User unfollower, User user) {
        this.userDao.unfollow(unfollower, user);
    }

    public void editUser(User newUser) {
        this.userDao.editUser(newUser);
    }
    
    public UserDTO addSelfLink(UserDTO userDTO, UriInfo uriInfo){
        URI selfUri = uriInfo.getBaseUriBuilder().path(UserResource.class).path(UserResource.class, "getUser").build(userDTO.getUuid());

        Link.Builder linkBuilder = Link.fromUri(selfUri);
        Link selfLink = linkBuilder.rel("self").build();

        userDTO.addLink(selfLink);

        return userDTO;
    }

    public List<UserDTO> addSelfLinks(List<UserDTO> userDTOS, UriInfo uriInfo){
        List<UserDTO> userDTOSFinal = new ArrayList<>();
        userDTOS.forEach(userDTO -> {
            userDTOSFinal.add(addSelfLink(userDTO,uriInfo));
        });
        return userDTOSFinal;
    }

    public Set<UserDTO> addSelfLinks(Set<UserDTO> userDTOS, UriInfo uriInfo){
        Set<UserDTO> userDTOSFinal = new HashSet<>();
        userDTOS.forEach(userDTO -> {
            userDTOSFinal.add(addSelfLink(userDTO,uriInfo));
        });
        return userDTOSFinal;
    }
}
