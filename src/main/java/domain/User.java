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

/**
 *
 * @author Yannick
 */
public class User {
   
    private String name;
    private String location;
    private String website;
    private String password;
    private String bio;
    private Role role;
    private Language language;
    private ByteArray photo;
    
    private List<Tweet> tweets;
    private List<Tweet> mentions;
    
    private List<User> following;
    private List<User> followers;

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
    
    public void tweet(Tweet tweet) {
        this.tweets.add(tweet);
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
    
    public void removeFollower(User follower){
        this.followers.remove(follower);
    }
    
    public void addFollowing(User following) {
        if(!following.equals(this)) {
            this.following.add(following);
        }
    }
    
    public void removeFollowing (User following){
        this.following.remove(following);
    }
    
    /* - Getters and Setters - */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ByteArray getPhoto() {
        return photo;
    }

    public void setPhoto(ByteArray photo) {
        this.photo = photo;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public List<Tweet> getMentions() {
        return mentions;
    }

    public void setMentions(List<Tweet> mentions) {
        this.mentions = mentions;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }
}
