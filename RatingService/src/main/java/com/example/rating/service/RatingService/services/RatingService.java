package com.example.rating.service.RatingService.services;

import com.example.rating.service.RatingService.entities.Rating;
import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //IMPPP-
    //get all ratings by user id
    List<Rating> getRatingsByUserId(String userId);

    //IMPPP-
    //get all ratings by hotel id
    List<Rating> getRatingsByHotelId(String hotelId);
}
