/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enums.Language;
import org.junit.Test;

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

    //Haven't found anything useful to test here yet, since only the getters
    //and setters are implemented
    //TODO: Add useful test
    @Test
    public void testTweet() {
    }
}
