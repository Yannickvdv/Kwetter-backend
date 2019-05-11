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
