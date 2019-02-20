/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Yannick
 */
public class Tweet {
    
    @Getter
    private String text;
    @Getter
    private User user;
    @Getter
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
        
        this.user.tweet(this);
    }
      
    public Tweet(String text, User user, Date insertedAt, List<User> likes){
        this.text = text;
        this.insertedAt = insertedAt;
        this.user = user;
        this.likes = likes;
        
        this.user.tweet(this);
    }
    
    public void like(User like)
    {
        if (!this.likes.contains(user)) {
            this.likes.add(like);
        } else {
            this.likes.remove(like);
        }
    }
    
    public int getLikesCount() {
        return this.likes.size();
    }
}
