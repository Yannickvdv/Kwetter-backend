/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.hashtag;

import domain.HashTag;
import java.util.List;

/**
 *
 * @author Yannick
 */
public interface HashTagDao {
    
    /**
     * 
     * @return Return all {@link HashTag}
     */
    List<HashTag> getHashTags();
    
    /**
     * Add {@link HashTag}
     * 
     * @param hashTag The {@link HashTag} to be added
     */
    HashTag findByName(String name);
    
    /**
     * Find a specific {@link HashTag} by its name
     * 
     * @param name The name of the {@link HashTag}
     * @return The corresponding {@link HashTag} with that name
     */
    void addHashTag(HashTag hashTag);
}
