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
package dao.JPA;

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

import common.DatabaseCleaner;
import dao.user.UserDaoJPA;
import domain.User;
import domain.enums.Language;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class UserDaoJPAIT {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterPersistenceTestUnit");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDaoJPA userDaoJPA;
    
    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJPAIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
        
        this.userDaoJPA = new UserDaoJPA();
        this.userDaoJPA.setEm(em);    }

    @Test 
    public void addUser() {
        User newUser = new User("Bart", "Password3", Language.English);
        
        tx.begin();
        userDaoJPA.addUser(newUser);
        tx.commit();
        tx.begin();
        List<User> users = userDaoJPA.getUsers();
        tx.commit();
        
        assert(users.contains(newUser));
    }
    
    @Test(expected = RollbackException.class)
    public void AddUserSameName() {
        User newUser0 = new User("Dennis", "Password3", Language.English);
        User newUser1 = new User("Dennis", "Password3", Language.English);
        
        tx.begin();
        userDaoJPA.addUser(newUser0);
        tx.commit();
        tx.begin();
        userDaoJPA.addUser(newUser1);
        tx.commit();
    }
    
    @Test
    public void testFollow() {
        User user0 = new User("user0", "barry", Language.English);
        User user1 = new User("user1", "huh", Language.Dutch);
        User user2 = new User("user2", "what", Language.Dutch);

        tx.begin();
        this.userDaoJPA.addUser(user0);
        this.userDaoJPA.addUser(user1);
        this.userDaoJPA.addUser(user2);
        tx.commit();
        
        tx.begin();
        this.userDaoJPA.follow(user0, user1);
        this.userDaoJPA.follow(user0, user2);
        tx.commit();
        
        tx.begin();
        assertEquals(Arrays.asList(user1, user2), this.userDaoJPA.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(user0), this.userDaoJPA.findByName(user1.getName()).getFollowers());
        assertEquals(Arrays.asList(user0), this.userDaoJPA.findByName(user2.getName()).getFollowers()); 
        tx.commit();
    }
    
    @Test
    public void testUnfollow() {
        User user0 = new User("user0", "henk", Language.English);
        User user1 = new User("user1", "test", Language.English);
        
        //Add users to user service
        tx.begin();
        this.userDaoJPA.addUser(user0);
        this.userDaoJPA.addUser(user1);
        tx.commit();
        
        //Follow
        tx.begin();
        this.userDaoJPA.follow(user0, user1);
        assertEquals(Arrays.asList(user1), this.userDaoJPA.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(user0), this.userDaoJPA.findByName(user1.getName()).getFollowers());
        tx.commit();
        
        //Unfollow
        tx.begin();
        this.userDaoJPA.unfollow(user0, user1);
        assertEquals(Arrays.asList(), this.userDaoJPA.findByName(user0.getName()).getFollowing());
        assertEquals(Arrays.asList(), this.userDaoJPA.findByName(user1.getName()).getFollowers());
        tx.commit();
    }
}
