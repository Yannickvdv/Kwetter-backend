/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import common.exceptions.UniqueConstraintViolationException;
import domain.Tweet;
import domain.User;
import domain.enums.Language;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import service.KwetterService;
import service.UserService;

/**
 *
 * @author Yannick
 */
@Singleton
@Startup
public class StartUp {
    
    @Inject 
    private UserService userService;
    
    @Inject
    private KwetterService kwetterService;
    
    public StartUp() {
    }
    
    @PostConstruct
    private void initData() {
        User user1 = new User("Bart", "Eindhoven", Language.English);
        User user2 = new User("Henk", "New York", Language.Dutch);
        
        try {
            userService.addUser(user1);
            userService.addUser(user2);
        } catch (UniqueConstraintViolationException ex) {
            System.out.println(ex);
        }
        
        userService.follow(user2, user1);
        
        for(int i = 0; i < 100; i++) {
            Tweet tweet = new Tweet(
                    "Test #" + i,
                    i > 50 ? user1 : user2
            );
            kwetterService.tweet(tweet);
        }  
    }
}
