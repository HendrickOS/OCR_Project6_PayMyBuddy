package com.example.PayMyBuddy.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class TransactionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int montant;

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "Transaction_User", joinColumns = { @JoinColumn(name = "transaction_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private UserEntity userEntity = new UserEntity();

	@OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "Transaction_User", joinColumns = { @JoinColumn(name = "transaction_id") }, inverseJoinColumns = {
			@JoinColumn(name = "contact_id") })
	private ContactEntity contactEntity = new ContactEntity();

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

}
