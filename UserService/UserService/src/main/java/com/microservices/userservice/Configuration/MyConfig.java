package com.microservices.userservice.Configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration //to declare that beans will be here
public class MyConfig {
    @Bean
    @LoadBalanced //this will implement namebased load balancing instead of ip and port in userServiceImpl
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //jwt
    @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
