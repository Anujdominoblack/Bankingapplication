package com.example.banking.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.banking.model.Beneficiary;
import com.example.banking.model.User;
import com.example.banking.repository.BeneficiaryRepo;
import com.example.banking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepo beneficiaryRepo;
    private final UserRepo userRepo;

    @Autowired
    public BeneficiaryServiceImpl(BeneficiaryRepo beneficiaryRepo, UserRepo userRepo) {
        this.beneficiaryRepo = beneficiaryRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Beneficiary addBeneficiary(Beneficiary beneficiary, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        beneficiary.setUser(user);
        user.getBeneficiaries().add(beneficiary);
        return beneficiaryRepo.save(beneficiary);
    }

    @Override
    public boolean deleteBeneficary(Long beneficiaryId) {
        Optional<Beneficiary> b = beneficiaryRepo.findById(beneficiaryId);
        if (b.isPresent()) { beneficiaryRepo.delete(b.get()); return true; }
        return false;
    }

    @Override
    public List<Beneficiary> getAllBeneficiariesByUserId(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return beneficiaryRepo.findByUserId(userId);
    }
}
