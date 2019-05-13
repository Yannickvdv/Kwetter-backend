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
package service;

import common.exceptions.InvalidCredentialsException;
import domain.User;
import domain.auth.JWT;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;
import rest.dto.auth.AccountCreateDTO;
import rest.dto.auth.AccountResponseDTO;

/**
 *
 * @author Yannick
 */
@Stateless
public class AuthenticationService {

    @Inject
    private UserService userService;

    @Inject
    private JWTService jwtService;

    /**
     * Generate a new JWT
     *
     * @return The generated JWT
     */
    private JWT generateJWT() {
        Instant issueDate = Instant.now();
        Instant expiryDate = issueDate.plus(Duration.ofMinutes(30));

        String token = Jwts.builder()
                .setIssuer("Kwetter")
                .setIssuedAt(Date.from(issueDate))
                .setExpiration(Date.from(expiryDate))
                .claim("scope", "self api/*")
                .signWith(KeyGenerator.getSIGNATURE_ALGORITHM(), KeyGenerator.getKEY())
                .compact();

        return new JWT(token, issueDate, expiryDate);
    }

    /**
     * Register a new Account.
     *
     * @param dto The DTO containing the credentials for the new Account.
     * @return The DTO containing the email address.
     */
    public AccountResponseDTO register(AccountCreateDTO dto) {
        User newUser = new User(dto.getName(), dto.getPassword(), dto.getLanguage());
        
        JWT generatedJwt = generateJWT();
        this.jwtService.create(generatedJwt);

        newUser.setJwt(generatedJwt);
        this.userService.addUser(newUser);

        return new AccountResponseDTO(newUser);
    }

    /**
     * Login using credentials.
     *
     * @param name The username.
     * @param password The password.
     * @return The corresponding Account.
     */
    public AccountResponseDTO login(String name, String password) throws InvalidCredentialsException {
        User entity = this.userService.findByName(name);

        if (entity == null || !entity.getPassword().equals(password)) {
            throw new InvalidCredentialsException();
        }

        JWT jwt = entity.getJwt();

        boolean hasToken = jwt != null;
        boolean isTokenExpired = hasToken && jwt.getExpiryDate().isBefore(Instant.now());
        
        // Check if a token already exists and if it has expired. If it has, remove it
        if (hasToken) {
            if (!isTokenExpired) {
                System.out.println(entity);
                return new AccountResponseDTO(entity);
            }
             
            this.jwtService.remove(jwt);
        }

        //Generate new token
        JWT generatedJwt = generateJWT();
        this.jwtService.create(generatedJwt);
        entity.setJwt(generatedJwt);

        //Edit user and return user data
        this.userService.editUser(entity);
        User updatedEntity = this.userService.getUser(entity.getUuid());
        return new AccountResponseDTO(updatedEntity);
    }
}
