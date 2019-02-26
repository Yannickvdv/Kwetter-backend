/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.hashtag.HashTagDao;
import domain.HashTag;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Yannick
 */
@Stateless
public class HashTagService {
    
    @Inject
    private HashTagDao hashTagDao;
    
    public HashTagService() {
    }

    public List<HashTag> getHashTags() {
        return this.hashTagDao.getHashTags();
    }
    
    public HashTag findByName(String name) {
        return this.hashTagDao.findByName(name);
    }
    
    public void addHashTag(HashTag hashTag) {
        this.hashTagDao.addHashTag(hashTag);
    }
    
}
