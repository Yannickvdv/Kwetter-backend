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
package domain.helpers;

import domain.User;
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
    
    /**
     * Search a specific text for hashtags (#)
     * 
     * @param text The text to be searched through
     * @return Returns a list of hashtags as strings
     */
    public static List<String> searchHashTags(String text){
       return TextHelper.parse(text, "#");
    }
    
    /**
     * Search a specific text for mentions (@)
     * 
     * @param text The text to be searched through
     * @return Returns a list of mentions as strings
     */
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
