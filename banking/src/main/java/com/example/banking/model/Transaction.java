package com.example.banking.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private Double amount;
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount; // Always exists (for all transaction types)

    private Long destinationAccountNumber; // Only relevant for TRANSFER type

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user; // The user who initiated this transaction

    public Transaction() {}

    public Transaction(String type, Double amount, String description, LocalDateTime timestamp,
                       Account sourceAccount, Long destinationAccountNumber, User user) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
        this.sourceAccount = sourceAccount;
        this.destinationAccountNumber = destinationAccountNumber;
        this.user = user;
    }

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Long getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Long destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
