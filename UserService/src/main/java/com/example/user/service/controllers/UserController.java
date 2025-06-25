package com.example.user.service.controllers;

import com.example.user.service.entities.User;
import com.example.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
         return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }


    //Fault Tolerance implemented as this service impl has dependency on other service

    //this service is calling Rating service & RatingService calling Hotel Service so implement Fault Tolerance here
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUser(userId)); // userService.getUser(userId)) - see impl has dependency
    }

    //creating fall back  method for circuitbreaker
    //this method will run if Rating service or Hotel Service fails

    //fallbackMethod - ratingHotelFallback
    //Imppp- return type and params of method - same as @CircuitBreaker method return type
    // i.e. - ResponseEntity<User>, userId along with exception object
   public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
   {
       ex.printStackTrace();

       User user =new User("565668","Rating Service failed so called this fallback method","dummy@gmail","dummy");
       return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
   }


}
