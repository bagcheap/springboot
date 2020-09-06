package com.example.photoapp.accountservice.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    @Autowired
    private Environment env;

    @GetMapping(path = "/status/check")
    public String getStatus(){
        return "OK: ACCOUNT-WS Service running on " + env.getProperty("local.server.port");
    }
}
