package com.example.banking.model;

public class TransactionWrapper {
private TransactionRequest transaction;
    private Beneficiary beneficiary;

    public TransactionRequest getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionRequest transaction) {
        this.transaction = transaction;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }
}
