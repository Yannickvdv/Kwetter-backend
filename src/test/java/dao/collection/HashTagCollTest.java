package dao.collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.hashtag.HashTagDaoColl;
import domain.HashTag;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class HashTagCollTest {
        
    HashTagDaoColl hashTagDaoColl;
    
    @Before
    public void setUp() {
        this.hashTagDaoColl = new HashTagDaoColl();
    }

    @Test
    public void hashTagsTest() {
        HashTag hashTag = new HashTag("test", null);
         
        this.hashTagDaoColl.addHashTag(hashTag);
        assertEquals(Arrays.asList(hashTag), this.hashTagDaoColl.getHashTags());
    }
}
