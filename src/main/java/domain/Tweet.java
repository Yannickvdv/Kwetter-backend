/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Yannick
 */
public class Tweet {
    
    private String text;
    private Date insertedAt;
    private List<User> likes;   
    
    public Tweet() {
    }
    
    public Tweet(String text, Date insertedAt){
        this.text = text;
        this.insertedAt = insertedAt;
    }
      
    public Tweet(String text, Date insertedAt, List<User> likes){
        this.text = text;
        this.insertedAt = insertedAt;
        this.likes = likes;
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

    void assertEquals(Tweet expectedTweet0, Tweet tweet0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
