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

	/**
	 * Liste de toutes les transactions du User connecté
	 **/
	@GetMapping("/list")
	public Iterable<TransactionEntity> findAllTransactions() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		Set<TransactionEntity> transactions = currentUser.getTransactions();

		for (TransactionEntity transactionEntity : transactions) {
			if (transactionEntity.getContact() != null) {
				transactionEntity.getContact().setTransactions(null);
				transactionEntity.getContact().setContacts(null);
			}
			if (transactionEntity.getUser() != null) {
				transactionEntity.getUser().setTransactions(null);
				transactionEntity.getUser().setContacts(null);
			}
		}

		return transactions;
	}

	/**
	 * Montant total récupéré par PMB avec les taxes sur les transactions
	 **/
	@GetMapping("/taxemoney")
	public double payMyBuddyMoney() {
		double result;
		double somme = 0;
		Iterable<TransactionEntity> allTransactions = transactionDao.findAll();
		for (TransactionEntity transaction : allTransactions) {
			somme = somme + transaction.getMontant();
		}
		result = somme * 0.005;
		return result;
	}

	/**
	 * Transferer de l'argent d'un user à un contact
	 **/
	@PostMapping("/transfert")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<TransactionEntity> payment2(@RequestBody TransactionEntity transactionEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		List<UserEntity> allUsers = userDao.findAll();

		currentUser.getTransactions().add(transactionEntity);

		Set<UserEntity> contacts = currentUser.getContacts();
		for (UserEntity contact : contacts) {
			if (contact.getId().equals(transactionEntity.getContact().getId())) {
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
