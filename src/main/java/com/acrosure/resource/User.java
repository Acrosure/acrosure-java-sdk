package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String id;
    private String email;
    private String firstNameTh;
    private String lastNameTh;
    private String role;

    @JsonCreator
    public User(
            @JsonProperty("id") String id,
            @JsonProperty("email") String email,
            @JsonProperty("first_name_th") String firstNameTh,
            @JsonProperty("last_name_th") String lastNameTh,
            @JsonProperty("role") String role) {
        this.id = id;
        this.email = email;
        this.firstNameTh = firstNameTh;
        this.lastNameTh = lastNameTh;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstNameTh() {
        return firstNameTh;
    }

    public String getLastNameTh() {
        return lastNameTh;
    }

    public String getRole() {
        return role;
    }
}
