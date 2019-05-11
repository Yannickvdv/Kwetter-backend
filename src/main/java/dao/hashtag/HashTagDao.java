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
