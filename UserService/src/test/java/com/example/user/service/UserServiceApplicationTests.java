package com.example.user.service;

import com.example.user.service.entities.Rating;
import com.example.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
    void createRating() {
        Rating rating =new Rating("","","",5,"hhshhshs");
        ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
    }
}
