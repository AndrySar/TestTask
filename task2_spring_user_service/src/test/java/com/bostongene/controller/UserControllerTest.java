package com.bostongene.controller;


import com.bostongene.model.UserProfile;
import com.bostongene.service.UserService;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Andry on 29.05.17.
 *
 * Using MockMvc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@Transactional
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    // Using Faker for generate user data
    private Faker faker;


    @Before
    public void init(){
        faker = new Faker();
    }

    @Test
    public void createUser() throws Exception{

        UserProfile user = getUserProfile();

        // Post request, add new user
        mockMvc.perform(MockMvcRequestBuilders
                .post("/user")
                .content(String.format("{" +
                        "\"surname\":  \"%s\", " +
                        "\"username\": \"%s\" , " +
                        "\"dateOfBirth\": \"%s\", " +
                        " \"email\": \"%s\", " +
                        "\"password\": \"%s\" " +
                        "}",
                        user.getSurname(),
                        user.getUsername(),
                        user.getDateFormat(),
                        user.getEmail(),
                        user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Check created user
        UserProfile userDB = userService.findByEmail(user.getEmail());
        assertNotNull(userDB);

        // Delete created user
        userService.delete(userDB);
    }

    @Test
    public void deleteUser() throws Exception {
        // Add new user
        UserProfile user = userService.save(getUserProfile());

        // Delete request for delete user
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/user")
                .param("email", user.getEmail())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Check that user is deleted
        UserProfile userDB = userService.findByEmail(user.getEmail());
        assertNull(userDB);
    }

    private UserProfile getUserProfile() {
        return new UserProfile(
                faker.firstName(),
                faker.name(),
                new Date(),
                faker.sentence() + "@mail.ru",
                faker.sentence()
        );
    }
}
