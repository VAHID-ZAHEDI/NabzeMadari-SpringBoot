package com.example.api.pregnancy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DynamicUpdate
@Document(collection = "users")
@TypeAlias("users")
public class User extends MyAuditModel {

    @NotBlank
    @Field("phoneNumber")
    @Indexed(unique = true)
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String imagePath;
    private boolean isRegister;
    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User(@NotBlank String phoneNumber, String password, String firstName, String lastName, boolean isRegister) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isRegister = isRegister;
        this.password = password;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
