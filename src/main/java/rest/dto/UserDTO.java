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

import domain.User;
import domain.enums.Language;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Yannick
 */
@Getter @Setter
public class UserDTO {
    
    private String uuid;
    private String name;
    private String location;
    private String bio;
    private String website;
    private Language language;
    private String photo;
    
    private List<TweetDTO> tweets;
    private List<TweetDTO> mentions;
    private List<UserDTO> following;
    private List<UserDTO> followers;
    
    public UserDTO(User user) {
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.location = user.getLocation();
        this.bio = user.getBio();
        this.website = user.getWebsite();
        this.language = user.getLanguage();
        this.photo = user.getPhoto();
    }
    
    public UserDTO(User user, boolean fat) {
        this(user);
        if (fat) {
            this.tweets = user.getTweets().stream()
                    .map(TweetDTO::new)
                    .collect(Collectors.toList());
            this.mentions = user.getMentions().stream()
                      .map(TweetDTO::new)
                      .collect(Collectors.toList());
            this.following = user.getFollowing().stream()
                      .map(UserDTO::new)
                      .collect(Collectors.toList());
            this.followers = user.getFollowers().stream()
                      .map(UserDTO::new)
                      .collect(Collectors.toList());
        }
    }
}