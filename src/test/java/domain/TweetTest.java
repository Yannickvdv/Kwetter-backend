/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.Language;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yannick
 */
public class TweetTest {
    
    private User user0;
    private Tweet tweet;
    
    public TweetTest() {
        this.user0 = new User("Bert", "Password0", Language.English);
        this.tweet = new Tweet("Tweet", this.user0);
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

    //Haven't found anything useful to test here yet, since only the getters
    //and setters are implemented
    //TODO: Add useful test
    @Test
    public void testTweet() {
    }
}
