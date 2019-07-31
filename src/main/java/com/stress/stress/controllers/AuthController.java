package com.stress.stress.controllers;

import java.net.http.HttpHeaders;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(AuthController.BASE_URL) // Map the URL to this controller
public class AuthController {

    public static final String BASE_URL = "api/v1/auth";
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
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
}