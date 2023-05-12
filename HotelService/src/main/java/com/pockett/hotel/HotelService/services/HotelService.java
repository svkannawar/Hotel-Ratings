package com.pockett.hotel.HotelService.services;

import com.pockett.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(Hotel hotel);


    //getAll
    List<Hotel> getAllHotels();


    //getSingle
    Hotel getHotel(String id);


}
