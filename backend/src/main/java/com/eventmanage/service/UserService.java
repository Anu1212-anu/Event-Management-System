package com.eventmanage.service;

import java.util.List;

import com.eventmanage.entity.User;


public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User login(String email, String password);

}
