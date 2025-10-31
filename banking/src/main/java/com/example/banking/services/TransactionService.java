package com.example.banking.services;

import com.example.banking.model.Transaction;
import com.example.banking.model.TransactionWrapper;
import java.util.List;

public interface TransactionService {
    Transaction deposit(Long userId, Long accountId, Double amount, String description);
    Transaction withdraw(Long userId, Long accountId, Double amount, String description);
    Transaction transfer(Long userId, Long fromAccountId, TransactionWrapper requestWrapper);
    List<Transaction> getAllTransactionsByUser(Long userId);
    List<Transaction> getAllTransactions();
}
