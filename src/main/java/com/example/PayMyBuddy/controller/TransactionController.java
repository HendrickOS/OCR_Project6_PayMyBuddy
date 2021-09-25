package com.example.PayMyBuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.persistence.TransactionDao;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionDao transactionDao;

	/* Liste de toutes les transactions (envoyé et reçu) */
	@GetMapping("/list")
	public List<TransactionEntity> findAll() {
		return transactionDao.findAll();
	}

	/* Liste des transactions reçues */
	@GetMapping("/receipt")
	public List<TransactionEntity> receivedMoney() {
		return transactionDao.receivedMoney();
	}

	/* Liste des transactions sorties */
	@GetMapping("/send")
	public List<TransactionEntity> sentMoney() {
		return transactionDao.sentMoney();
	}

	/* Envoyer de l'argent à un contact */
	@PostMapping("/payment")
	@ResponseStatus(HttpStatus.CREATED)
	public void payment(@RequestBody TransactionEntity transactionEntity) {
		transactionDao.payment();
	}

	/* Réapprovisionner son compte PayMyBuddy */
	@PostMapping("/supplying")
	@ResponseStatus(HttpStatus.CREATED)
	public void supplying(@RequestBody TransactionEntity transactionEntity) {
		transactionDao.supplying();
	}

}
