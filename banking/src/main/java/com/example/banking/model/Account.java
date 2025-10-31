package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long accountNumber;
    private String accounttype;
    private double balance;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;

    public Account() {
    }


    public Account(Long id, long accountNumber, String accounttype, double balance, User user) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accounttype = accounttype;
        this.balance = balance;
        this.user = user;
    }


    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

}
