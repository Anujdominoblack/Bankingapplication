package com.example.banking.services;
import java.util.List;
import com.example.banking.model.Beneficiary;

public interface BeneficiaryService {
    Beneficiary addBeneficiary(Beneficiary beneficiary, Long userId);
    boolean deleteBeneficary(Long beneficiaryId);
    List<Beneficiary> getAllBeneficiariesByUserId(Long userId);
}
