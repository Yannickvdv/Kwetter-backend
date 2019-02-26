/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.tweet;

import dao.JPA;
import domain.Tweet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Yannick
 */
@Stateless @JPA
public class TweetDaoJPA implements TweetDao {
    
    @PersistenceContext(unitName = "tweetPU")
    private EntityManager em;
    
    public TweetDaoJPA() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println("---UserDaoJPA works");
    }
    
    @Override
    public List<Tweet> getTweets() {
        Query query = em.createQuery("SELECT * FROM Tweet");
        return new ArrayList<>(query.getResultList());
    }
    
}
