package com.microservices.userservice.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.userservice.entities.User;
import com.microservices.userservice.services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //int retryCount=1;
    //get user by id 
    //this method calls other services hence circuitbreaker will be implemented here
    @GetMapping("/{userid}")
    //@CircuitBreaker(name="ratingHotelBreaker" , fallbackMethod = "ratingHotelFallback") 
   // @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
   @RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String userid){
        User user=userService.getUserById(userid);
        // log.info("Retry count: "+ retryCount);
        // retryCount++;
        return ResponseEntity.ok(user);
    }
//this is fallbackmethod type and arguments should be similar
    public ResponseEntity<User> ratingHotelFallback(String userid, Exception ex){
        log.info("This is fallback method, some service is down: "+ ex);
        User user= User.builder()
                       .email("dummy@abc.com")
                       .about("this is dummy user")
                       .name("Dummy").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
