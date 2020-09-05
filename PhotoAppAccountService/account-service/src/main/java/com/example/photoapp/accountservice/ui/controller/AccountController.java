package com.example.photoapp.accountservice.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    @GetMapping(path = "/status/check")
    public String getStatus(){
        return "OK: Account Service";
    }
}
