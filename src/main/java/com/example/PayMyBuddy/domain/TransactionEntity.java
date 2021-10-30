package com.example.PayMyBuddy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int montant;

//	private ContactEntity contactEntity;

//	@OneToOne
//	@JoinColumn(name = "user_id", nullable = true)
//	private UserEntity userEntity;
//
	@OneToOne
	@JoinColumn(name = "contact_id", nullable = true)
	private ContactEntity contactEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

//	public UserEntity getUserEntity() {
//		return userEntity;
//	}
//
//	public void setUserEntity(UserEntity userEntity) {
//		this.userEntity = userEntity;
//	}
//
//	public ContactEntity getContactEntity() {
//		return contactEntity;
//	}
//
//	public void setContactEntity(ContactEntity contactEntity) {
//		this.contactEntity = contactEntity;
//	}

}
