/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import domain.User;
import domain.enums.Role;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Yannick
 */
@ApplicationScoped
public class UserDaoColl {
    
    private List<User> users = new ArrayList<>();
    
    /**
     * 
     * @return The existing users
     */
    public List<User> getUsers() {
        return users;
    }
    
    /**
     * Add {@link User}
     * 
     * @param user The {@link User} to add
     */
    public void addUser(User user) {
        users.add(user);
    }
    
    /**
     * Get a user by username
     * 
     * @param username User name to identify the {@Link User} by
     * @return Return the identified {@link User}
     */
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
    public void setUserRole(User user, Role role) {
        this.getUser(user.getName()).setRole(role);
    }
    
    /**
     * Add a follower to user's 'followers', and add the user to the follower his 'following'
     * 
     * @param follower The {@link user} that will follow the user
     * @param user The {@link user} that will be followed
     */
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
    public void unfollow(User unfollower, User user){
        this.getUser(user.getName()).removeFollower(unfollower);
        this.getUser(unfollower.getName()).removeFollowing(user);
    }
    
    public List<Tweet> getAllTweets(){
        List<Tweet> tweets = new ArrayList<>();
        this.users.forEach((User u) ->{
            tweets.addAll(u.getTweets());
        });
        return tweets;
    }
}
