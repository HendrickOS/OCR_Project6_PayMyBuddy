package com.example.PayMyBuddy.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.TransactionEntity;

@Component
public class TransactionDaoJDBCImpl implements TransactionDao {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Iterable<TransactionEntity> findAll() {
		return transactionRepository.findAll();
	}

}
