/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Yannick
 */
@ApplicationScoped
public class TweetDaoColl {
    
    private List<Tweet> tweets = new ArrayList<>();
    
    /**
     * 
     * @return The existing tweets
     */
    public List<Tweet> getTweets() {
        return tweets;
    }
}
