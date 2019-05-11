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
