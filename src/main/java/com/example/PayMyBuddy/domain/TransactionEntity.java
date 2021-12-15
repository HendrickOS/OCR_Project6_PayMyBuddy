package com.example.PayMyBuddy.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int montant;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", nullable = true)
//	private UserEntity userEntity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "Transactions_UserToContact", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private UserEntity userEntity;

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

//	public ContactEntity getContactEntity() {
//		return contactEntity;
//	}
//
//	public void setContactEntity(ContactEntity contactEntity) {
//		this.contactEntity = contactEntity;
//	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}
