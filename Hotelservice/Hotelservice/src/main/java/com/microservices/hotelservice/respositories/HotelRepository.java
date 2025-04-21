package com.microservices.hotelservice.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.hotelservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
