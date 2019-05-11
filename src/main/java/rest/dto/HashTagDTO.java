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
package rest.dto;

import domain.HashTag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Yannick
 */
@Getter @Setter
public class HashTagDTO {
    
    private String uuid;
    private String text;
    
    private List<TweetDTO> tweets;
    
    public HashTagDTO(HashTag hashTag) {
        this.uuid = hashTag.getUuid();
        this.text = hashTag.getText();
    }
    
    public HashTagDTO(HashTag hashTag, boolean fat) {
        this(hashTag);
        
        if (fat) {
            this.tweets = hashTag.getTweets().stream()
                    .map(TweetDTO::new)
                    .collect(Collectors.toList());
        }
    }
}
