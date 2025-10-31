package com.example.banking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.banking.model.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(long accountNumber);
}
