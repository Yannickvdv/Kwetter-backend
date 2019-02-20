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
    
    public static List<String> searchHashTags(String text){
       return TextHelper.parse(text, "#");
    }
    
    public static List<String> searchMentions(String text){
       return TextHelper.parse(text, "@");
    }
    
    /**
     * Search a list of mentioned {@link User} objects in a given text
     * 
     * @param text The text to be searched for mentions
     * @param users The users to be potentially mentioned
     * @return Array of {@link user} objects that were mentioned 
     */
    public static List<User> searchMentionedUsers(String text, List<User> users){
       List<String> mentionTexts = TextHelper.searchMentions(text);
       
       List<User> mentioned = new ArrayList<>();
       mentionTexts.forEach((String s) -> {
           users.forEach((User u) -> {
               if(u.getName().equals(s)){
                   mentioned.add(u);
               }
           });
       });
       
       return mentioned;
    }
    
    /**
     * Loop through text split by whitespace and filter substrings starting with the given key
     * 
     * @param text The text to be searched through
     * @param key The key to be searched for
     * @return A list of strings containing the substrings matching the key
     */
    public static List<String> parse(String text, String key) {
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
