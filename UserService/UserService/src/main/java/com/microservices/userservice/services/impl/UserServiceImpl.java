package com.microservices.userservice.services.impl;



import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.userservice.External.HotelService;
import com.microservices.userservice.entities.Hotel;
import com.microservices.userservice.entities.Rating;
import com.microservices.userservice.entities.User;
import com.microservices.userservice.repositories.UserRepository;
import com.microservices.userservice.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);//for logging
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    //resttemplate to communicate with other services
    @Autowired
    private RestTemplate restTemplate; //need to create bean of resttemplate to autowired

    //private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);//for logging
    @Autowired
    private HotelService hotelService;

    //jwt
    @Autowired
private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        System.out.println("Incoming password: " + user.getPassword());

        //jwt
        String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        User users= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given userid not found "+userId));
        //fetching records from rating service for this userid
        //localhost:8083/ratings/users/f98ecf1b-8443-4d50-8497-91fbbc4abbc9
        //String ratingsUrl="http://localhost:8083/ratings/users/"+userId;
        String ratingsUrl="http://RATINGSERVICE/ratings/users/"+userId;
       
       // ArrayList<Rating> userRating=restTemplate.getForObject(ratingsUrl, ArrayList.class);
       Rating [] userRating=restTemplate.getForObject(ratingsUrl, Rating [].class);
       List<Rating> ratings =Arrays.stream(userRating).toList();

       List<Rating> ratingList =ratings.stream().map(rating -> {
        //api to call hotelservice to get hotel
        //localhost:8082/hotels/9d2bc07d-98c2-4301-b1b1-91cce1c39ea8
        System.out.println("hotel id: "+rating.getHotelId());
        //ResponseEntity<Hotel> forHotel = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
        
        //ResponseEntity<Hotel> forHotel = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
        //Hotel hotel =forHotel.getBody();
        //System.out.println("hotel : "+hotel);
       //log.info("response status code: {}",forHotel.getStatusCode());


       Hotel hotel =hotelService.getHotel(rating.getHotelId()); //using feign client
        //setting hotel to rating
        rating.setHotel(hotel);
        System.out.println("hotel rating: "+rating);
        //returning rating
        return rating;
       }).collect(Collectors.toList());
        log.info("{}",ratingList);
       // System.out.println("abcdefg "+Arrays.toString(ratingList.toArray()));
        users.setRatings(ratingList);
        return users;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
