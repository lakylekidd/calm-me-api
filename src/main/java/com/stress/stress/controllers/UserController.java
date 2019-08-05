package com.stress.stress.controllers;

import java.util.List;

import com.stress.stress.domain.User;
import com.stress.stress.domain.UserAuthData;
import com.stress.stress.helpers.TokenHelper;
import com.stress.stress.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://calm-me.herokuapp.com")
@RestController
@RequestMapping(UserController.BASE_URL) // Map the URL to this controller
public class UserController {

    public static final String BASE_URL = "api/v1/users";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping // WE can also do request mapping on the function level
    List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}") // Can also use @RequestMapping
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("api/v1/auth")
    public ResponseEntity<String> login(@RequestBody UserAuthData user) {
        // Retrieve the user from the database
        User entity = userService.findUserByEmail(user.getEmail());
        // Check if user exists
        if (entity != null) {
            // Entity exists, Check if passwords match
            boolean match = entity.getPassword().equals(user.getPassword());
            if (match) {
                // User password match, return token
                String jwt = TokenHelper.CreateJWT(entity);
                return ResponseEntity.ok(jwt);
            }
        }
        // Everything failed, return 404
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User savCustomer(@RequestBody User user) {
        return userService.saveUser(user);
    }
}