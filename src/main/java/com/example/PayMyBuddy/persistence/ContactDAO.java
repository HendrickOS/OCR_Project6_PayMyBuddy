package com.example.PayMyBuddy.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.ContactEntity;

@Component
public class ContactDAO {
	@Autowired
	ContactRepository contactRepository;
	
	public ContactEntity save(ContactEntity contactEntity) {
		return contactRepository.save(contactEntity);
	}
}
