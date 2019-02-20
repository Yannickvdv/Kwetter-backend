/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.sun.mail.iap.ByteArray;
import domain.enums.Language;
import domain.enums.Role;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Yannick
 */
public class User {
   
    
    @Getter @Setter private String name;
    @Getter @Setter private String location;
    @Getter @Setter private String website;
    @Getter @Setter private String password;
    @Getter @Setter private String bio;
    @Getter @Setter private Role role;
    @Getter @Setter private Language language;
    @Getter @Setter private ByteArray photo;
    
    @Getter private List<Tweet> tweets;
    @Getter private List<Tweet> mentions;
    
    @Getter private List<User> following;
    @Getter private List<User> followers;

    public User() {
    }
    
    public User(String name, String password, Language language){
        this.tweets = new ArrayList();
        this.mentions = new ArrayList();
        
        this.following = new ArrayList();
        this.followers = new ArrayList();
        
        this.name = name;
        this.password = password;
        this.language = language;
    }
    
    public User(String name, String location, String website, String password, String bio, Role role, Language language, ByteArray photo, List<Tweet> tweets, List<Tweet> mentions, List<User> following, List<User> followers) {
        this.name = name;
        this.location = location;
        this.website = website;
        this.password = password;
        this.bio = bio;
        this.role = role;
        this.language = language;
        this.photo = photo;
        this.tweets = tweets;
        this.mentions = mentions;
        this.following = following;
        this.followers = followers;
    }
    
    /**
     * Add a {@link Tweet} to this {@link User}
     * 
     * @param tweet The {@link Tweet} written by the {@link User}
     */
    public void tweet(Tweet tweet) {
        this.tweets.add(tweet);
    }
    
    /**
     * Add a {@link Mention} to the {@link Tweet}
     * 
     * @param tweet 
     */
    public void addMention (Tweet tweet) {
        this.mentions.add(tweet);
    }
    
    /**
    * Add a follower to the list of followers. Not allowing a user to follow themselves.
    * 
    * @param follower The follower to be added to the followers list
    */
    public void addFollower(User follower) {
        if(!follower.equals(this)){
            this.followers.add(follower);
        }
    }
    
    /**
     * Remove a specific follower from the list of followers.
     * 
     * @param follower The follower to be removed from the followers list.
     */
    public void removeFollower(User follower){
        this.followers.remove(follower);
    }
    
    /**
     * Add a {@link User} to the list of people this {@link Uer} is following. Not allowing a user to unfollow themselves.
     * 
     * @param following  The follower to be added to the followers list
     */
    public void addFollowing(User following) {
        if(!following.equals(this)) {
            this.following.add(following);
        }
    }
    
    /**
     * Remove a specific {@link User} this {@link User} is following.
     * 
     * @param follower The follower to be removed from the followers list.
     */
    public void removeFollowing (User following){
        this.following.remove(following);
    }
}
