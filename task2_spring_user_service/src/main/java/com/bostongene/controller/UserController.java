package com.bostongene.controller;

import com.bostongene.exception.ServiceException;
import com.bostongene.exception.UserExistsException;
import com.bostongene.model.UserProfile;
import com.bostongene.responses.CreateUserRequest;
import com.bostongene.responses.ErrorResponse;
import com.bostongene.responses.SuccessResponse;
import com.bostongene.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Andry on 29.05.17.
 */
@Api(value = "users", description = "Endpoint for user management", protocols="HTTP")
@RestController
public class UserController {

    private final UserService userService;

    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService _userService, PasswordEncoder _passwordEncoder) {
        this.userService = _userService;
        this.passwordEncoder = _passwordEncoder;
    }


    @ApiOperation(value = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User with email 'email' is create", response = SuccessResponse.class),
            @ApiResponse(code = 400, message = "Some fields is invalid/User 'email' already exists", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Service unavailable", response = ErrorResponse.class)
    })
    @RequestMapping(path = "/user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity create(@RequestBody CreateUserRequest body) {

        final String surname = body.getSurname();
        final String username = body.getUsername();
        final Date dateOfBirth = body.getDateOfBirth();
        final String email = body.getEmail();
        final String password = body.getPassword();

        if (StringUtils.isEmpty(surname)
                || StringUtils.isEmpty(username)
                || dateOfBirth == null
                || StringUtils.isEmpty(email)
                || StringUtils.isEmpty(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Some fields is invalid"));
        }

        try {

            UserProfile user = userService.save(new UserProfile(surname, username, dateOfBirth, email, passwordEncoder.encode(password)));

        } catch (UserExistsException e) {
            LOGGER.error(String.format("User %s already exists", email), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        } catch (ServiceException e) {
            LOGGER.error("UserService error: ", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse("Service unavailable"));
        }

        LOGGER.info(String.format("User with email %s is create", email));
        return ResponseEntity.ok(new SuccessResponse(email));
    }



    @ApiOperation(value = "Find user by email")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User email", required = true, dataType = "string", paramType = "query", defaultValue="string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = UserProfile.class),
            @ApiResponse(code = 400, message = "Email field is invalid", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Service unavailable", response = ErrorResponse.class)
    })
    @RequestMapping(path = "/user", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findByEmail(@RequestParam(value = "email") String email) {

        if (StringUtils.isEmpty(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Email field is invalid"));
        }

        try {

            UserProfile user = userService.findByEmail(email);

            if( user != null) {
                LOGGER.info(String.format("User with email %s is found: ", email));
                return ResponseEntity.ok(user);
            }

        } catch (ServiceException e) {
            LOGGER.error("UserService error: ", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse("Service unavailable"));
        }

        LOGGER.info(String.format("User with email %s is Not found: ", email));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Not found"));
    }




    @ApiOperation(value = "Delete user by email")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User email", required = true, dataType = "string", paramType = "query", defaultValue="string")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "{}"),
            @ApiResponse(code = 400, message = "Email field is invalid", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Service unavailable", response = ErrorResponse.class)
    })
    @RequestMapping(path = "/user", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@RequestParam(value = "email") String email) {

        if (StringUtils.isEmpty(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Email field is invalid"));
        }

        try {

            UserProfile user = userService.findByEmail(email);

            if( user != null) {
                userService.delete(user);

                LOGGER.info(String.format("User with email %s is deleted: ", email));
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}");
            }

        } catch (ServiceException e) {
            LOGGER.error("UserService error: ", e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ErrorResponse("Service unavailable"));
        }

        LOGGER.info(String.format("User with email %s is Not found: ", email));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Not found"));
    }

}
