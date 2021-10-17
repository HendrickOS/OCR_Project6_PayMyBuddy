package com.example.PayMyBuddy.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.UserDao;

@Component
public class AuthenticationSystem {

	public static Map<String, User> admins = new HashMap<>();

	@Autowired
	UserDao userDao;

	@PostConstruct
	public void createFakeUsers() {

		List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new SimpleGrantedAuthority(Roles.ADMIN));
		adminAuthorities.add(new SimpleGrantedAuthority(Roles.USER));

		admins.put("springadmin", new User("springadmin", "admin123", adminAuthorities));

	}

	public User checkUser(String username, String password) {
		User user = admins.get(username.toLowerCase());
		if (user == null) {
			UserEntity userEntity = userDao.findByEmail(username);
			if (userEntity != null) {
				List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
				userAuthorities.add(new SimpleGrantedAuthority(Roles.USER));
				user = new User(userEntity.getEmail(), userEntity.getPassword(), userAuthorities);
			}
		}
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

	public User getUserFromName(String name) {
		User user = admins.get(name.toLowerCase());
		if (user == null) {
			UserEntity userEntity = userDao.findByEmail(name);
			if (userEntity != null) {
				List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
				userAuthorities.add(new SimpleGrantedAuthority(Roles.USER));
				user = new User(userEntity.getEmail(), userEntity.getPassword(), userAuthorities);
			}
		}
		return user;
	}

}
