package com.ecommerce.user_service.service;

import com.ecommerce.user_service.entity.User;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface Service {
    User registerUser(User user);

    Optional<User> getUserByEmail(String email);

    User getUserById(Long id);;

    List<User> getAllUser();

}
