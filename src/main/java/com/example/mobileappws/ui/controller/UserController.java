package com.example.mobileappws.ui.controller;

import com.example.mobileappws.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    private static boolean DEBUG=true;

    Map<String, User> _users = new HashMap<String, User>();

    @GetMapping
    public ResponseEntity<Map<String, User>> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return new ResponseEntity<Map<String, User>>(_users, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        if(DEBUG) throw new NullPointerException();
        if (_users.containsKey(userId)) {
            return new ResponseEntity<>(_users.get(userId), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        String _id = UUID.randomUUID().toString();
        User _user = new User();
        _user.setFirstName(user.getFirstName());
        _user.setLastName(user.getLastName());
        _user.setEmail(user.getEmail());
        _user.setPassword(user.getPassword());
        _user.setUserId(_id);
        _users.put(_id, _user);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @PutMapping (path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        if (_users.containsKey(userId)) {
            User userBeingModified = _users.get(userId);
            if (user.getFirstName() != null) userBeingModified.setFirstName(user.getFirstName());
            if (user.getLastName() != null) userBeingModified.setLastName(user.getLastName());
            if (user.getEmail() != null) userBeingModified.setEmail(user.getEmail());
            if (user.getPassword() != null) userBeingModified.setPassword(user.getPassword());
            _users.replace(userId, userBeingModified);
            return new ResponseEntity<>(_users.get(userId), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping (path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        if (_users.containsKey(userId)) {
            _users.remove(userId);
            return new ResponseEntity<String>("Deleted user with ID " + userId, HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("User ID " + userId + " not found", HttpStatus.OK);
    }

}
