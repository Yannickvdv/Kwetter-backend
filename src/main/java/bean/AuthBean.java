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

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Yannick
 */
@Named
@SessionScoped
public class AuthBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthBean.class);

    @Inject
    private FacesContext facesContext;
    @Inject
    private ExternalContext externalContext;
    @Inject
    private SecurityContext securityContext;

    /**
     * Validate the username and password
     *
     * @param username
     * @param password
     *
     * @return
     */
    private AuthenticationStatus authenticate(String username, String password) {
        return this.securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password))
        );
    }

    /**
     * Try to login the given user
     *
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        switch (authenticate((String) username, (String) password)) {
            case SEND_CONTINUE:
                this.facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                this.facesContext.addMessage(null,
                                             new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            case SUCCESS:
                this.facesContext.addMessage(null,
                                             new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                try {
                    this.externalContext.redirect(this.externalContext.getRequestContextPath() + "/index.xhtml");
                } catch (IOException ex) {
                    AuthBean.LOGGER.warn("Cannot redirect to /index.xhtml");
                }
                break;
            case NOT_DONE:
                this.facesContext.addMessage(null,
                                             new FacesMessage(FacesMessage.SEVERITY_INFO, "Login NOT_DONE", null));
                break;
        }
    }

    /**
     * Logout the current user
     *
     * @return The redirect page
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}

