package com.example.PayMyBuddy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayMyBuddy.domain.UserEntity;
import com.example.PayMyBuddy.persistence.UserDao;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserService {

	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<UserEntity> listUsers() {
		return userDao.findAll();
	}
}
