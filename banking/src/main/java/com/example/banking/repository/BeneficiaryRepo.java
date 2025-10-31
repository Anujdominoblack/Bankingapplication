package com.example.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Beneficiary;
@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Long> {
    boolean existsByAccountNumber(String accountNumber);
    List<Beneficiary> findByUserId(Long userId);
    

}
