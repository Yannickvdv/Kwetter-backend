/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.HashTagDaoColl;
import dao.UserDaoColl;
import domain.HashTag;
import domain.helpers.TextHelper;
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
    public HashTagDaoColl hDAO;
    @Inject
    public UserDaoColl uDAO;
    
    /**
     * Add a new {@link Tweet} to an existing {@link User}
     * Mentions and HashTags are parsed out and added 
     * 
     * @param tweet The {@link Tweet} to be added
     */
    public void tweet(Tweet tweet) {
        //Add Tweets to the existing HashTags
        List<String> usedHashTags = TextHelper.searchHashTags(tweet.getText());
        List<HashTag> availableHashTags = this.hDAO.getHashTags();
        
        for(int i = 0; i < usedHashTags.size(); i++){
            for(int j = 0; j < availableHashTags.size(); j++){
                if(usedHashTags.get(i).equals(availableHashTags.get(j))){
                    availableHashTags.get(j).addTweet(tweet);
                    usedHashTags.remove(i);
                    j--;
                    break;
                }
            }
        }
        
        //Add new HashTags
        usedHashTags.forEach((String s) -> {
            this.hDAO.addHashTag(new HashTag(s, tweet));
        });
        
        //Add the mentions to the user
        TextHelper.searchMentionedUsers(tweet.getText(), this.uDAO.getUsers()).forEach((User u) -> {
            u.addMention(tweet);
        });
    }
}
