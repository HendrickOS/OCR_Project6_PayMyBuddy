package com.example.PayMyBuddy.misc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.persistence.UserDao;

@Component
public class DataInitializr {
	@Autowired
	UserDao userDAO;

//	@PostConstruct
//	public void init() {
//		UserEntity user = new UserEntity();
//		user.setPassword("user123");
//		user.setEmail("springuser");
//
//		ContactEntity contactEntity = new ContactEntity();
//		contactEntity.setUsername("toto");
//		ContactEntity contactEntity2 = new ContactEntity();
//		contactEntity2.setUsername("tutu");
//		Set<ContactEntity> contacts = new HashSet<>();
//		contacts.add(contactEntity);
//		contacts.add(contactEntity2);
//		user.setContacts(contacts);
//		userDAO.save(user);
//	}

//	@PostConstruct
//	public void init3() {
//		UserEntity findByEmail = userDAO.findByEmail("springuser");
//		System.out.println("coucou");
//	}

//	@PostConstruct
//	public void init2() {
//		ContactEntity contact = new ContactEntity();
//		contact.setConnections("Hendrick");
//		contact.setDescription("JAVA Developer");
//		contact.setAmount(50);
//		contactDAO.save(contact);
//	}

}
