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
package dao.hashtag;

import domain.HashTag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.*;
/**
 *
 * @author Yannick
 */
@Stateless @Default
public class HashTagDaoJPA implements HashTagDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        System.out.println("---HashTagDaoJPA Initialized");
    }
    
    @Override
    public List<HashTag> getHashTags() {
       Query query = em.createNamedQuery("hashTag.getHashTags", HashTag.class);
       return new ArrayList<>(query.getResultList());
    }

    @Override
    public HashTag findByName(String name) {
        TypedQuery<HashTag> query = em.createNamedQuery("hashTag.findByName", HashTag.class);
        query.setParameter("name", name);
        List<HashTag> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public void addHashTag(HashTag hashTag) {
        em.persist(hashTag);
    }
    
    public int amount () {
        return getHashTags().size();
    }
    
    /**
     * Set the entity manager of HashTagDaoJPA
     * 
     * @param em The entity manager to be set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
