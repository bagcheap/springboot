package com.example.mobileappws.ui.controller;

import com.example.mobileappws.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    Map<String, User> _users = new HashMap<String, User>();

    @GetMapping
    public ResponseEntity<Map<String, User>> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return new ResponseEntity<Map<String, User>>(_users, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable String userId) {
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
        _user.setUserId(_id);
        _users.put(_id, _user);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "updateUser was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser was called";
    }

}
