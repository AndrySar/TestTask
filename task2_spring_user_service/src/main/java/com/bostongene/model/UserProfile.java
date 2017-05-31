package com.bostongene.model;

import com.bostongene.jackson.model.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andry on 29.05.17.
 */
public class UserProfile {

    @JsonProperty
    private String surname;

    @JsonProperty
    private String username;

    @JsonProperty
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dateOfBirth;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @SuppressWarnings("unused")
    public UserProfile() {
    }

    public UserProfile(String surname, String username, Date dateOfBirth, String email, String password) {
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    // Getter

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public String getDateFormat() {
        return  (new SimpleDateFormat("yyyy-MM-dd").format(this.dateOfBirth));
    }

    // Setter


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
