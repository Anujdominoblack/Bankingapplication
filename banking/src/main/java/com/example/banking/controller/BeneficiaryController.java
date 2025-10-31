package com.example.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.Beneficiary;
import com.example.banking.services.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {

    BeneficiaryService beneficiaryService;
    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiary(@PathVariable Long userId){
        List<Beneficiary> beneficiaries = beneficiaryService.getAllBeneficiariesByUserId(userId);
        if(beneficiaries != null) {
            return ResponseEntity.ok(beneficiaries);
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping("/add/{userID}")
    public ResponseEntity<Beneficiary> addBeneficary(@RequestBody Beneficiary beneficiary,@PathVariable Long userID){
        Beneficiary addedBeneficiary = beneficiaryService.addBeneficiary(beneficiary, userID);
        if(addedBeneficiary != null) {
            return ResponseEntity.ok(addedBeneficiary);
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/delete/{beneficiaryId}")
    public ResponseEntity<String> deleteBeneficary(@PathVariable Long beneficiaryId){
        boolean isDeleted = beneficiaryService.deleteBeneficary(beneficiaryId);
        if(isDeleted) {
            return ResponseEntity.ok("Beneficiary deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    

}
