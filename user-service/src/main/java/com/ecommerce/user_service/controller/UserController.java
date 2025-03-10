package com.ecommerce.user_service.controller;


import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private Service service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        this.service.registerUser(user);
        String res = "User Added";
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(this.service.getUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getUserById(id), HttpStatus.OK);
    }
}
