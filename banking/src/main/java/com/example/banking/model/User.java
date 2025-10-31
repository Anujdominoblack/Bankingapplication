package com.example.banking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // ✅ avoid SQL keyword conflict
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String accountNumber;
    private String password;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;

    // ✅ One User -> Many Accounts
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    // ✅ One User -> Many Beneficiaries
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Beneficiary> beneficiaries = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Transaction> transactions = new ArrayList<>();

    // 🏗️ Constructors
    public User() {}

    public User(Long id, String name, String accountNumber, String password, String email, String phoneNumber,
            String dateOfBirth, List<Account> accounts, List<Beneficiary> beneficiaries,
            List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.accounts = accounts;
        this.beneficiaries = beneficiaries;
        this.transactions = transactions;
    }

    // ⚙️ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }

    public List<Beneficiary> getBeneficiaries() { return beneficiaries; }
    public void setBeneficiaries(List<Beneficiary> beneficiaries) { this.beneficiaries = beneficiaries; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}
