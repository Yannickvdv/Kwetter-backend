/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tweet;

import domain.Tweet;
import domain.User;
import java.util.List;

/**
 *
 * @author Yannick
 */
public interface TweetDao {

    /**
     * Get the existing tweets
     * 
     * @return The existing tweets
     */
    List<Tweet> getTweets();

    /**
     * Get a specific tweet based on ID
     * 
     * @param id The ID correlating to the tweet
     * @return The tweet to which the ID correlates
     */
    Tweet getTweet(String id);
    
    /**
     * Create a new tweet
     * 
     * @param tweet The new tweet
     */
    void addTweet(Tweet tweet);
    
    /**
     * Edit a tweet
     * 
     * @param tweet The edited tweet
     */
    void editTweet(Tweet tweet);
      
    /**
     * Remove a tweet
     * 
     * @param tweet The tweet to be removed 
     */
    void remove(Tweet tweet);
    
    /**
     * Like a specific tweet
     * 
     * @param tweet The tweet to be liked
     * @param user The user that likes the tweet
     */
    void like(Tweet tweet, User user);
}
