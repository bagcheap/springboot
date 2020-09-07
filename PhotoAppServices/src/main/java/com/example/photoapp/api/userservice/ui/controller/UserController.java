package com.example.photoapp.api.userservice.ui.controller;

import com.example.photoapp.api.userservice.models.CreateUserRequestModel;
import com.example.photoapp.api.userservice.service.UserService;
import com.example.photoapp.api.userservice.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    @GetMapping(path = "/status/check")
    public String getStatus() {
        return "OK: USERS-WS Service running on " + env.getProperty("local.server.port");
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid CreateUserRequestModel userDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userService.createUser(userDto);
        return userDto;
    }
}
