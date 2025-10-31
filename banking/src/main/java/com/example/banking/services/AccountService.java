package com.example.banking.services;

import com.example.banking.model.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Account account, Long userId);
    List<Account> getAccountById(Long id);
    Account updateAccount(Account account, Long id);
    boolean deleteAccount(Long id);
    List<Account> getAllAccounts();
}
