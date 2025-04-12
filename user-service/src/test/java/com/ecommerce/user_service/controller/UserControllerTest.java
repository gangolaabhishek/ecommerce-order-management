package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.customexception.UserNotFoundException;
import com.ecommerce.user_service.entity.User;
import com.ecommerce.user_service.repositories.UserRepository;
import com.ecommerce.user_service.service.Service;
import com.ecommerce.user_service.serviceImpl.ServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ServiceImpl service;

    private User user;

    @BeforeEach
    void setup() {
        user = new User(1L, "Alice", "alice@example.com");
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void createUser(){
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = service.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("Alice",savedUser.getName());
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void testGetUserById(){
        when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        User foundUser = service.getUserById(1l);

        assertNotNull(foundUser);
        assertEquals("alice@example.com",foundUser.getEmail());
        verify(userRepository,times(1)).findById(1l);
    }

    @Test
    void testGetUserByIdNotFound(){
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(UserNotFoundException.class,()->{service.getUserById(2L);});

        assertFalse(ex.getMessage().contains("User not found"));
        verify(userRepository,times(1)).findById(2L);

    }

    @Test
    void testGetAllUsers(){
        List<User> users = Arrays.asList(
                new User(1L, "Alice", "alice@example.com"),
                new User(2L, "Bob", "bob@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = service.getAllUser();
        assertEquals(2,result.size());
        verify(userRepository,times(1)).findAll();
    }
}
