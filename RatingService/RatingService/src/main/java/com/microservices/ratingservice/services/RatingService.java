package com.microservices.ratingservice.services;

import java.util.List;

import com.microservices.ratingservice.entities.Rating;

public interface RatingService {
    //create
    Rating createRating(Rating rating);
    //get all ratings
    List<Rating> getAllRatings();
    //get by userId
    List<Rating> getRatingsByUserId(String userId);
    //get by hotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
