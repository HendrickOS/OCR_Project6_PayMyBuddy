package com.example.PayMyBuddy.persistence;

import java.util.List;

import com.example.PayMyBuddy.domain.ContactEntity;

public interface ContactDao {

	List<ContactEntity> findAll();

	ContactEntity addContact(ContactEntity contactEntity);

	ContactEntity updateContact(ContactEntity contactEntity) throws PersistenceException;

	void deleteContact(Integer id);

}
