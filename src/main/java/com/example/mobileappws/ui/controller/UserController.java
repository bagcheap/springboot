package com.example.mobileappws.ui.controller;

import com.example.mobileappws.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return "getUser with page= " + page + " and limit=" + limit + " was called";
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user = new User();
        user.setFirstName("Angus");
        user.setEmail("spare_brain@yahoo.com");
        user.setLastName("Bagchee");
        user.setUserId("123");
        return new ResponseEntity<User>(user, HttpStatus.OK);
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
        return new ResponseEntity<User>(user, HttpStatus.OK);
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
