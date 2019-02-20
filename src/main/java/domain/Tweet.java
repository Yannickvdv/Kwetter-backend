/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Yannick
 */
public class Tweet {
    
    private String text;
    private User user;
    private Date insertedAt;
    private List<User> likes;   
    
    public Tweet() {
        this.likes = new ArrayList();
    }
    
    public Tweet(String text, User user){
        this.text = text;
        this.insertedAt = new Date();
        this.user = user;
        this.likes = new ArrayList();
        
        this.user.addTweet(this);
    }
      
    public Tweet(String text, User user, Date insertedAt, List<User> likes){
        this.text = text;
        this.insertedAt = insertedAt;
        this.user = user;
        this.likes = likes;
        
        this.user.addTweet(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    public User getUser(){
        return user;
    }               
    
    public void setUser(User user){
        this.user = user;
    }
    
    public List<User> getLikes() {
        return likes;
    }
    
    public void setLikes(List<User> likes) {
        this.likes = likes;
    }
    
    public void addLike(User like)
    {
        this.likes.add(like);
    }
}
