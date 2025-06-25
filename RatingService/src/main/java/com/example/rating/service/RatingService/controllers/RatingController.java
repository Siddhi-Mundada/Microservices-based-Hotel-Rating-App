package com.example.rating.service.RatingService.controllers;

import com.example.rating.service.RatingService.entities.Rating;
import com.example.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;


    //Only users who have the Admin authority are allowed to create ratings
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
    }

    //get all
    @GetMapping("/ratings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    //IMPPP- see the URL, How its written
    //ratings given by a user

    // internal scope - means only specific clients (like internal microservices) can call them
    // or if he is Admin then only can access this API
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    //IMPPP-
    //ratings given to a hotel - see the URL
    @GetMapping("/ratings/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }

}
