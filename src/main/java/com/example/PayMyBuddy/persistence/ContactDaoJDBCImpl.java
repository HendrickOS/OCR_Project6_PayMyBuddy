package com.example.PayMyBuddy.persistence;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.PayMyBuddy.domain.ContactEntity;
import com.example.PayMyBuddy.domain.UserEntity;

@Component
public class ContactDaoJDBCImpl implements ContactDao {
	@Autowired
	ContactRepository contactRepository;

	public ContactEntity save(ContactEntity contactEntity) {
		return contactRepository.save(contactEntity);
	}

	@Override
	public Iterable<ContactEntity> findAll() {
		return contactRepository.findAll();
	}

	@Override
	public ContactEntity updateContact(ContactEntity contactEntity) throws PersistenceException {
		Integer id = contactEntity.getId();
		if (id != null) {
			ContactEntity existingContact = contactRepository.findById(id).get();
			if (existingContact != null) {
				return contactRepository.save(contactEntity);
			} else {
				throw new PersistenceException("No corresponding entity", HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new PersistenceException("Id must be present", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteContact(Integer id) {
		contactRepository.deleteById(id);
	}

	@Override
	public Set<ContactEntity> addContact(UserEntity userEntity, ContactEntity contactEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactEntity findById(Integer id) {
		return contactRepository.findById(id).get();
	}
}
