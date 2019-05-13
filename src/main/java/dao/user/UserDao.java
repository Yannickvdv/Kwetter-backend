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
package dao.user;

import common.exceptions.UniqueConstraintViolationException;
import domain.User;
import domain.enums.Role;
import java.util.List;

/**
 *
 * @author Yannick
 */
public interface UserDao {

    /**
     * Add {@link User}
     *
     * @param user The {@link User} to add
     */
    void addUser(User user) throws UniqueConstraintViolationException;
    
    /**
     * Edit a {@link User} to a new user object
     * 
     * @param newUser {@link User} object containing the new values
     */
    void editUser(User newUser);

    /**
     * Get a user by uuid
     *
     * @param uuid User uuid to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    User getUser(String uuid);
    
    /**
     * Get a user by username
     *
     * @param name Username to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    User findByName(String name);
    
     /**
     * Get a user by JWT
     *
     * @param jwtToken JWT token to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    User findByJWT(String jwtToken);
    
    /**
     * Get all the existing users
     * 
     * @return The existing users
     */
    List<User> getUsers();

    /**
     * Set the role of a specific user
     *
     * @param user {@link User} to have role set
     * @param role {@link Role} to be added to user
     */
    void setUserRole(User user, Role role);
    
    /**
     * Add a follower to user's 'followers', and add the user to the follower his 'following'
     *
     * @param follower The {@link user} that will follow the user
     * @param user The {@link user} that will be followed
     */
    void follow(User follower, User user);

    
    /**
     * Remove a follower of a user's 'followers', and remove the user of the follower his 'following'
     *
     * @param unfollower The {@link user} that will unfollow the user
     * @param user The {@link user} that will be unfollowed
     */
    void unfollow(User unfollower, User user);
    
}
