package com.example.PayMyBuddy.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.PayMyBuddy.domain.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

}
