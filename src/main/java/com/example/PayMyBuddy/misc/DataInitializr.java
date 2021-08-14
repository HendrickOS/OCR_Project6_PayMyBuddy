package com.example.PayMyBuddy.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.ContactDAO;
import com.example.PayMyBuddy.persistence.UserDAO;

@Component
public class DataInitializr {
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ContactDAO contactDAO;
	
	@PostConstruct
	public void init() {
		UserEntity user = new UserEntity();
		user.setFirstName("Hendrick");
		user.setLastName("Osseux");
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
