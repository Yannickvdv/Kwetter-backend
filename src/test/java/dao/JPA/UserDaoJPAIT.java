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
    
    User user0;
    User user1;
    User user2;
    User user3;
    
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
        this.userDaoJPA.setEm(em);
        
        this.user0 = new User("Bert", "Password0", Language.English);
        this.user1 = new User("Henk", "Password1", Language.Dutch);
        this.user2 = new User("Marc", "Password2", Language.English);
        this.user3 = new User("Dennis", "Password3", Language.English);
        
        tx.begin();
        this.userDaoJPA.addUser(this.user0);
        this.userDaoJPA.addUser(this.user1);
        this.userDaoJPA.addUser(this.user2);
        this.userDaoJPA.addUser(this.user3);
        tx.commit();
    }

    @Test 
    public void addUser() {
        User newUser = new User("Bart", "Password3", Language.English);
        
        tx.begin();
        userDaoJPA.addUser(newUser);
        tx.commit();
        tx.begin();
        List<User> users = userDaoJPA.getUsers();
        tx.commit();
        
        assert(users.contains(this.user0));
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
