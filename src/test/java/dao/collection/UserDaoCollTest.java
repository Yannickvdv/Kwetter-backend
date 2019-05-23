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
