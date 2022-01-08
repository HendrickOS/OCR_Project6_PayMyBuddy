package com.example.PayMyBuddy.persistence;

import com.example.PayMyBuddy.domain.TransactionEntity;

public interface TransactionDao {

	Iterable<TransactionEntity> findAll();

}
