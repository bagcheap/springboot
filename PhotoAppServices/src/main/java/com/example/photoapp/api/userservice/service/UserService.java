package com.example.photoapp.api.userservice.service;

import com.example.photoapp.api.userservice.models.CreateUserRequestModel;
import com.example.photoapp.api.userservice.shared.UserDto;

public interface UserService {
    default UserDto createUser(UserDto user) {
        return null;
    }
}
