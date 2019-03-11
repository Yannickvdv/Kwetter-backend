/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import dao.JPA;
import domain.User;
import domain.enums.Role;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yannick
 */
@Stateless @JPA @Default
public class UserDaoJPA implements UserDao {

    @PersistenceContext(unitName = "userPU")
    private EntityManager em;
    
    public UserDaoJPA() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("---UserDaoJPA works");
    }
    
    @Override
    public void addUser(User user) {
        em.persist(user);
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
    public User getUser(String name) {
        TypedQuery<User> query = em.createNamedQuery("user.findByName", User.class);
        query.setParameter("name", name);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = this.em.createNamedQuery("User.getUsers", User.class);
        return query.getResultList();
    }    
}
