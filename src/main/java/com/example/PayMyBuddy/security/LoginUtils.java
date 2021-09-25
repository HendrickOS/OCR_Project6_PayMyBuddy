package com.example.PayMyBuddy.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class LoginUtils {

	public static User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			if (authentication.getPrincipal() instanceof User) {
				return (User) authentication.getPrincipal();
			}
		}
		return null;
	}

}
