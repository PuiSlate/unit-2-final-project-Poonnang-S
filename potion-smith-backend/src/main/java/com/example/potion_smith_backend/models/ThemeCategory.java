package com.example.potion_smith_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ThemeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String title;

//    One ThemeCategory can have many drinks
    @OneToMany (mappedBy = "themeCategory", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Drink> drinks = new ArrayList<>();

    public ThemeCategory() {};


    public ThemeCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
