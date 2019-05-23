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
package bean;

import domain.User;
import domain.enums.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

/**
 *
 * @author Yannick
 */
@Named("userBean")
@RequestScoped
public class UserBean implements Serializable{
    
    @Inject
    private UserService userService;
    
    public List<User> getUsers(String username) {
        if (username == null || username.isEmpty())
            return this.userService.getUsers();
        else
            return new ArrayList<>(Arrays.asList(this.userService.findByName(username)));
    }

    public void updateUserRole(User user, String role) {
        user.setRole(Role.valueOf(role));
        this.userService.editUser(user);
    }
}
