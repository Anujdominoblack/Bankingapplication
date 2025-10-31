package com.example.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.banking.model.Transaction;
@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long>{
  List<Transaction> findByUserId(Long userId);
}
