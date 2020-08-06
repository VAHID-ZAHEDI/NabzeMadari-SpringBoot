package com.example.api.pregnancy.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.List;

@DynamicUpdate
@Document(collection = "users")
@TypeAlias("users")
public class User extends MyAuditModel {

    //    @Transient
//    public static final String SEQUENCE_NAME = "users_sequence";

    @Field("phoneNumber")
    @Indexed(unique = true)
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String imagePath;
    private boolean isRegister;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    private String username;

    public User(String name, String username, String password, List<String> roles) {
        this.firstName = name;
        this.password = password;
        this.username = username;
    }


    public User() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        this.isRegister = register;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
