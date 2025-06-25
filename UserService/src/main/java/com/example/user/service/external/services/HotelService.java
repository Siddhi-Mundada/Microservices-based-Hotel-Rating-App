package com.example.user.service.external.services;

import com.example.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="HOTEL-SERVICE")
public interface HotelService {

    //Declarative approach

    //advantage of feign client - no need to write the implementation of methods
    //this URL should be same as in Hotel-Service controller URL
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);

}





