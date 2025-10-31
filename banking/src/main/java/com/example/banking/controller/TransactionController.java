package com.example.banking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.model.TransactionRequest;
import com.example.banking.model.TransactionWrapper;
import com.example.banking.services.TransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Deposit money
    @PostMapping("/deposit/{userId}/{accountId}")
    public ResponseEntity<Transaction> depositMoney(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @RequestBody TransactionRequest request) {

        Transaction transaction = transactionService.deposit(
                userId,
                accountId,
                request.getAmount(),
                request.getDescription()
        );
        return ResponseEntity.ok(transaction);
    }

    // ✅ Withdraw money
    @PostMapping("/withdraw/{userId}/{accountId}")
    public ResponseEntity<Transaction> withdrawMoney(
            @PathVariable Long userId,
            @PathVariable Long accountId,
            @RequestBody TransactionRequest request) {

        Transaction transaction = transactionService.withdraw(
                userId,
                accountId,
                request.getAmount(),
                request.getDescription()
        );
        return ResponseEntity.ok(transaction);
    }

    // ✅ Transfer money (handles beneficiary creation if destination missing)
    @PostMapping("/transfer/{userId}/{fromAccountId}")
public ResponseEntity<Transaction> transferMoney(
        @PathVariable Long userId,
        @PathVariable Long fromAccountId,
        @RequestBody TransactionWrapper requestWrapper) {

    Transaction transaction = transactionService.transfer(
            userId,
            fromAccountId,
              requestWrapper
    );

    return ResponseEntity.ok(transaction);
}

    // ✅ Get all transactions for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUser(userId));
    }

    // ✅ Get all transactions (admin/analytics)
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
