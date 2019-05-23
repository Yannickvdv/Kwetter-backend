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
