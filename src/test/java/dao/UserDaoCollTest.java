/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.user.UserDaoColl;
import domain.User;
import domain.enums.Language;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yannick
 */
public class UserDaoCollTest {
    
    UserDaoColl userDaoColl;
    
    User user0;
    User user1;
    User user2;
    User user3;
    
    public UserDaoCollTest() {
        this.userDaoColl = new UserDaoColl();
        
        this.user0 = new User("Bert", "Password0", Language.English);
        this.user1 = new User("Henk", "Password1", Language.Dutch);
        this.user2 = new User("Marc", "Password2", Language.English);
        this.user3 = new User("Dennis", "Password3", Language.English);
        
        this.userDaoColl.addUser(this.user0);
        this.userDaoColl.addUser(this.user1);
        this.userDaoColl.addUser(this.user2);
        this.userDaoColl.addUser(this.user3);
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
    public void testGetUsers() {
        List<User> users = Arrays.asList(this.user0, this.user1, this.user2, this.user3);
        assertEquals(this.userDaoColl.getUsers(), users);
    }

    @Test
    public void testFollow() {
        this.userDaoColl.follow(this.user0, this.user1);
        this.userDaoColl.follow(this.user0, this.user2);
        
        assertEquals(Arrays.asList(this.user1, this.user2), this.userDaoColl.getUser(this.user0.getName()).getFollowing());
        assertEquals(Arrays.asList(this.user0), this.userDaoColl.getUser(this.user1.getName()).getFollowers());
        assertEquals(Arrays.asList(this.user0), this.userDaoColl.getUser(this.user2.getName()).getFollowers()); 
    }
    
    @Test
    public void testUnfollow() {
        //Follow
        this.userDaoColl.follow(this.user2, this.user3);
        assertEquals(Arrays.asList(this.user3), this.userDaoColl.getUser(this.user2.getName()).getFollowing());
        assertEquals(Arrays.asList(this.user2), this.userDaoColl.getUser(this.user3.getName()).getFollowers());
        
        //Unfollow
        this.userDaoColl.unfollow(this.user2, this.user3);
        assertEquals(Arrays.asList(), this.userDaoColl.getUser(this.user2.getName()).getFollowing());
        assertEquals(Arrays.asList(), this.userDaoColl.getUser(this.user3.getName()).getFollowers());
    }
}
