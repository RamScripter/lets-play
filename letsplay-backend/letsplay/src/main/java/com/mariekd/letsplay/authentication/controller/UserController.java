package com.mariekd.letsplay.authentication.controller;

import com.mariekd.letsplay.authentication.entities.User;
import com.mariekd.letsplay.authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class UserController {

    @Autowired
    private final UserService userService;

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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
