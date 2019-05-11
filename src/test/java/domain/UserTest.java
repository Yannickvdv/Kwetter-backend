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
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class UserTest {
    private User user0;
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;
    private User user8;
    private User user9;
    
    public UserTest() {
        this.user0 = new User("Bert", "Password0", Language.English);
        this.user1 = new User("Henk", "Password1", Language.Dutch);
        this.user2 = new User("Marc", "Password2", Language.English);
        this.user3 = new User("Dennis", "Password3", Language.English);
        this.user4 = new User("Donald Trump", "Password4", Language.Dutch);
        this.user5 = new User("Frank", "Password5", Language.English);
        this.user6 = new User("Sir Lancelot III", "Password6", Language.English);
        this.user7 = new User("Adrianus", "Password7", Language.English);
        this.user8 = new User("JavaScript", "Password8", Language.English);
        this.user9 = new User("Asta", "Password9", Language.Dutch);
    }
    
    @Test
    public void testFollowers() {
        List user0Followers = Arrays.asList(this.user8, this.user2);
        user0.addFollower(this.user8);
        user0.addFollower(this.user2);
        assertEquals(user0.getFollowers(), user0Followers);
        
        List user1Followers = Arrays.asList(this.user5, this.user7, this.user9);
        user1.addFollower(this.user5);
        user1.addFollower(this.user7);
        user1.addFollower(this.user9); 
        assertEquals(user1.getFollowers(), user1Followers);
    }
    
    @Test 
    public void testSelfAsFollower() {
        user2.addFollower(this.user2);
        assertEquals(user2.getFollowers(), Arrays.asList());
        
        List expectedUser3Followers = Arrays.asList(this.user8, this.user4);
        user3.addFollower(this.user3);
        user3.addFollower(this.user8);
        user3.addFollower(this.user4);
        assertEquals(user3.getFollowers(), expectedUser3Followers);
    }
}
