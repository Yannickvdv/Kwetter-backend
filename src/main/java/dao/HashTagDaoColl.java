/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.HashTag;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Yannick
 */
@ApplicationScoped
public class HashTagDaoColl {
    
    private List<HashTag> HashTags = new ArrayList<>();
    
    /**
     * 
     * @return Return all {@link HashTag}
     */
    public List<HashTag> getHashTags() {
        return this.HashTags;
    }
    
    /**
     * Add {@link HashTag}
     * 
     * @param hashTag The {@link HashTag} to be added
     */
    public void addHashTag(HashTag hashTag) {
        this.HashTags.add(hashTag);
    }
}
