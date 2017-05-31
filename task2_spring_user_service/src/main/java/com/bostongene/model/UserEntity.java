package com.bostongene.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Andry on 29.05.17.
 */

@Entity
@Table(name = "userTable")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "surname", nullable=false)
    private String surname;

    @Column(name = "username", nullable=false)
    private String username;

    @Temporal(TemporalType.DATE)
    @Column(name = "datebirth", nullable=false)
    private Date dateOfBirth;

    @Column(name = "email", unique = true, nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public UserEntity() {
    }

    public UserEntity(String surname, String username, Date dateOfBirth, String email, String password) {
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    // Getter

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
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

    public UserProfile toDto() {
        return new UserProfile(surname, username, dateOfBirth, email, password);
    }
}
