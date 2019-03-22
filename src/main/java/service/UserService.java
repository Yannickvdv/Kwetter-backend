/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import common.exceptions.UniqueConstraintViolationException;
import dao.user.UserDao;
import domain.User;
import domain.enums.Role;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Yannick
 */
@Stateless
public class UserService {
    
    @Inject
    private UserDao userDao;
    
    public void addUser(User user) throws UniqueConstraintViolationException {
        this.userDao.addUser(user);
    }

    public User getUser(String uuid) {
        return this.userDao.getUser(uuid);
    }
    
    public User findByName(String name) {
        return this.userDao.getUser(name);
    }

    public List<User> getUsers() {
        return this.userDao.getUsers();
    }

    public void setUserRole(User user, Role role) {
        this.userDao.setUserRole(user, role);
    }
        
    public void follow(User follower, User user) {
        this.userDao.follow(follower, user);
    }

    public void unfollow(User unfollower, User user) {
        this.userDao.unfollow(unfollower, user);
    }

    public void editUser(User newUser) {
        this.userDao.editUser(newUser);
    }
}
