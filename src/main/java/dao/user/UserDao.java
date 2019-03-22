/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
