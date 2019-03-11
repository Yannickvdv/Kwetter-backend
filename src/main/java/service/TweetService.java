/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.tweet.TweetDao;
import domain.Tweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Yannick
 */
@Stateless
public class TweetService {
    
    @Inject
    private TweetDao tweetDAO;
    
    public List<Tweet> getTweets() {
        return this.tweetDAO.getTweets();
    }
    
    public Tweet getTweet(String id) {
        return this.tweetDAO.getTweet(id);
    }
     
    public void addTweet(Tweet tweet){
        this.tweetDAO.addTweet(tweet);
    }
    
    public void editTweet(Tweet tweet) {
        this.tweetDAO.editTweet(tweet);
    }

    public void remove(Tweet tweet) {
        this.tweetDAO.remove(tweet);
    }
    
    public void like(Tweet tweet, User user) {
        this.tweetDAO.like(tweet, user);
    }
}
