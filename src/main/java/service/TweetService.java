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
