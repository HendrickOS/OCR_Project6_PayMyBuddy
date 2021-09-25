package com.example.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.persistence.ContactDao;
import com.example.PayMyBuddy.persistence.PersistenceException;
import com.example.PayMyBuddy.security.LoginUtils;
import com.example.PayMyBuddy.security.Roles;

@RestController
@CrossOrigin
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	ContactDao contactDao;

	@Secured({ Roles.USER })
	@GetMapping("/list")
	public Iterable<ContactEntity> findAll() {
		User user = LoginUtils.getLoggedUser();
		return contactDao.findAll();
	}

	/* Ajouter un contact */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContactEntity addNewContact(@RequestBody ContactEntity contactEntity) {
		return contactDao.addContact(contactEntity);
	}

	/* Mettre à jour un contact */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ContactEntity updateInfoContact(@RequestBody ContactEntity contactEntity) {
		try {
			return contactDao.updateContact(contactEntity);
		} catch (PersistenceException e) {
			throw new ResponseStatusException(e.getStatus(), e.getMessage());
		}
	}

	/* Supprimer un contact */
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteExistingContact(@PathVariable("id") Integer id) {
		contactDao.deleteContact(id);
	}

}
