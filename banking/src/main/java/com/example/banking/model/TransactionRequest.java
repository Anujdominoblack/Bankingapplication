package com.example.banking.model;

public class TransactionRequest {


 private Double amount;
    private String description;
 public TransactionRequest() {
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
}
