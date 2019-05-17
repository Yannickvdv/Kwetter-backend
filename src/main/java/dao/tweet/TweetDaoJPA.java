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
package dao.tweet;

import domain.Tweet;
import domain.User;
import java.util.ArrayList;
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
@Stateless @Default
public class TweetDaoJPA implements TweetDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        System.out.println("---TweetDaoJPA Initialized");
    }
    
    @Override
    public List<Tweet> getTweets() {
        TypedQuery<Tweet> query = this.em.createNamedQuery("tweet.getTweets", Tweet.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public Tweet getTweet(String id) {
        return this.em.find(Tweet.class, id);
    }

    @Override
    public void addTweet(Tweet tweet) {
        System.out.println(tweet);
        this.em.persist(tweet);
    }

    @Override
    public void editTweet(Tweet tweet) {
        this.em.merge(tweet);
    }

    @Override
    public void remove(Tweet tweet) {
        this.em.remove(this.em.merge(tweet));
    }

    @Override
    public void like(Tweet tweet, User user) {
        tweet.like(user);
        this.editTweet(tweet);
    }
    
    /**
     * Set the entity manager of TweetDaoJPA
     * 
     * @param em The entity manager to be set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
