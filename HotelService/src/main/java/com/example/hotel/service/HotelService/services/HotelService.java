package com.example.hotel.service.HotelService.services;

import com.example.hotel.service.HotelService.entities.Hotel;
import java.util.List;

public interface HotelService {

    //by default, all methods in an interface are public and abstract,

    //create
    Hotel createHotel(Hotel hotel);

    //get all
    List<Hotel> getAllHotels();

    //get single
    Hotel getHotelByID(String hotelId);
}
