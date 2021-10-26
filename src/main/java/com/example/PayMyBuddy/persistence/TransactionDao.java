package com.example.PayMyBuddy.persistence;

import java.util.List;

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;

public interface TransactionDao {

	List<TransactionEntity> findAll();

	List<TransactionEntity> receivedMoney();

	List<TransactionEntity> sentMoney();

	void payment();

	void supplying();

	void transfert(UserEntity userSend, UserEntity userReceive, int montant);

}
