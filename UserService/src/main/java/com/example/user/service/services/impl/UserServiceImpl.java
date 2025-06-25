package com.example.user.service.services.impl;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.services.HotelService;
import com.example.user.service.repositories.UserRepository;
import com.example.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId); //setting using Setter method
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE or feign client, so that we can get ratings of user,
        // now it is showing "ratings":[] in response in postman
        return userRepo.findAll();
    }




    //Imppp - Implemented microservice communication
    //get single user
    @Override
    public User getUser(String userId) {
        //get user from database with the help  of user repository
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! : " + userId));

        // fetch rating of the given by or of above user from RATING-SERVICE
        //first see in Rating service do we have the method to get ratings by userId...ye we do..findBYUserId(userId)
        //so use its-same URL to get the ratings of the user - http://localhost:8083/ratings/user/{userId} => http://RATING-SERVICE/ratings/user/{userId}

        Rating[] ratingArray  =
                restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.asList(ratingArray);

        //for each rating, fetch its hotel data using hotelId and set it in rating object
       for(Rating rating : ratings) {
           // Use service name instead of localhost for load balancer - http://localhost:8082/hotels/ => http://HOTEL-SERVICE/hotels/

           //Using RestTemplate
           //Hotel hotel=restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);

           //Using Feign Client
              Hotel hotel = hotelService.getHotelById(rating.getHotelId());

              rating.setHotel(hotel); //setting hotel in rating object
       }

        //now set this ratings to user after setting hotel to each rating
        user.setRatings(ratings);
        return user;
    }
}
