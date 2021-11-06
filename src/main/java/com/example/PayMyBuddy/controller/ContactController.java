package com.example.PayMyBuddy.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.ContactDao;
import com.example.PayMyBuddy.persistence.UserDao;
import com.example.PayMyBuddy.security.LoginUtils;
import com.example.PayMyBuddy.security.Roles;

@RestController
@CrossOrigin
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	ContactDao contactDao;

	@Autowired
	UserDao userDao;

	@Secured({ Roles.USER })
	@GetMapping("/list")
	public Iterable<ContactEntity> findAllContacts() {
		User user = LoginUtils.getLoggedUser();
		UserEntity userEntity = userDao.findByEmail(user.getUsername());
		return userEntity.getContacts();
	}

	/* Ajouter un contact */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<ContactEntity> addNewContact(@RequestBody ContactEntity contactEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity userEntity = userDao.findByEmail(user.getUsername());
//		contactEntity.setUser(userEntity);
		userEntity.getContacts().add(contactEntity);
		userDao.save(userEntity);
		return findAllContacts();
	}

//	/* Mettre à jour un contact */
//	@PutMapping
//	@ResponseStatus(HttpStatus.OK)
//	public ContactEntity updateInfoContact(@RequestBody ContactEntity contactEntity) {
//		try {
//			return contactDao.updateContact(contactEntity);
//		} catch (PersistenceException e) {
//			throw new ResponseStatusException(e.getStatus(), e.getMessage());
//		}
//	}

	/* Mettre à jour un contact */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<ContactEntity> updateInfoContact(@RequestBody ContactEntity contactEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity userEntity = userDao.findByEmail(user.getUsername());
		ContactEntity contact = getContactFromId(userEntity, contactEntity.getId());
		if (contactEntity.getUsername() != null) {
			contact.setUsername(contactEntity.getUsername());
		}
		if (contactEntity.getDescription() != null) {
			contact.setDescription(contactEntity.getDescription());
		}
		if (contactEntity.getSolde() != null) {
			contact.setSolde(contactEntity.getSolde());
		}
		userDao.save(userEntity);
		return findAllContacts();
	}

	private ContactEntity getContactFromId(UserEntity userEntity, Integer id) {
		Set<ContactEntity> contacts = userEntity.getContacts();
		for (ContactEntity contactEntity : contacts) {
			if (contactEntity.getId().equals(id)) {
				return contactEntity;
			}
		}
		return null;
	}

	/* Supprimer un contact */
//	@DeleteMapping(value = "existing/{id}")
//	@ResponseStatus(HttpStatus.OK)
//	public Iterable<ContactEntity> deleteExistingContact(@PathVariable("id") Integer id) {
//		contactDao.deleteContact(id);
//		return findAllContacts();
//	}

	/* Supprimer un contact */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<ContactEntity> deleteContactFromList(@RequestBody ContactEntity contactEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity userEntity = userDao.findByEmail(user.getUsername());
		ContactEntity contact = getContactFromId(userEntity, contactEntity.getId());
		userEntity.getContacts().remove(contact);
		userDao.save(userEntity);
		return findAllContacts();
	}

}
