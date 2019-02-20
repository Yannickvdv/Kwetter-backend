/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yannick
 */
public class HashTag {
    
    private String text;
    private List<Tweet> tweets;

    public HashTag(){
    }
    
    public HashTag(String text, Tweet tweet){
        this.text = text;
        this.tweets = Arrays.asList(tweet);
    }
    
    public HashTag(String text, List<Tweet> HashTags) {
        this.text = text;
        this.tweets = HashTags;
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
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tweet> getHashTags() {
        return tweets;
    }

    public void setHashTags(List<Tweet> HashTags) {
        this.tweets = HashTags;
    }
}
