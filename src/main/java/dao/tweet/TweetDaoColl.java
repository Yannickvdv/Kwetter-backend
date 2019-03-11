/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tweet;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Yannick
 */
@Stateless @Alternative
public class TweetDaoColl implements TweetDao {
    
    private List<Tweet> tweets = new ArrayList<>();
    
    /**
     * 
     * @return The existing tweets
     */
    @Override
    public List<Tweet> getTweets() {
        return tweets;
    }

    @Override
    public void addTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    @Override
    public Tweet getTweet(String id) {
        for(Tweet tweet : this.tweets) {
            if(tweet.getTweetId().equals(id)) {
                return tweet;
            }
        }
        return null;
    }

    @Override
    public void editTweet(Tweet tweet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Tweet tweet) {
        this.tweets.remove(this.tweets.indexOf(tweet));
       
    }

    @Override
    public void like(Tweet tweet, User user) {
        Tweet foundTweet = this.getTweet(tweet.getTweetId());
        foundTweet.like(user);
    }
    
    
}
