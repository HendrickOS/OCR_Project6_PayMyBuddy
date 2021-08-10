package com.example.PayMyBuddy.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.UserEntity;

@Component
public class UserDAO {
	@Autowired
	UserRepository userRepository;
	
	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
}
