package com.example.potion_smith_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String age;

//    One user can have many comments
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rating> ratings = new ArrayList<>();


    public User() {};

    public User(String username, String email, String age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
