/* 
 * Copyright (C) 2019 Yannick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
