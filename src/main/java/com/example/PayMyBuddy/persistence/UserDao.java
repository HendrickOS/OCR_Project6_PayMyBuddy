package com.example.PayMyBuddy.persistence;

import java.util.List;

import com.example.PayMyBuddy.domain.UserEntity;

public interface UserDao {

	UserEntity save(UserEntity user);

	List<UserEntity> findAll();

	UserEntity findByUsername(String username);

}
