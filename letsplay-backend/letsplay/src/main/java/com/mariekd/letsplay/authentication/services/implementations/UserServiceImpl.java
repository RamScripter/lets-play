package com.mariekd.letsplay.authentication.services.implementations;

import com.mariekd.letsplay.authentication.config.PasswordEncoderConfig;
import com.mariekd.letsplay.entities.User;
import com.mariekd.letsplay.authentication.repositories.UserRepository;
import com.mariekd.letsplay.authentication.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoderConfig passwordEncoderConfig, UserRepository userRepository) {
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(User user) {
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUser(UUID id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
