package com.microservices.hotelservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hotelservice.entities.Hotel;
import com.microservices.hotelservice.exceptions.ResourceNotFoundException;
import com.microservices.hotelservice.respositories.HotelRepository;
import com.microservices.hotelservice.services.HotelService;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String HotelId = UUID.randomUUID().toString();
        hotel.setId(HotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotel(String id) {
        
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no resource found for given id "+id));
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        // TODO Auto-generated method stub
        return null;
    }

}
