package com.example.banking.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.banking.model.Account;
import com.example.banking.model.User;
import com.example.banking.repository.AccountRepo;
import com.example.banking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo, UserRepo userRepo) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Account createAccount(Account account, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    @Override
    public List<Account> getAccountById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAccounts(); 
        
     }

    @Override
    public Account updateAccount(Account account, Long id) {
        Optional<Account> opt = accountRepo.findById(id);
        if (opt.isPresent()) {
            Account a = opt.get();
            a.setAccounttype(account.getAccounttype());
            a.setBalance(account.getBalance());
            return accountRepo.save(a);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Optional<Account> opt = accountRepo.findById(id);
        if (opt.isPresent()) { accountRepo.delete(opt.get()); return true; }
        return false;
    }

    @Override
    public List<Account> getAllAccounts() { return accountRepo.findAll(); }
}
