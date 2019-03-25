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

import domain.User;
import domain.enums.Language;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class UserServiceTest {
    
    private UserService userService;
    
    @Before
    public void setUp() {
        this.userService = new UserService();
    }

   @Test
    public void testFollow() {
        User user0 = new User("user0", "barry", Language.English);
        User user1 = new User("user1", "huh", Language.Dutch);
        User user2 = new User("user2", "what", Language.Dutch);

        this.userService.addUser(user0);
        this.userService.addUser(user1);
        this.userService.addUser(user2);
        
        this.userService.follow(user0, user1);
        this.userService.follow(user0, user2);
        
        assertEquals(Arrays.asList(user1, user2), this.userService.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(user0), this.userService.findByName(user1.getName()).getFollowers());
        assertEquals(Arrays.asList(user0), this.userService.findByName(user2.getName()).getFollowers()); 
    }
    
    @Test
    public void testUnfollow() {
        User user0 = new User("user0", "henk", Language.English);
        User user1 = new User("user1", "test", Language.English);
        
        //Add users to user service
        this.userService.addUser(user0);
        this.userService.addUser(user1);
        
        //Follow
        this.userService.follow(user0, user1);
        assertEquals(Arrays.asList(user1), this.userService.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(user0), this.userService.findByName(user1.getName()).getFollowers());
        
        //Unfollow
        this.userService.unfollow(user0, user1);
        assertEquals(Arrays.asList(), this.userService.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(), this.userService.findByName(user1.getName()).getFollowers());
    }
}
