package com.example.potion_smith_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    @JsonManagedReference
    private Drink drink;

    public Favorite() {
    }

    public Favorite(User user, Drink drink) {
        this.user = user;
        this.drink = drink;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    // Convenience getters for IDs if needed
    public int getUserId() {
        return user != null ? user.getId() : 0;
    }

    public int getDrinkId() {
        return drink != null ? drink.getId() : 0;
    }

}