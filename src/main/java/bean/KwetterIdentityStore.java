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
import java.util.Arrays;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import service.UserService;

/**
 *
 * @author Yannick
 */
@ApplicationScoped
public class KwetterIdentityStore implements IdentityStore {

    @Inject
    private UserService userService;

    /**
     * Check the credentials using the database
     *
     * @param credential
     *
     * @return The username and it's role
     */
    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        User user = userService.getUser(login.getCaller());
        if (user != null
            && user.getPassword().equals(login.getPasswordAsString()))
            return new CredentialValidationResult(user.getName(), new HashSet<>(Arrays.asList(user.getRole().toString())));
        return CredentialValidationResult.INVALID_RESULT;
    }
}
