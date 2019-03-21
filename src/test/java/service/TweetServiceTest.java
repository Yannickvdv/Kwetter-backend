/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.User;
import domain.enums.Language;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class TweetServiceTest {
    
    private TweetService tweetService;
    private UserService userService;
    private HashTagService hashTagService;
    
    User user0;
    User user1;
    User user2;
    User user3;
    
    public TweetServiceTest() {
        this.tweetService = new TweetService();
        this.userService = new UserService();
        this.hashTagService = new HashTagService();
        
        this.user0 = new User("user0", "barry", Language.English);
        this.user1 = new User("user1", "huh", Language.Dutch);
        this.user2 = new User("user2", "test", Language.English);
        this.user3 = new User("user3", "henk", Language.English);
        
        this.userService.addUser(user0);
        this.userService.addUser(user1);
        this.userService.addUser(user2);
        this.userService.addUser(user3);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void tweetTest() {
//        Tweet tweet1 = new Tweet("Hey there @user2 #Hello", this.user1);
//        this.tweetService.tweet(tweet1);
//
//        assertEquals(Arrays.asList(tweet1), this.userService.getUser(this.user1.getUuid()).getTweets());
//        assertEquals(Arrays.asList(tweet1), this.userService.getUser(this.user2.getUuid()).getMentions());
//        assertTrue(this.hashTagService.getHashTags().get(0).getText().equals("Hello"));
//
//        Tweet tweet2 = new Tweet("Hello World", this.user2);
//        this.tweetService.tweet(tweet2);
//        assertEquals(Arrays.asList(tweet1, tweet2), this.userService.getAllTweets());
    }

}
