/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Yannick
 */
public class TextHelper {
    
    public TextHelper(){
    }
    
    public List<String> searchHashTag(String text){
       return this.parse(text, "#");
    }
    
    public List<String> searchMentions(String text){
       return this.parse(text, "@");
    }
    
    public List<String> parse(String text, String key) {
        List<String> substrings = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith(key)) {
                substrings.add(token.substring(1));
            }
        }
        return substrings;
    }
}
