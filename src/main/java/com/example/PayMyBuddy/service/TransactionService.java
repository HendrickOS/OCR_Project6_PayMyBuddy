package com.example.PayMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.persistence.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	public List<TransactionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void payment() {
		// TODO Auto-generated method stub

	}

	public List<TransactionEntity> receivedMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TransactionEntity> sentMoney() {
		// TODO Auto-generated method stub
		return null;
	}

	public void supplying() {
		// TODO Auto-generated method stub

	}

}
