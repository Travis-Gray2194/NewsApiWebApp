package me.travisgray.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NewsFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String topic;


@ManyToMany(mappedBy = "news")
    private Set<User> user;



    public NewsFavorites() {
    }

    public NewsFavorites(String topic) {
        this.topic = topic;
        this.user = new HashSet<User>();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addUser (User u){
        this.user.add(u);
    }
}
