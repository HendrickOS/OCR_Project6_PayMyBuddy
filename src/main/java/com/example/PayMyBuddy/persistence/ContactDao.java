package com.example.PayMyBuddy.persistence;

import com.example.PayMyBuddy.domain.ContactEntity;

public interface ContactDao {

	Iterable<ContactEntity> findAll();

	ContactEntity addContact(ContactEntity contactEntity);

	ContactEntity updateContact(ContactEntity contactEntity) throws PersistenceException;

	void deleteContact(Integer id);

}
