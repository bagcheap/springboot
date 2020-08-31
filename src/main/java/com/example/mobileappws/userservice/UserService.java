package com.example.mobileappws.userservice;

import com.example.mobileappws.model.User;

import java.util.Map;

public interface UserService {
    User createUser(User user);
    String deleteUser(String userId);
    User updateUser(String userId, User updatedUser);
    Map getAllUsers();
}
