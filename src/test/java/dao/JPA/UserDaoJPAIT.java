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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
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
    
   
}
