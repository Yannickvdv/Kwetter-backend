/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {
    
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String kweetId;
    
    @Getter
    @Column(length = 140)
    private String text;
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    @Getter
    @Temporal(TemporalType.DATE)
    private Date insertedAt;
    
    @OneToMany
    private List<User> likes;   
    
    public Tweet() {
        this.likes = new ArrayList();
    }
    
    public Tweet(String text, User user){
        this.text = text;
        this.insertedAt = new Date();
        this.user = user;
        this.likes = new ArrayList();
        
        this.user.tweet(this);
    }
      
    public Tweet(String text, User user, Date insertedAt, List<User> likes){
        this.text = text;
        this.insertedAt = insertedAt;
        this.user = user;
        this.likes = likes;
        
        this.user.tweet(this);
    }
    
    public void like(User like)
    {
        if (!this.likes.contains(user)) {
            this.likes.add(like);
        } else {
            this.likes.remove(like);
        }
    }
    
    public int getLikesCount() {
        return this.likes.size();
    }
}
