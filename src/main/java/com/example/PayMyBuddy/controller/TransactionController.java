package com.example.PayMyBuddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.ContactDao;
import com.example.PayMyBuddy.persistence.TransactionDao;
import com.example.PayMyBuddy.persistence.UserDao;
import com.example.PayMyBuddy.security.LoginUtils;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionDao transactionDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ContactDao contactDao;

	/* Liste de toutes les transactions (envoyé et reçu) */
	@GetMapping("/list")
	public List<TransactionEntity> findAll() {
		return transactionDao.findAll();
	}

	/* Liste des transactions reçues */
	@GetMapping("/receiptlist")
	public List<TransactionEntity> receivedMoney() {
		return transactionDao.receivedMoney();
	}

	/* Liste des transactions sorties */
	@GetMapping("/sentlist")
	public List<TransactionEntity> sentMoney() {
		return transactionDao.sentMoney();
	}

	/* Transferer de l'argent d'un user à un contact */
	@PostMapping("/transfert")
	@ResponseStatus(HttpStatus.CREATED)
	public void payment(@RequestBody TransactionEntity transactionEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity userEntity = userDao.findByEmail(user.getUsername());
		ContactEntity contactEntity = contactDao.findById(transactionEntity.getContactEntity().getId());
		transactionDao.payment(userEntity, contactEntity, transactionEntity.getMontant());
		return;

	}

	/* Réapprovisionner son compte PayMyBuddy */
	@PostMapping("/supplying")
	@ResponseStatus(HttpStatus.CREATED)
	public void supplying(@RequestBody TransactionEntity transactionEntity) {
		transactionDao.supplying();
	}

}
