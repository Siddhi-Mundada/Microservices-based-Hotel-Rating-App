package com.example.user.service.external.services;

import com.example.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="RATING-SERVICE") // name of the service in eureka server
public interface RatingService {

    //URLS should be same as in Rating-Service controller urls

    //create rating
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(@RequestBody Rating rating);

    //update rating
    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@RequestBody Rating rating, @PathVariable String ratingId);

    //delete rating
    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);
}


