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
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String hashTagId;
    
    @Getter
    @Column(nullable = false, unique = true)
    private String text;
    
    @Getter
    @OneToMany
    @JsonbTransient
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
