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
import domain.User;
import domain.enums.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Yannick
 */
@Singleton @Alternative
public class UserDaoColl implements UserDao {
    
    private List<User> users = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        System.out.println("---UserDaoCOLL works");
    }
    
    /**
     * 
     * @return The existing users
     */
    @Override
    public List<User> getUsers() {
        return users;
    }
    
    /**
     * Edit a {link @User} 
     * 
     * @param oldUser The {@link User} to be edited
     * @param newUser The {@link User} with the new values
     */
    @Override
    public void editUser(User newUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Add {@link User}
     * 
     * @param user The {@link User} to add
     */
    @Override
    public void addUser(User user) {
        users.add(user);
    }
    
    /**
     * Get a user by uuid
     * 
     * @param uuid User uuid to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    @Override
    public User getUser(String uuid) {
        for(User user : this.users) {
            if(user.getUuid().equalsIgnoreCase(uuid)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Get a user by username
     * 
     * @param name Username to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    @Override
    public User findByName(String name) {
        for(User user : this.users) {
            if(user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Set the role of a specific user
     * 
     * @param user {@link User} to have role set
     * @param role {@link Role} to be added to user
     */
    @Override
    public void setUserRole(User user, Role role) {
        this.getUser(user.getUuid()).setRole(role);
    }
    
    /**
     * Add a follower to user's 'followers', and add the user to the follower his 'following'
     * 
     * @param follower The {@link user} that will follow the user
     * @param user The {@link user} that will be followed
     */
    @Override
    public void follow(User follower, User user){
        this.getUser(user.getUuid()).addFollower(follower);
        this.getUser(follower.getUuid()).addFollowing(user);
    }
    
    /**
     * Remove a follower of a user's 'followers', and remove the user of the follower his 'following'
     * 
     * @param unfollower The {@link user} that will unfollow the user
     * @param user The {@link user} that will be unfollowed
     */
    @Override
    public void unfollow(User unfollower, User user){
        this.getUser(user.getUuid()).removeFollower(unfollower);
        this.getUser(unfollower.getUuid()).removeFollowing(user);
    }
}
