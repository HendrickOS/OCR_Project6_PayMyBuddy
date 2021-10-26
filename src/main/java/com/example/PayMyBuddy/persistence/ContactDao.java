package com.example.PayMyBuddy.persistence;

import java.util.Set;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.UserEntity;

public interface ContactDao {

	Iterable<ContactEntity> findAll();

//	ContactEntity addContact(ContactEntity contactEntity);

	ContactEntity updateContact(ContactEntity contactEntity) throws PersistenceException;

	void deleteContact(Integer id);

	Set<ContactEntity> addContact(UserEntity userEntity, ContactEntity contactEntity);

}
