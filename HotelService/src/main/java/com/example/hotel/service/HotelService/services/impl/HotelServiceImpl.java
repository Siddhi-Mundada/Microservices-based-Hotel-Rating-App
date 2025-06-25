package com.example.hotel.service.HotelService.services.impl;

import com.example.hotel.service.HotelService.entities.Hotel;
import com.example.hotel.service.HotelService.exceptions.ResourceNotFoundException;
import com.example.hotel.service.HotelService.repositories.HotelRepository;
import com.example.hotel.service.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelByID(String hotelId) {
         Hotel hotel = hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found !!"));
        return hotel;
    }
}
