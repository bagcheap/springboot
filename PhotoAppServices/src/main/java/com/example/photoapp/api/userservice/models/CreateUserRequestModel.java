package com.example.photoapp.api.userservice.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
    @NotNull (message = "firstname cannot be null")
    @Size (min = 2, message = "Must have at least 2 chars")
    private String firstname;
    @NotNull (message = "lastname cannot be null")
    private String lastname;
    @Email (message = "Must be a valid email address")
    private String email;
    @NotNull (message = "Password cannot be null")
    @Size (min = 8, max = 24, message = "Must be between 8 and 24 characters")
    private String password;
    private String userId;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
