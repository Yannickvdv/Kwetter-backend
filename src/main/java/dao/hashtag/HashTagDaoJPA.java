/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hashtag;

import domain.HashTag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
/**
 *
 * @author Yannick
 */
@Stateless @Default
public class HashTagDaoJPA implements HashTagDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        System.out.println("---HashTagDaoJPA Initialized");
    }
    
    @Override
    public List<HashTag> getHashTags() {
       Query query = em.createNamedQuery("hashTag.getHashTags", HashTag.class);
       return new ArrayList<>(query.getResultList());
    }

    @Override
    public HashTag findByName(String name) {
        TypedQuery<HashTag> query = em.createNamedQuery("hashTag.findByName", HashTag.class);
        query.setParameter("name", name);
        List<HashTag> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public void addHashTag(HashTag hashTag) {
        em.persist(hashTag);
    }
    
    public int amount () {
        return getHashTags().size();
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
