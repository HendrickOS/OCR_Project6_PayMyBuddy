package com.example.PayMyBuddy.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.PayMyBuddy.domain.TransactionEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
//@Transactional grâce à CrudRepository
}
