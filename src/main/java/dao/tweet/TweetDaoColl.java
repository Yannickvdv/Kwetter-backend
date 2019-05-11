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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Yannick
 */
@Singleton @Alternative
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
            if(tweet.getUuid().equals(id)) {
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
        Tweet foundTweet = this.getTweet(tweet.getUuid());
        foundTweet.like(user);
    }
    
    
}
