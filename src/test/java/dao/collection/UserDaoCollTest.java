/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.collection;

import dao.user.UserDaoColl;
import domain.User;
import domain.enums.Language;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

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
    
    @Test
    public void testGetUsers() {
        List<User> users = Arrays.asList(this.user0, this.user1, this.user2, this.user3);
        assertEquals(this.userDaoColl.getUsers(), users);
    }
}
