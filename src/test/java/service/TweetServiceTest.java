/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Tweet;
import domain.User;
import domain.enums.Language;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class TweetServiceTest {
    
    private TweetService tweetService;
    private UserService userService;
    private HashTagService hashTagService;
    
    
    @Before
    public void setUp() {
        this.tweetService = new TweetService();
        this.userService = new UserService();
        this.hashTagService = new HashTagService();
    }
    

    @Test
    public void tweetTest() {
        //Create users
        User user0 = new User("user0", "huh", Language.Dutch);
        User user1 = new User("user1", "heythere", Language.English);
        
        //Save them
        this.userService.addUser(user0);
        this.userService.addUser(user1);
        
        //Tweet with user0, while mentioning user1
        Tweet tweet1 = new Tweet("Hey there @user1 #Hello", user0);
        this.tweetService.addTweet(tweet1);

        //Check if tweet and mention exist
        assertEquals(Arrays.asList(tweet1), this.userService.getUser(user0.getUuid()).getTweets());
        assertEquals(Arrays.asList(tweet1), this.userService.getUser(user1.getUuid()).getMentions());
        assertTrue(this.hashTagService.getHashTags().get(0).getText().equals("Hello"));
        
        //Check if getTweets returns tweets from all users
        Tweet tweet2 = new Tweet("Hello World", user1);
        this.tweetService.addTweet(tweet2);
        assertEquals(Arrays.asList(tweet1, tweet2), this.tweetService.getTweets());
    }

}
