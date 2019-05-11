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
