package com.microservices.hotelservice.services;

import java.util.List;


import com.microservices.hotelservice.entities.Hotel;


public interface HotelService {
    //create hotel
    Hotel createHotel(Hotel hotel);
    //get hotel by id
    Hotel getHotelById(String id);
    //get all hotels
    List<Hotel> getAllHotels();
    //update hotel
    Hotel updateHotel(Hotel hotel);
    //delete hotel
    void deleteHotel(String id);
    }

