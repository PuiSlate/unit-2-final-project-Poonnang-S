package com.example.potion_smith_backend.models;

import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stars;


//    Based on ER diagram, a rating belongs to one user and one drink, but a user and a drink
//    can have many ratings. Therefore, we have a ManyToOne relationship between Rating and User,
//    and Rating and Drink.
    @ManyToOne
    private User user;

    @ManyToOne
    private Drink drink;


    public Rating() {
    }

    public Rating(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
