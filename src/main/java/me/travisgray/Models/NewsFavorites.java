package me.travisgray.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NewsFavorites {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    private String favorites;

@ManyToMany(mappedBy = "newsfavorites")
    private Set<User> user;

    public NewsFavorites(String favorites) {
        this.favorites = favorites;
        this.user = new HashSet<User>();
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public void addUser (User u){
        this.user.add(u);
    }
}
