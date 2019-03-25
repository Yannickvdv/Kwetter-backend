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

import common.DatabaseCleaner;
import dao.hashtag.HashTagDaoJPA;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class HashTagDaoJPAIT {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterPersistenceTestUnit");
    private EntityManager em;
    private EntityTransaction tx;
    private HashTagDaoJPA hashTagDaoJPA;
    
    @Before 
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJPAIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();
        
        this.hashTagDaoJPA = new HashTagDaoJPA();
        this.hashTagDaoJPA.setEm(em);
    }

    @Test
    public void addHashTag() {
        
    }
}
