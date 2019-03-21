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

import dao.user.UserDaoJPA;
import domain.User;
import domain.enums.Language;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class UserDaoJPATest {
    
    UserDaoJPA userDaoJPA;
    
    User user0;
    User user1;
    User user2;
    User user3;
    
    public UserDaoJPATest() {
        this.userDaoJPA = new UserDaoJPA();
        
        this.user0 = new User("Bert", "Password0", Language.English);
        this.user1 = new User("Henk", "Password1", Language.Dutch);
        this.user2 = new User("Marc", "Password2", Language.English);
        this.user3 = new User("Dennis", "Password3", Language.English);
        
        this.userDaoJPA.addUser(this.user0);
        this.userDaoJPA.addUser(this.user1);
        this.userDaoJPA.addUser(this.user2);
        this.userDaoJPA.addUser(this.user3);
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
    public void testFollow() {
        this.userDaoJPA.follow(this.user0, this.user1);
        this.userDaoJPA.follow(this.user0, this.user2);
        
        assertEquals(Arrays.asList(this.user1, this.user2), this.userDaoJPA.findByName(this.user0.getName()).getFollowing());
        assertEquals(Arrays.asList(this.user0), this.userDaoJPA.findByName(this.user1.getName()).getFollowers());
        assertEquals(Arrays.asList(this.user0), this.userDaoJPA.findByName(this.user2.getName()).getFollowers()); 
    }
    
    @Test
    public void testUnfollow() {
        //Follow
        this.userDaoJPA.follow(this.user2, this.user3);
        assertEquals(Arrays.asList(this.user3), this.userDaoJPA.findByName(this.user2.getName()).getFollowing());
        assertEquals(Arrays.asList(this.user2), this.userDaoJPA.findByName(this.user3.getName()).getFollowers());
        
        //Unfollow
        this.userDaoJPA.unfollow(this.user2, this.user3);
        assertEquals(Arrays.asList(), this.userDaoJPA.findByName(this.user2.getName()).getFollowing());
        assertEquals(Arrays.asList(), this.userDaoJPA.findByName(this.user3.getName()).getFollowers());
    }
}
