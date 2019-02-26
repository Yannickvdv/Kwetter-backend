/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tweet;

import domain.Tweet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author Yannick
 */
@Stateless @Default
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
}
