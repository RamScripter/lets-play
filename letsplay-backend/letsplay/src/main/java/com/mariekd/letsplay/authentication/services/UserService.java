package com.mariekd.letsplay.authentication.services;

import com.mariekd.letsplay.app.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
    User getUserByEmail(String email);

    boolean existsByEmail(String email);

    User createUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
}
