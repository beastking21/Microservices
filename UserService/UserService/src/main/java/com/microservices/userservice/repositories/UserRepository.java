package com.microservices.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
