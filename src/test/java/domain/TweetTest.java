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
