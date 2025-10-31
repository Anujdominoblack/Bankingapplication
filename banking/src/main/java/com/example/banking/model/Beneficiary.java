package com.example.banking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Beneficiary {
  @Id
  @GeneratedValue
  private long id;
  private long accountNumber;
  private String name;
  private String bankname;
  private String ifsccode;
    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private User user;
    public Beneficiary() {
    }
    public Beneficiary(long accountNumber, String name, String bankname, String ifsccode, User user) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.bankname = bankname;
        this.ifsccode = ifsccode;
        this.user = user;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getIfsccode() {
        return ifsccode;
    }
    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
        public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    


}
