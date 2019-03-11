/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;
;
import domain.User;
import domain.enums.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Yannick
 */
@Stateless @Alternative
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
     * Get a user by username
     * 
     * @param username User name to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
    @Override
    public User getUser(String username) {
        for(User user : this.users) {
            if(user.getName().equalsIgnoreCase(username)) {
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
        this.getUser(user.getName()).setRole(role);
    }
    
    /**
     * Add a follower to user's 'followers', and add the user to the follower his 'following'
     * 
     * @param follower The {@link user} that will follow the user
     * @param user The {@link user} that will be followed
     */
    @Override
    public void follow(User follower, User user){
        this.getUser(user.getName()).addFollower(follower);
        this.getUser(follower.getName()).addFollowing(user);
    }
    
    /**
     * Remove a follower of a user's 'followers', and remove the user of the follower his 'following'
     * 
     * @param unfollower The {@link user} that will unfollow the user
     * @param user The {@link user} that will be unfollowed
     */
    @Override
    public void unfollow(User unfollower, User user){
        this.getUser(user.getName()).removeFollower(unfollower);
        this.getUser(unfollower.getName()).removeFollowing(user);
    }
}
