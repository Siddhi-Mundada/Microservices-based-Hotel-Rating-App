package com.example.hotel.service.HotelService.controllers;

import com.example.hotel.service.HotelService.entities.Hotel;
import com.example.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;


    //Only an Admin can create new hotels
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<Hotel>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }


    //get single hotel

    //Only users (or services) with the scope internal are allowed to fetch the details of a single hotel
    //means only Internal microservices like User or Rating can call this api endpoint
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.getHotelByID(id));
    }


    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

}
