package com.ecommerce.user_service.serviceImpl;

import com.ecommerce.user_service.customexception.DuplicateUserException;
import com.ecommerce.user_service.customexception.UserNotFoundException;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.repositories.UserRepository;
import com.ecommerce.user_service.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userRepository.existsByEmail(user.getEmail())){
            throw new DuplicateUserException("User Already Exist with email: "+user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long id) {
//        return  userRepository.findById(id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user with provided id"));
    }

    @Override
    public List<User> getAllUser() {
        return  userRepository.findAll();
    }


}
