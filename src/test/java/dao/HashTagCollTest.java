package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.HashTagDaoColl;
import domain.HashTag;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yannick
 */
public class HashTagCollTest {
        
    HashTagDaoColl hashTagDaoColl;
    
    public HashTagCollTest() {
        this.hashTagDaoColl = new HashTagDaoColl();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hashTagsTest() {
        HashTag hashTag = new HashTag("test", null);
         
        this.hashTagDaoColl.addHashTag(hashTag);
        assertEquals(Arrays.asList(hashTag), this.hashTagDaoColl.getHashTags());
    }
}
