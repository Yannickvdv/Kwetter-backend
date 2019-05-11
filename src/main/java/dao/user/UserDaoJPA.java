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
package dao.user;

import common.exceptions.UniqueConstraintViolationException;
import dao.helpers.JPAResultHelper;
import domain.User;
import domain.enums.Role;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Yannick
 */
@Stateless @Default
public class UserDaoJPA implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        System.out.println("---UserDaoJPA Initialized");
    }
    
    @Override
    public void addUser(User user) throws UniqueConstraintViolationException {
        try {
            this.em.persist(user);
        } catch (EJBTransactionRolledbackException ex) {
            Throwable t = ex.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                throw new UniqueConstraintViolationException("User name must be unique", ex);
            }    
        }
    }

    @Override
    public void follow(User follower, User user) {
        user.addFollower(follower);
        this.editUser(user);
        
        follower.addFollowing(user);
        this.editUser(follower);
    }
    
    
    @Override
    public void unfollow(User unfollower, User user) {
        user.removeFollower(unfollower);
        this.editUser(user);
        
        unfollower.removeFollowing(user);
        this.editUser(unfollower);
    }
    
    @Override
    public void setUserRole(User user, Role role) {
        user.setRole(role);
        this.editUser(user);        
    }
    
    @Override
    public void editUser(User newUser) {
        this.em.merge(newUser);
    }

    @Override
    public User getUser(String uuid) {
        TypedQuery<User> query = this.em.createNamedQuery("user.findByUuid", User.class);
        query.setParameter("uuid", uuid);
        
        return JPAResultHelper.getSingleResult(query);
    }
    
    @Override
    public User findByName(String name) {
        TypedQuery<User> query = this.em.createNamedQuery("user.findByName", User.class);
        query.setParameter("name", name);
        
        return JPAResultHelper.getSingleResult(query);
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = this.em.createNamedQuery("user.getUsers", User.class);
        return query.getResultList();
    }    
    
    /**
     * Set the entity manager of UserDaoJPA
     * 
     * @param em The entity manager to be set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
