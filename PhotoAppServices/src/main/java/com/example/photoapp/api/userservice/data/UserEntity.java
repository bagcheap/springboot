package com.example.photoapp.api.userservice.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 8585157152767810503L;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 128, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String encryptedPassword;

    @Id
    @GeneratedValue
    private long id;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long databaseId) {
        this.id = databaseId;
    }
}
