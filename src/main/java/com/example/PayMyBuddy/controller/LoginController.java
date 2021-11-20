package com.example.PayMyBuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.PayMyBuddy.config.JwtConfig;
import com.example.PayMyBuddy.domain.LoginInformation;
import com.example.PayMyBuddy.security.AuthenticationSystem;
import com.example.PayMyBuddy.security.jwt.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

	@Autowired
	AuthenticationSystem authentication;

	@Autowired
	JwtConfig jwtConfig;

	@PostMapping("/")
	public String login(@RequestBody LoginInformation information) {
		User user = authentication.checkUser(information.getUsername(), information.getPassword());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bad login information");

		} else
			try {
				return JwtUtil.generateToken(user.getUsername(), jwtConfig.getSigningKey(),
						jwtConfig.getTokenValidity());
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occured");
			}
	}

}
