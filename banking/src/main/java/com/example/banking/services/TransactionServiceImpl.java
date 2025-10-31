package com.example.banking.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking.model.*;
import com.example.banking.repository.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    // ✅ Deposit
    @Transactional
    @Override
    public Transaction deposit(Long userId, Long accountId, Double amount, String description) {
        Account account = accountRepo.findByAccountNumber(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setDescription(description);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSourceAccount(account);
        tx.setUser(account.getUser());

        return transactionRepo.save(tx);
    }

    // ✅ Withdraw
    @Transactional
    @Override
    public Transaction withdraw(Long userId, Long accountId, Double amount, String description) {
        Account account = accountRepo.findByAccountNumber(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setDescription(description);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSourceAccount(account);
        tx.setUser(account.getUser());

        return transactionRepo.save(tx);
    }

    // ✅ Transfer
    @Transactional
    @Override
    public Transaction transfer(Long userId, Long fromAccountId, TransactionWrapper requestWrapper) {
        // Extract request details
        Double amount = requestWrapper.getTransaction().getAmount();
        String description = requestWrapper.getTransaction().getDescription();
        Long destinationAccountNumber = requestWrapper.getBeneficiary().getAccountNumber();

        // Fetch source account by ID
        Account fromAccount = accountRepo.findByAccountNumber(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // ✅ Try to find destination account by accountNumber
        Account toAccount = accountRepo.findByAccountNumber(destinationAccountNumber).orElse(null);

        // ✅ Debit sender
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepo.save(fromAccount);

        if (toAccount != null) {
            // ✅ Credit receiver
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountRepo.save(toAccount);
        } else {
            // ✅ Register as new Beneficiary if receiver account doesn't exist
            User user = fromAccount.getUser();
            if (user == null) {
                throw new RuntimeException("Source account has no linked user");
            }

            Beneficiary newBeneficiary = new Beneficiary();
            newBeneficiary.setUser(user);
            newBeneficiary.setAccountNumber(destinationAccountNumber);
            newBeneficiary.setName(requestWrapper.getBeneficiary().getName());
            newBeneficiary.setBankname(requestWrapper.getBeneficiary().getBankname());
            newBeneficiary.setIfsccode(requestWrapper.getBeneficiary().getIfsccode());
            beneficiaryRepo.save(newBeneficiary);
        }

        // ✅ Record transaction
        Transaction tx = new Transaction();
        tx.setType("TRANSFER");
        tx.setAmount(amount);
        tx.setDescription(description);
        tx.setTimestamp(LocalDateTime.now());
        tx.setSourceAccount(fromAccount);
        tx.setDestinationAccountNumber(destinationAccountNumber);
        tx.setUser(fromAccount.getUser());

        return transactionRepo.save(tx);
    }

    // ✅ Get all transactions by user
    @Override
    public List<Transaction> getAllTransactionsByUser(Long userId) {
        return transactionRepo.findByUserId(userId);
    }

    // ✅ Get all transactions (admin)
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
}
