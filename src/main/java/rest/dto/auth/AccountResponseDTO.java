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
package rest.dto.auth;

import domain.User;
import domain.enums.Role;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Yannick
 */
@Getter
@Setter
public class AccountResponseDTO {

    private String uuid;
    private String name;
    private Role role;
    private String token;
    private Instant expiryDate;

    public AccountResponseDTO(User user) {
        this.uuid = user.getUuid();
        this.name = user.getName();
        this.role = user.getRole();
        this.token = user.getJwt().getToken();
        this.expiryDate = user.getJwt().getExpiryDate();
    }
}
