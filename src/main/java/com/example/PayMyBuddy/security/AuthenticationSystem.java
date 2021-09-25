package com.example.PayMyBuddy.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSystem {

	public static Map<String, User> users = new HashMap<>();

	@PostConstruct
	public void createFakeUsers() {

		List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new SimpleGrantedAuthority(Roles.ADMIN));

		List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new SimpleGrantedAuthority(Roles.USER));

		users.put("springadmin", new User("springadmin", "admin123", adminAuthorities));
		users.put("springuser", new User("springuser", "user123", userAuthorities));

	}

	public User checkUser(String username, String password) {
		User user = users.get(username.toLowerCase());
		if (user == null) {
			return null;
		} else {
			String aux = user.getPassword();
			if (aux.equalsIgnoreCase(password)) {
				return user;
			}
			return null;
		}
	}

}
