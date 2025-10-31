package com.example.banking.config;

import org.springframework.stereotype.Service;
import com.example.banking.repository.UserRepo;
import com.example.banking.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Autowired public MyUserDetailsService(UserRepo userRepo) { this.userRepo = userRepo; }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found with email: " + email);
        return new UserPrincipal(user);
    }
}
