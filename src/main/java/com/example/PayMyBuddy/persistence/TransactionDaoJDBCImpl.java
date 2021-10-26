package com.example.PayMyBuddy.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;

@Component
public class TransactionDaoJDBCImpl implements TransactionDao {

	@Override
	public void transfert(UserEntity userSend, UserEntity userReceive, int montant) {
		if (userSend.getSolde() >= montant) {
			userSend.setSolde(userSend.getSolde() - montant);
			userReceive.setSolde(userReceive.getSolde() + montant);
		}
	}

	@Override
	public List<TransactionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionEntity> receivedMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionEntity> sentMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payment() {
		// TODO Auto-generated method stub

	}

	@Override
	public void supplying() {
		// TODO Auto-generated method stub

	}

}
