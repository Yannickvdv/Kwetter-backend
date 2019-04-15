/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.hashtag.HashTagDao;
import dao.tweet.TweetDao;
import dao.user.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author Yannick
 */
@RunWith(MockitoJUnitRunner.class)
public class KwetterServiceTest {
    
    @Mock
    private static TweetDao tweetDao;
    @Mock
    private static UserDao userDao;
    @Mock
    private static HashTagDao hashTagDao;
    
    @InjectMocks
    private KwetterService kwetterService;
    
    
    @Before
    public void setUp() {
        this.kwetterService = new KwetterService();
    }
    

    @Test
    public void tweetTest() {
//        //Create users
//        User user0 = new User("user0", "huh", Language.Dutch);
//        User user1 = new User("user1", "heythere", Language.English);
//        
//        //Save them
//        this.userDao.addUser(user0);
//        this.userDao.addUser(user1);
//        
//        //Tweet with user0, while mentioning user1
//        Tweet tweet1 = new Tweet("Hey there @user1 #Hello", user0);
//        this.kwetterService.tweet(tweet1);
//
//        //Check if tweet and mention exist
//        assertEquals(Arrays.asList(tweet1), this.userDao.getUser(user0.getUuid()).getTweets());
//        assertEquals(Arrays.asList(tweet1), this.userDao.getUser(user1.getUuid()).getMentions());
//        assertTrue(this.hashTagDao.getHashTags().get(0).getText().equals("Hello"));
//        
//        //Check if getTweets returns tweets from all users
//        Tweet tweet2 = new Tweet("Hello World", user1);
//        this.tweetDao.addTweet(tweet2);
//        assertEquals(Arrays.asList(tweet1, tweet2), this.tweetDao.getTweets());
    }

}
