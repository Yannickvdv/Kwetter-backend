/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author Yannick
 */
public class HashTag {
    
    private String text;
    private List<Tweet> HashTags;

    public HashTag(){
    }
    
    public HashTag(String text, List<Tweet> HashTags) {
        this.text = text;
        this.HashTags = HashTags;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tweet> getHashTags() {
        return HashTags;
    }

    public void setHashTags(List<Tweet> HashTags) {
        this.HashTags = HashTags;
    }
}
