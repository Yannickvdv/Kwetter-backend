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
