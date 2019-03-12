/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        System.out.println("---TweetDaoJPA works");
    }
    
    @Override
    public List<Tweet> getTweets() {
        TypedQuery<Tweet> query = this.em.createNamedQuery("Tweet.getTweets", Tweet.class);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public Tweet getTweet(String id) {
        return this.em.find(Tweet.class, id);
    }

    @Override
    public void addTweet(Tweet tweet) {
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
    
}
