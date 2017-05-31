package com.bostongene.responses;

import com.bostongene.jackson.model.CustomDateSerializer;
import com.bostongene.jackson.model.CustomJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Andry on 29.05.17.
 */
public class CreateUserRequest {

    @ApiModelProperty(notes = "User surname", required = true)
    private String surname;

    @ApiModelProperty(notes = "User name", required = true)
    private String username;

    @ApiModelProperty(notes = "User date of birth", required = true)
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date dateOfBirth;

    @ApiModelProperty(notes = "User email", required = true)
    private String email;

    @ApiModelProperty(notes = "User password", required = true)
    private String password;

    @SuppressWarnings("unused")
    public CreateUserRequest() {
    }

    @SuppressWarnings("unused")
    public CreateUserRequest(String surname, String username, Date date, String email, String password) {
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = date;
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
