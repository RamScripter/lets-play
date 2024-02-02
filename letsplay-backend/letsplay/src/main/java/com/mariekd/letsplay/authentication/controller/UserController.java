package com.mariekd.letsplay.authentication.controller;

import com.mariekd.letsplay.authentication.config.PasswordEncoderConfig;
import com.mariekd.letsplay.authentication.entities.LoginRequest;
import com.mariekd.letsplay.authentication.entities.User;
import com.mariekd.letsplay.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(maxAge = 3600)
public class UserController {

    private final UserService userService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    public UserController(UserService userService, PasswordEncoderConfig passwordEncoderConfig) {
        this.userService = userService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    @CrossOrigin
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users: " + userService.getAllUsers());
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        try {
            if (!userService.existsByEmail(user.getEmail())) {
                user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
            } else {
                throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
            }
        } catch (Exception e) {
            LOGGER.error("Error creating user: " + e.getMessage()); //TODO : send back error message to client
            throw new IllegalArgumentException("Error creating user: " + e.getMessage());
        }
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails receivedUserInfo = userService.loadUserByUsername(loginRequest.getEmail());
            if (!passwordEncoderConfig.passwordEncoder().matches(loginRequest.getPassword(), receivedUserInfo.getPassword())) {
                throw new UsernameNotFoundException("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

        return ResponseEntity.ok("Logged in");
    }

}
