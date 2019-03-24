/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hashtag;

import domain.HashTag;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.enterprise.inject.Alternative;

/**
 *
 * @author Yannick
 */
@Singleton @Alternative
public class HashTagDaoColl implements HashTagDao{
    
    private List<HashTag> HashTags = new ArrayList<>();
    
    @Override
    public List<HashTag> getHashTags() {
        return this.HashTags;
    }
    
    @Override
    public void addHashTag(HashTag hashTag) {
        this.HashTags.add(hashTag);
    }

    @Override
    public HashTag findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
