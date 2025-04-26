package com.microservices.userservice.External;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.userservice.entities.Hotel;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId")String hotelId);
}
