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
package common.exceptions;

import javax.ejb.ApplicationException;

/** 
 * User.Name is @unique, meaning it will throw an error if a {@link user} 
 * is persisted while the database contains another user with that name.
 *
 * @author Yannick
 */
@ApplicationException(rollback=true)
public class UniqueConstraintViolationException extends RuntimeException {
    
    public UniqueConstraintViolationException (String message, Throwable cause) {
        super(message, cause);
    }
    
    public UniqueConstraintViolationException (String message) {
        super(message);
    }
}
