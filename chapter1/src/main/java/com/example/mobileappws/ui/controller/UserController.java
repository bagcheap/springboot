package com.example.mobileappws.ui.controller;

import com.example.mobileappws.model.User;
import com.example.mobileappws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static boolean DEBUG=false;

//    Map<String, User> _users = new HashMap<String, User>();

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, User>> getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        Map allUsers = userService.getAllUsers();
        if (allUsers == null || allUsers.size() < 1)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        if(DEBUG) throw new NullPointerException();
        Map allUsers = userService.getAllUsers();
        if (allUsers.containsKey(userId)) {
            return new ResponseEntity<>((User)allUsers.get(userId), HttpStatus.OK);
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
        User _user = userService.createUser(user);
        return new ResponseEntity<>(_user, HttpStatus.OK);
    }

    @PutMapping (path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {

        if (!userService.hasUser(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User userModified = userService.updateUser(userId, user);
        if (userModified != null) {
            return new ResponseEntity<>(userModified, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping (path = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {

        if (!userService.hasUser(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<String>(userService.deleteUser(userId), HttpStatus.OK);
    }

}
