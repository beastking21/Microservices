package com.microservices.userservice.controllers;

import com.microservices.userservice.entities.User;
import com.microservices.userservice.repositories.UserRepository;
import com.microservices.userservice.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) {
        String userId = loginData.get("username");
    String password = loginData.get("password");
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(token);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
