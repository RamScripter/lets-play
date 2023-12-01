package com.mariekd.letsplay.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class AuthenticationController {

//        private final AuthenticationService authenticationService;
//
        // @PostMapping
        // public User createUser(@RequestBody User user) {
        //     return userService.createUser(user);
        // }

        // @PutMapping("/{id}")
        // public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        //     return userService.updateUser(id, user);
        // }

        // @DeleteMapping("/{id}")
        // public void deleteUser(@PathVariable UUID id) {
        //     userService.deleteUser(id);
        // }
}
