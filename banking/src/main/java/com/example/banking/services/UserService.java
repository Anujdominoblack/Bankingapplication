package com.example.banking.services;

import com.example.banking.model.User;

public interface UserService {
    User register(User user);
    String login(String email, String password);
}
