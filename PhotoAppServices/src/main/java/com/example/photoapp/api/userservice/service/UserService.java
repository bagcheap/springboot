package com.example.photoapp.api.userservice.service;

import com.example.photoapp.api.userservice.models.CreateUserRequestModel;
import com.example.photoapp.api.userservice.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUserDetailsByEmail(String email);
}
