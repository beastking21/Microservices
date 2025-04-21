package com.microservices.ratingservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.ratingservice.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String>{
    //custom methods for getting data using userId and hotelId
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
