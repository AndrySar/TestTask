package com.bostongene.service;

import com.bostongene.exception.ServiceException;
import com.bostongene.exception.UserExistsException;
import com.bostongene.model.UserProfile;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Andry on 29.05.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    // Using Faker for generate user data
    private Faker faker;

    @Before
    public void init() {

        faker = new Faker();

        try {
            userService.save(getUserProfile());
            userService.save(getUserProfile());
            userService.save(getUserProfile());

        }catch (UserExistsException e)
        {
            assertNotNull(null);
        }
    }

    @Test
    public void saveUser() {

        try {
            // Add new user
            UserProfile user = userService.save(getUserProfile());
            assertNotNull(user);
        }catch (UserExistsException e) {
            assertNotNull(null);
        }
    }

    @Test(expected = UserExistsException.class)
    public void addExistsUser() {
        UserProfile user = userService.save(getUserProfile());

        userService.save(new UserProfile(
                user.getSurname(),
                user.getUsername(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getPassword()
        ));
    }

    @Test
    public void findByEmail() {
        UserProfile user = userService.save(getUserProfile());
        assertNotNull(user);

        UserProfile userDB = userService.findByEmail(user.getEmail());

        assertEquals(user.getSurname(), userDB.getSurname());
        assertEquals(user.getUsername(), userDB.getUsername());
        assertEquals(user.getDateOfBirth(), userDB.getDateOfBirth());
        assertEquals(user.getEmail(), userDB.getEmail());
        assertEquals(user.getPassword(), userDB.getPassword());
    }

    @Test
    public void getNotExistsUserByEmail() {
        UserProfile user = userService.findByEmail(faker.sentence() + "@mail.ru");
        assertNull(user);
    }

    // Add user with invalid parameter
    @Test(expected = ServiceException.class)
    public void saveUserWithInvalidParameter() {
        UserProfile user = userService.save(new UserProfile(
                null,
                faker.country(),
                new Date(),
                "email",
                "password"
        ));
        assertNull(user);
    }

    // Generate new user
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
