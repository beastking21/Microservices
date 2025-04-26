package com.microservices.userservice.services;

import java.util.List;

import com.microservices.userservice.entities.User;

public interface UserService {
//create
User saveUser(User user);
//read
User getUserById(String userId);
//update
User updateUser(User user);
//delete
void deleteUser(String userId);
//read all users
List<User> getAllUsers();
}
