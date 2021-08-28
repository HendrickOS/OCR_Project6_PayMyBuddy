package com.example.PayMyBuddy.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.TransactionEntity;

@Component
public class TransactionDaoJDBCImpl implements TransactionDao {

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
