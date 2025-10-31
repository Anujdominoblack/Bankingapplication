package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banking.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
