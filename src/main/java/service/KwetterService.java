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

import domain.HashTag;
import domain.Tweet;
import domain.User;
import domain.helpers.TextHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Yannick
 */
@Stateless
public class KwetterService {

    @Inject
    private HashTagService hashTagService;
    @Inject
    private UserService userService;
    @Inject
    private TweetService tweetService;

    /**
     * Add a new {@link Tweet} to an existing {@link User} Mentions and HashTags
     * are parsed out and added
     *
     * @param tweet The {@link Tweet} to be added
     */
    public void tweet(Tweet tweet) {
        // Tweet
        this.tweetService.addTweet(tweet);

         // Add the mentions to the user
        List<String> mentions = TextHelper.searchMentions(tweet.getText());
        mentions.forEach((String s) -> {
            User user = this.userService.findByName(s);
            if (user != null)
                user.addMention(tweet);
        });
        
        // Create new hashTags and add the tweet
        List<String> hashtags = TextHelper.searchHashTags(tweet.getText());
        hashtags.forEach((String s) -> {
            HashTag hashTag = this.hashTagService.findByName(s);
            if (hashTag != null) {
                 hashTag.addTweet(tweet);
            }
            else {
                HashTag newHashTag = new HashTag(s, tweet);
                this.hashTagService.addHashTag(newHashTag);
            }
                
        });   
    }
}
