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
package dao.auth;

import dao.helpers.JPAResultHelper;
import domain.auth.JWT;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yannick
 */
@Stateless
@Default
public class JwtDaoJPA {

    @PersistenceContext
    private EntityManager em;

    public void createJwt(JWT jwt) {
        this.em.persist(jwt);
    }

    public void editJWT(JWT jwt) {
        this.em.merge(jwt);
    }

    public void remove(JWT jwt) {
        this.em.remove(this.em.merge(jwt));
    }
    
    public Boolean tokenExists(String token) {
    return JPAResultHelper.getSingleResult(
            this.em.createNamedQuery("JWT.getJWT", JWT.class)
                    .setParameter("token", token)) != null;
    }

}
