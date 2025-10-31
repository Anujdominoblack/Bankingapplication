package com.example.banking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.banking.config.JwtUtils;
import com.example.banking.model.User;
import com.example.banking.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User register(User user) {
        // ✅ Check if email already exists
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }

        // ✅ Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public String login(String email, String password) {
        // ✅ Find user by email
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Generate JWT token (subject = email)
        return jwtUtils.generateToken(user);
    }
}
