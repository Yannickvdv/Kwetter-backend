/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tweet;

import domain.Tweet;
import java.util.List;

/**
 *
 * @author Yannick
 */
public interface TweetDao {

    /**
     *
     * @return The existing tweets
     */
    List<Tweet> getTweets();
    
}
