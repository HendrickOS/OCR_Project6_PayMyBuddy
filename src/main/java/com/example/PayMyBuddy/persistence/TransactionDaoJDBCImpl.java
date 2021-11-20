package com.example.PayMyBuddy.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;

@Component
public class TransactionDaoJDBCImpl implements TransactionDao {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Iterable<TransactionEntity> findAll() {
		return transactionRepository.findAll();
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

//	@Override
//	public void payment(UserEntity userEntity, ContactEntity contactEntity, int montant) {
//		if (userEntity.getSolde() >= montant) {
//			userEntity.setSolde(userEntity.getSolde() - montant);
//			contactEntity.setSolde(contactEntity.getSolde() + montant);
//		}
//		TransactionEntity transactionEntity = new TransactionEntity();
//		transactionEntity.setMontant(montant);
//		transactionEntity.setContactEntity(contactEntity);
//		transactionEntity.setUserEntity(userEntity);
//		transactionRepository.save(transactionEntity);
//	}

	@Override
	public void supplying(UserEntity userEntity, Integer montant) {
		userEntity.setSolde(userEntity.getSolde() + montant);
	}

//	@Override
//	public void payment(UserEntity userEntity, ContactEntity contactEntity, int montant) {
//		// TODO Auto-generated method stub
//
//	}

}
