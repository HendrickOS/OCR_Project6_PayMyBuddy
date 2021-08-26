package com.example.PayMyBuddy.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.ContactDaoJDBCImpl;
import com.example.PayMyBuddy.persistence.UserDao;

//@Component
public class DataInitializr {
	@Autowired
	UserDao userDAO;

	@Autowired
	ContactDaoJDBCImpl contactDAO;

	@PostConstruct
	public void init() {
		UserEntity user = new UserEntity();
		user.setFirstName("Hendrick");
		user.setLastName("Osseux");
		user.setEmail("hendrickosseux@email.com");
		userDAO.save(user);
	}

	@PostConstruct
	public void init2() {
		ContactEntity contact = new ContactEntity();
		contact.setConnections("Hendrick");
		contact.setDescription("JAVA Developer");
		contact.setAmount(50);
		contactDAO.save(contact);
	}

}
