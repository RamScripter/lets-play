package com.mariekd.letsplay.authentication.controller;

import com.mariekd.letsplay.authentication.payload.response.UserInfoResponse;
import com.mariekd.letsplay.authentication.jwt.JwtService;
import com.mariekd.letsplay.authentication.payload.request.LoginRequest;
import com.mariekd.letsplay.authentication.entities.User;
import com.mariekd.letsplay.authentication.models.UserInfo;
import com.mariekd.letsplay.authentication.services.implementations.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(maxAge = 3600)
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserServiceImpl userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserInfo userDetails = (UserInfo) authentication.getPrincipal();

        String jwt = jwtService.generateJwtToken(authentication);

        final List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt)
                        .body(new UserInfoResponse(userDetails.id(), userDetails.getUsername(), roles));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        ResponseCookie cookie = jwtService.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out successfully");
    }

    @GetMapping("/all")
    @CrossOrigin
    public List<User> getAllUsers() {
        LOGGER.info("Getting all users: {} ", userService.getAllUsers());
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if (!userService.existsByEmail(user.getEmail())) {
                userService.createUser(user);
                return ResponseEntity.ok(new UserInfoResponse(user.getId(), user.getEmail(), List.of(user.getRoles().toString())));
            } else {
                throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
            }
        } catch (final IllegalArgumentException e) {
            LOGGER.error("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error creating user: " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("Error creating user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')") // ne le laisser qu'en admin ?
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

}
