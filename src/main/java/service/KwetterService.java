/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
