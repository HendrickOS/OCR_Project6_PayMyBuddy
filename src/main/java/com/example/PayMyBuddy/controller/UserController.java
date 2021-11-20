package com.example.PayMyBuddy.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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

import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.UserDao;
import com.example.PayMyBuddy.security.LoginUtils;
import com.example.PayMyBuddy.security.Roles;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserDao userDao;

	/* Récupérer le solde du user connecté */
	@GetMapping("/solde")
	public Integer getUserSolde() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		return currentUser.getSolde();
	}

	/* Récupérer le username du user connecté */
	@GetMapping("/username")
	public String getUserUsername() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		return currentUser.getUsername();
	}

	@GetMapping("/list")
	public Set<UserEntity> getAllUsersButMe() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		List<UserEntity> findAll = userDao.findAll();
		Set<UserEntity> result = new TreeSet<>();
		for (UserEntity userEntity : findAll) {
			if (userEntity.compareTo(currentUser) != 0) {
				result.add(userEntity);
			}
		}
		return result;
	}

	/* Créer un user */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewUser(@RequestBody UserEntity userEntity) {
		userEntity.setSolde(0);
		userDao.save(userEntity);
		System.out.print("User crée");
		return;
	}

	/* Mettre à jour le user connecté */
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateInfoUser(@RequestBody UserEntity userEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity userCurrent = userDao.findByUsername(user.getUsername());
		if (userEntity.getUsername() != null) {
			userCurrent.setUsername(userEntity.getUsername());
		}
		if (userEntity.getEmail() != null) {
			userCurrent.setEmail(userEntity.getEmail());
		}
		if (userEntity.getSolde() != null) {
			userCurrent.setSolde(userEntity.getSolde());
		}
		if (userEntity.getPassword() != null) {
			userCurrent.setPassword(userEntity.getPassword());
		}
		userDao.save(userCurrent);
		System.out.print("User mis à jour");
		return;
	}

	/* Supprimer le user connecté */
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteCurrentUser(@RequestBody UserEntity userEntity) {

		return;
	}

	/***************************************************************/
	/* SUPPRIMER CONTACTENTITY => AJOUTER UN CHAMP SET<USERENTITY> À USERENTITY */
	/* AGIR SUR CE SET QUI SERA LA NOUVELLE LISTE DE CONTACT */
	/***************************************************************/

	/* Récupérer la liste des contacts du user connecté */
	@Secured({ Roles.USER })
	@GetMapping("/contacts/list")
	public Iterable<UserEntity> findAllUserContacts() {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		return currentUser.getContacts();
	}

	/* Ajouter un contact à la liste de contact du user connecté */
	@PostMapping("/contacts")
	@ResponseStatus(HttpStatus.CREATED)
	public Iterable<UserEntity> addNewContact(@RequestBody UserEntity userEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		currentUser.getContacts().add(userEntity);
		userDao.save(currentUser);
		return findAllUserContacts();
	}

	/*
	 * PAS DE UPDATE SUR UN USERCONTACT SPECIFIQUE CAR UPDATEUSER SE REPERCUTE SUR
	 * LE USERCONTACT DU USER CONNECTE
	 */

	/* Supprimer un contact de la liste de contact du user connecté */
	@DeleteMapping("/contacts")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<UserEntity> deleteContactFromList(@RequestBody UserEntity userEntity) {
		User user = LoginUtils.getLoggedUser();
		UserEntity currentUser = userDao.findByUsername(user.getUsername());
		Set<UserEntity> contacts = currentUser.getContacts();
		for (UserEntity contact : contacts) {
			if (contact.getId().equals(userEntity.getId())) {
				currentUser.getContacts().remove(contact);
			}
		}
		userDao.save(currentUser);
		return findAllUserContacts();
	}

}