package com.example.potion_smith_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(1)
    @Max(5)
    private int stars;


//    Based on ER diagram, a rating belongs to one user and one drink, but a user and a drink
//    can have many ratings. Therefore, we have a ManyToOne relationship between Rating and User,
//    and Rating and Drink.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    @JsonBackReference
    private Drink drink;


    public Rating() {
    }

    public Rating(int stars) {
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
}
