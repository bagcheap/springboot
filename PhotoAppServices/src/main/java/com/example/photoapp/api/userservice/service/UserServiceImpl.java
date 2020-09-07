package com.example.photoapp.api.userservice.service;

import com.example.photoapp.api.userservice.data.UserEntity;
import com.example.photoapp.api.userservice.data.UserRepository;
import com.example.photoapp.api.userservice.models.CreateUserRequestModel;
import com.example.photoapp.api.userservice.models.CreateUserResponseModel;
import com.example.photoapp.api.userservice.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword("TEST");
        userRepository.save(userEntity);

        UserDto userCreated = modelMapper.map(userEntity, UserDto.class);

        return userCreated;
    }
}
