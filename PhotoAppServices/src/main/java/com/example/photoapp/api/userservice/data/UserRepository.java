package com.example.photoapp.api.userservice.data;

import com.example.photoapp.api.userservice.shared.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <UserEntity, Long> {
    UserEntity findByEmail(String email); //always findBy<col_name>
}
