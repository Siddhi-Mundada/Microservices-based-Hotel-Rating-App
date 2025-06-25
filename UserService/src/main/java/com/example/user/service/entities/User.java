package com.example.user.service.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_users")
public class User {

    //not auto generating the ID
    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20) //varChar(20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;


    //ratings given by user to get in postman response
    @Transient //to not store this column in db
    private List<Rating> ratings=new ArrayList<>();






    //getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User() {
    }

    public User(String userId, List<Rating> ratings, String about, String email, String name) {
        this.userId = userId;
        this.ratings = ratings;
        this.about = about;
        this.email = email;
        this.name = name;
    }

    public User(String userId, String about, String email, String name) {
        this.userId = userId;
        this.about = about;
        this.email = email;
        this.name = name;
    }
}

