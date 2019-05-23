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
package dao.helpers;

import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Yannick
 */
public class JPAResultHelper {
    
    
    //Source: Mark H. Slaats
    /**
     * Because the JPA getSingelResult throws errors when it is not able to find
     * objects, I created this method which tries to get a single result and if
     * it does not get a single result it returns {@code null}
     * <p>
     * Source: https://stackoverflow.com/a/2003015
     *
     * @param <T>
     * @param query
     *
     * @return
     */
    public static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);

        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException ex) {
            System.out.println("WARNING JPAResultHelper: This should be unreachable due to query.setMaxResults(1)");
        } catch (IllegalStateException | PersistenceException ex) {
            // Ignore this Exception since it has something to do with an empty result
        } catch (Exception ex) {
            System.out.println("WARNING JPAResultHelper: Caught a general exception");
        }
        return null;
    }

}
