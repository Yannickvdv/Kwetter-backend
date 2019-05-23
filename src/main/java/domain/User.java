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
package domain;

import domain.auth.JWT;
import domain.enums.Language;
import domain.enums.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Yannick
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "user.findByUuid", query = "SELECT u FROM User u WHERE u.uuid = :uuid"),
    @NamedQuery(name = "user.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "user.getUsers", query = "SELECT u FROM User u")})
//    @NamedQuery(name = "user.getTimeline", query = "SELECT u.tweets FROM User u WHERE u.name = :name "
//            + "INNER JOIN u.following f")})
public class User implements Serializable {
    
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String uuid;
    

    @Column(nullable = false, unique = true)
    private String name;

    private String location;

    private String website;

    @Column(nullable = false)
    private String password;

    @Column(length = 160)
    private String bio;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    private String photo;
    
    @Setter(AccessLevel.NONE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Tweet> tweets;
    
    @Setter(AccessLevel.NONE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_is_mentioned",
            joinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "tweet_uuid", referencedColumnName = "uuid"))
    private List<Tweet> mentions;
 
    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "following")
    @LazyCollection(LazyCollectionOption.FALSE)
    private final List<User> followers;

    @Setter(AccessLevel.NONE)
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_follows",
               joinColumns = @JoinColumn(name = "followers_user", referencedColumnName = "uuid"),
               inverseJoinColumns = @JoinColumn(name = "followers_follows", referencedColumnName = "uuid"))
    private final List<User> following;

    
    @OneToOne
    private JWT jwt;

     public User() {
        this("", "", Language.Dutch);
    }
    
    public User(String name, String password, Language language){
        this.tweets = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        
        this.role = Role.USER;
        
        this.name = name;
        this.password = password;
        this.language = language;
    }
    
    /**
     * Add a {@link Tweet} to this {@link User}
     * 
     * @param tweet The {@link Tweet} written by the {@link User}
     */
    public void tweet(Tweet tweet) {
        this.tweets.add(tweet);
    }
    
    /**
     * Add a {@link Mention} to the {@link Tweet}
     * 
     * @param tweet 
     */
    public void addMention (Tweet tweet) {
        this.mentions.add(tweet);
    }
    
    /**
    * Add a follower to the list of followers. Not allowing a user to follow themselves.
    * 
    * @param follower The follower to be added to the followers list
    */
    public void addFollower(User follower) {
        if(!follower.equals(this)){
            this.followers.add(follower);
        }
    }
    
    /**
     * Remove a specific follower from the list of followers.
     * 
     * @param follower The follower to be removed from the followers list.
     */
    public void removeFollower(User follower){
        this.followers.remove(follower);
    }
    
    /**
     * Add a {@link User} to the list of people this {@link Uer} is following. Not allowing a user to unfollow themselves.
     * 
     * @param following  The follower to be added to the followers list
     */
    public void addFollowing(User following) {
        if(!following.equals(this)) {
            this.following.add(following);
        }
    }
    
    /**
     * Remove a specific {@link User} this {@link User} is following.
     * 
     * @param following The following to be removed from the following list.
     */
    public void removeFollowing (User following){
        this.following.remove(following);
    }
}
