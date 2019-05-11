/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.helpers.TextHelper;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class TextHelperTest {
    
    private TextHelper textHelper;
    
    public TextHelperTest() {
        this.textHelper = new TextHelper();
    }
   
    @Test
    public void testSearchHashTag() {
        List expectedHashTags = Arrays.asList("Sweets");
        List resultHashTags = this.textHelper.searchHashTags("Hello, I like #Sweets");
        assertEquals(expectedHashTags, resultHashTags);
        
        List expectedMultipleHashTags = Arrays.asList("Homework", "NoFilter", "AllNatural");
        List resultMultipleHashTags = this.textHelper.searchHashTags("Good day to do #Homework while #NoFilter and #AllNatural");
        assertEquals(expectedMultipleHashTags, resultMultipleHashTags);
    }
    
    @Test
    public void testSearchMentions() {
        List expectedMentions = Arrays.asList("Bart");
        List resultMentions = this.textHelper.searchMentions("Hello there @Bart");
        assertEquals(expectedMentions, resultMentions);
        
        List expectedMultipleMentions = Arrays.asList("DonaldJTrump", "Danny", "Taylor");
        List resultMultipleMentions = this.textHelper.searchMentions("Good day to @DonaldJTrump while @Danny and @Taylor");
        assertEquals(expectedMultipleMentions, resultMultipleMentions);
    }
}
