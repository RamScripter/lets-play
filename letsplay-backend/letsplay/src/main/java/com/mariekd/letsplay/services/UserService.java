package com.mariekd.letsplay.services;

import com.mariekd.letsplay.authentication.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID id);
    User createUser(User user);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
}
