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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "hashtags")
@NamedQueries({
    @NamedQuery(name = "hashTag.getHashTags", query = "SELECT h FROM HashTag h"),
    @NamedQuery(name = "hashTag.findByName", query = "SELECT h FROM HashTag h WHERE h.text = :name"),
    @NamedQuery(name = "hashTag.findTrends", query = "SELECT h.text, COUNT(h.tweets) AS amount_of_tweets FROM HashTag h")})
public class HashTag implements Serializable {
    
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String uuid;
    
    @Getter
    @Column(nullable = false, unique = true)
    private String text;
    
    @Getter
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "hashtag_has_tweets",
               joinColumns = @JoinColumn(name = "hashtags_hashtag", referencedColumnName = "uuid"),
               inverseJoinColumns = @JoinColumn(name = "hashtags_tweet", referencedColumnName = "uuid"))
    private final Set<Tweet> tweets;


    public HashTag(){
        this.text = "";
        this.tweets = new HashSet<>();
    }
    
    public HashTag(String text, Tweet tweet){
        this.text = text;
        this.tweets = new HashSet<>(Arrays.asList(tweet));
    }
    
    
    /**
     * If a {@link Tweet} uses this {@link HashTag}, add it to the list
     * 
     * @param tweet The {@link Tweet} to add to the {@link HashTag}
     */
    public void addTweet(Tweet tweet) {
        if(!this.tweets.contains(tweet)){
            this.tweets.add(tweet);
            tweet.addHashTag(this);
        }
    }
}
