package com.example.mobileappws.userservice;

import com.example.mobileappws.model.User;
import com.example.mobileappws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Utils _utils;

    public UserServiceImpl() {}

    @Autowired
    public UserServiceImpl(Utils utils) {
        this._utils = utils;
    }

    Map<String, User> _users = new HashMap<String, User>();

    @Override
    public User createUser(User user) {
        String _id = _utils.generateUserId();
        User _user = new User();
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setEmail(user.getEmail());
        _user.setPassword(user.getPassword());
        _user.setUserId(_id);
        _users.put(_id, _user);
        return _user;
    }


    public String deleteUser(String userId) {
        if (_users.containsKey(userId)) {
            _users.remove(userId);
            return "UserId " + userId + " deleted successfully!";
        }
        else
            return "UserId " + userId + " doesn't exist!";

    }

    @Override
    public User updateUser(String userId, User updatedUser) {

        if (_users.containsKey(userId)) {
            User userBeingModified = _users.get(userId);
            if (updatedUser.getFirstName() != null) userBeingModified.setFirstName(updatedUser.getFirstName());
            if (updatedUser.getLastName() != null) userBeingModified.setLastName(updatedUser.getLastName());
            if (updatedUser.getEmail() != null) userBeingModified.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null) userBeingModified.setPassword(updatedUser.getPassword());
            _users.replace(userId, userBeingModified);
            return userBeingModified;
        }
        else
            return null;
    }

    @Override
    public Map getAllUsers() {
        return _users;
    }

    @Override
    public boolean hasUser(String userId) {
        if (_users.containsKey(userId)) return true;
        else
            return false;
    }
}
