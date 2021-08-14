package com.example.PayMyBuddy.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.PayMyBuddy.domain.ContactEntity;

public interface ContactRepository extends CrudRepository<ContactEntity, Integer> {

}
