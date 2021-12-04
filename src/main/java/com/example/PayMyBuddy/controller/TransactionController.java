package com.example.PayMyBuddy.controller;

import java.util.List;
import java.util.Set;

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

import com.example.PayMyBuddy.domain.TransactionEntity;
import com.example.PayMyBuddy.domain.UserEntity;
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

	/* Liste de toutes les transactions (envoyé et reçu) */
	@GetMapping("/list")
	public Iterable<TransactionEntity> findAllTransactions() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		return currentUser.getTransactions();
	}

	/* *********************************************************** */
	/* NOUVELLE VERSION AVEC UN SET DE USER EN TANT QUE CONTACTS DU USER CONNECTE */
	/* *********************************************************** */

	/* Transferer de l'argent d'un user à un contact */
	@PostMapping("/transfert")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<TransactionEntity> payment2(@RequestBody TransactionEntity transactionEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		double taxe = 1.005;
		List<UserEntity> allUsers = userDao.findAll();

		currentUser.getTransactions().add(transactionEntity);

		Set<UserEntity> contacts = currentUser.getContacts();
		for (UserEntity contact : contacts) {
			if (contact.getId().equals(transactionEntity.getUserEntity().getId())) {
				contact.setSolde(contact.getSolde() + transactionEntity.getMontant());
				currentUser.setSolde(currentUser.getSolde() - (transactionEntity.getMontant() * 1.005));
				userDao.save(contact);

				for (UserEntity admin : allUsers) {
					if (admin.getUsername().equals("admin")) {
						admin.setSolde(admin.getSolde() + (transactionEntity.getMontant() * 0.005));
						userDao.save(admin);
					}
				}
			}
		}
		userDao.save(currentUser);
		return findAllTransactions();
	}
}
