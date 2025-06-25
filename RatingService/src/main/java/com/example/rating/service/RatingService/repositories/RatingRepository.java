package com.example.rating.service.RatingService.repositories;

import com.example.rating.service.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

//IMPPP - see using MongoRepository instead of JPA Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    //findByName() - fieldName from Rating.java should be in CamelCase - userId->UserId
    // custom finder methods - just like in Post API in BloggingApplication
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

//    IMPPPP-
//    ❌ Why not findByUser()?
//    Spring would look for a field named user in your Rating class — but it doesn’t exist.
//    And findByUserId() matches the field name exactly (userId) see Rating.java class
//    If instead your Rating clas had a User object like: private User user,
//    Then we can use: findByUser(User user)
}



