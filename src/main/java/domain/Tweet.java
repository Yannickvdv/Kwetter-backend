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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Yannick
 */
@Getter
@Entity
@Table(name = "tweets")
@NamedQueries({
    @NamedQuery(name = "tweet.getTweets", query = "SELECT t FROM Tweet t")})
public class Tweet implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    
    @Column(length = 140)
    private String text;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_uuid")
    private User user;
    
    @Temporal(TemporalType.DATE)
    private Date insertedAt;
    
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "tweet_has_likes",
            joinColumns = @JoinColumn(name = "tweet_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "user_uuid", referencedColumnName = "uuid"))
    private List<User> likes;   

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "tweets")
    private List<HashTag> hashTags;
    
    public Tweet() {
        this.insertedAt = new Date();
        this.likes = new ArrayList<>();
        this.hashTags = new ArrayList<>();
    }
    
    public Tweet(String text, User user){
        this.text = text;
        this.insertedAt = new Date();
        this.user = user;
        this.likes = new ArrayList<>();
        this.hashTags = new ArrayList<>();
        
        this.user.tweet(this);
    }
      
    public Tweet(String text, User user, Date insertedAt, List<User> likes, List<HashTag> hashTags){
        this.text = text;
        this.insertedAt = insertedAt;
        this.user = user;
        this.likes = likes;
        this.hashTags = hashTags;
        
        this.user.tweet(this);
    }
    
    public void like(User like)
    {
        if (!this.likes.contains(user)) {
            this.likes.add(like);
        } else {
            this.likes.remove(like);
        }
    }
    
//    public int getLikesCount() {
//        return this.likes.size();
//    }
}
