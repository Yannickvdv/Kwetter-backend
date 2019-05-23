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
package bean;

import domain.Tweet;
import domain.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import service.TweetService;
import service.UserService;

/**
 *
 * @author Yannick
 */
@Named("tweetBean")
@RequestScoped
public class TweetBean implements Serializable {

    @Inject
    private TweetService tweetService;
    @Inject
    private UserService userService;

    @Getter
    @Setter
    private String username;

    public List<Tweet> getTweets() {
        List<Tweet> tweets = new ArrayList<>();
        if (this.getUsername() == null || this.getUsername().isEmpty()) {
            tweets.addAll(this.tweetService.getTweets());
        } else {
            User user = this.userService.findByName(username);
            if (user != null) {
                List<Tweet> userTweets = user.getTweets();
                if (userTweets != null) {
                    tweets.addAll(userTweets);
                }
            }
        }
        return tweets;
    }

    public void removeTweet(Tweet tweet) {
        this.tweetService.remove(tweet);
    }
}
