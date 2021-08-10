package com.example.PayMyBuddy.misc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.UserDAO;

@Component
public class DataInitializr {
	@Autowired
	UserDAO userDAO;
	
	@PostConstruct
	public void init() {
		UserEntity user = new UserEntity();
		user.setFirstName("Hendrick");
		user.setLastName("Osseux");
		userDAO.save(user);
	}

}
