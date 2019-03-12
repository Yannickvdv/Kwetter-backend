/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "hashtags")
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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "hashtag_has_tweets",
            joinColumns = @JoinColumn(name = "hashtag_uuid", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "tweet_uuid", referencedColumnName = "uuid"))
    private List<Tweet> tweets;

    public HashTag(){
        this.text = "";
        this.tweets = new ArrayList<>();
    }
    
    public HashTag(String text, Tweet tweet){
        this.text = text;
        this.tweets = Arrays.asList(tweet);
    }
    
    
    /**
     * If a {@link Tweet} uses this {@link HashTag}, add it to the list
     * 
     * @param tweet The {@link Tweet} to add to the {@link HashTag}
     */
    public void addTweet(Tweet tweet) {
        if(!this.tweets.contains(tweet)){
            this.tweets.add(tweet);
        }
    }
}
