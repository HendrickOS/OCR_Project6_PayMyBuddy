package com.example.PayMyBuddy.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.PayMyBuddy.domain.ContactEntity;

//@RestController
public class LoginController {

	@Autowired
	ContactController contactController;

	@RolesAllowed("USER")
	@RequestMapping("/*")
	public String getUser() {
		return "Welcome User";
	}

	@RolesAllowed({ "USER", "ADMIN" })
	@RequestMapping("/admin")
	public Iterable<ContactEntity> getAdmin() {
		return contactController.findAll();
	}

}
