package com.example.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.Account;
import com.example.banking.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping("/create/{userId}")
    public ResponseEntity<Account> createAccount(@RequestBody Account account,@PathVariable Long userId){
        Account createdAccount = accountService.createAccount(account, userId);
        if(createdAccount != null) {
            return ResponseEntity.ok(createdAccount);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAccountById(@PathVariable Long id){
        List<Account> account = accountService.getAccountById(id);
        if(account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account,@PathVariable Long id){
        Account updatedAccount = accountService.updateAccount(account, id);
        if(updatedAccount != null) {
            return ResponseEntity.ok(updatedAccount);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        boolean isDeleted = accountService.deleteAccount(id);
        if(isDeleted) {
            return ResponseEntity.ok("Account deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

}
