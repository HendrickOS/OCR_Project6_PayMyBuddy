package com.example.PayMyBuddy.persistence;

import java.util.List;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;

public interface TransactionDao {

	Iterable<TransactionEntity> findAll();

	List<TransactionEntity> receivedMoney();

	List<TransactionEntity> sentMoney();

	void payment(UserEntity userEntity, ContactEntity contactEntity, int montant);

	void supplying(UserEntity userEntity, Integer montant);

}
