package com.example.photoapp.api.userservice.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private Environment env;

    @GetMapping(path = "/status/check")
    public String getStatus() {
        return "OK: USERS-WS Service running on " + env.getProperty("local.server.port");
    }
}
