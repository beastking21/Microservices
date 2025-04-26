package com.microservices.userservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int ratings;
    private String feedBack;
    //to get data from hotelId
    private Hotel hotel;

}
