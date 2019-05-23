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

import domain.Tweet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Link;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Yannick
 */
@Getter
@Setter
public class TweetDTO {

    private String uuid;
    private String text;
    private UserDTO user;
    private Instant insertedAt;

    private List<UserDTO> likes;
    private List<HashTagDTO> hashTags;

    private List<Link> links;

    public TweetDTO(Tweet tweet) {
        this.uuid = tweet.getUuid();
        this.text = tweet.getText();
        this.user = new UserDTO(tweet.getUser());
        this.insertedAt = tweet.getInsertedAt();

        this.links = new ArrayList<>();
    }

    public TweetDTO(Tweet tweet, boolean fat) {
        this(tweet);

        if (fat) {
            this.likes = tweet.getLikes().stream()
                    .map(UserDTO::new)
                    .collect(Collectors.toList());
            this.hashTags = tweet.getHashTags().stream()
                    .map(HashTagDTO::new)
                    .collect(Collectors.toList());
        }
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        this.links.add(link);
    }
}
