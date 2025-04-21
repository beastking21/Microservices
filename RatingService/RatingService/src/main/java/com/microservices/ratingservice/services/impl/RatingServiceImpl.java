package com.microservices.ratingservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.ratingservice.entities.Rating;
import com.microservices.ratingservice.repositories.RatingRepository;
import com.microservices.ratingservice.services.RatingService;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository repository;
    @Override
    public Rating createRating(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return repository.findByUserId(userId);
    }


}
