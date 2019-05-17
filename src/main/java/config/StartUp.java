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
import java.util.ArrayList;
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
        User user1 = new User("Bart", "test", Language.English);
        User user2 = new User("Henk", "test", Language.Dutch);

        try {
            userService.addUser(user1);
            userService.addUser(user2);
        } catch (UniqueConstraintViolationException ex) {
            System.out.println(ex);
        }

        userService.follow(user2, user1);

        ArrayList<Tweet> tweets = new ArrayList<Tweet>();
        tweets.add(new Tweet("Hey here is a cool new tweet", user1));
        tweets.add(new Tweet("Here is another one", user1));
        tweets.add(new Tweet("I Love #bread", user1));
        tweets.add(new Tweet("I Love #bread so much", user1));
        tweets.add(new Tweet("Let me tell you about #bread", user1));
        tweets.add(new Tweet("It's much better than #rice", user1));
        tweets.add(new Tweet("#bread is obviously the superior #grain", user1));

        tweets.add(new Tweet("To the buttface @Bart that keeps saying #bread is the best, you are obviously wrong.", user2));
        tweets.add(new Tweet("#cereal is vastly superior", user2));
        tweets.add(new Tweet("#cereal helps with dehydration and starvation.", user2));
        tweets.add(new Tweet("And it comes with cool colory boxes", user2));
        tweets.add(new Tweet("Just to annoy you; I'm gonna tag you a few times @Bart", user2));
        tweets.add(new Tweet("@bart", user2));
        tweets.add(new Tweet("@Bart", user2));
        
        tweets.forEach((tweet) -> kwetterService.tweet(tweet));

    }
}
