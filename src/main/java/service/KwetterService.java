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

import dao.hashtag.HashTagDao;
import dao.tweet.TweetDao;
import dao.user.UserDao;
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
    private HashTagDao hashTagDAO;
    @Inject
    private UserDao userDAO;
    @Inject
    private TweetDao tweetDAO;

    /**
     * Add a new {@link Tweet} to an existing {@link User} Mentions and HashTags
     * are parsed out and added
     *
     * @param tweet The {@link Tweet} to be added
     */
    public void tweet(Tweet tweet) {
        //Tweet
        this.tweetDAO.addTweet(tweet);

        //Add Tweets to the existing HashTags
        List<String> usedHashTagStrings = TextHelper.searchHashTags(tweet.getText());
        List<HashTag> availableHashTags = this.hashTagDAO.getHashTags();

        //Loop through used hastags and add tweet to them if applicable
        usedHashTagStrings.forEach(used -> {
            
            HashTag foundHashTag = availableHashTags.stream()
                    .filter(available -> used.equals(available.getText()))
                    .findFirst()
                    .orElse(null);
            
            if (foundHashTag != null) {
                foundHashTag.addTweet(tweet);
                usedHashTagStrings.remove(used);
            }
        });

        //Add new HashTags
        usedHashTagStrings.forEach((String s) -> {
            this.hashTagDAO.addHashTag(new HashTag(s, tweet));
        });

        //Add the mentions to the user
        TextHelper.searchMentionedUsers(tweet.getText(), this.userDAO.getUsers()).forEach((User u) -> {
            u.addMention(tweet);
        });
    }
}
