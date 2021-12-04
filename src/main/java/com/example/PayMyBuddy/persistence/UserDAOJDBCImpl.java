package com.example.PayMyBuddy.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.UserEntity;

@Component
public class UserDAOJDBCImpl implements UserDao {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserEntity save(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> result = new ArrayList<>();
		Iterable<UserEntity> findAll = userRepository.findAll();
		for (Iterator<UserEntity> iterator = findAll.iterator(); iterator.hasNext();) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserEntity findById(Integer id) {
		return userRepository.findById(id).get();
	}
}
