package com.example.PayMyBuddy.persistence;

import java.util.List;

import com.example.PayMyBuddy.domain.TransactionEntity;

public interface TransactionDao {

	List<TransactionEntity> findAll();

	List<TransactionEntity> receivedMoney();

	List<TransactionEntity> sentMoney();

	void payment();

	void supplying();

}
