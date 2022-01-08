package com.example.PayMyBuddy.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class UserEntity implements Comparable<UserEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String email;
	private String password;
	private Double solde;
	private String description;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "Contacts_of_User", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<UserEntity> contacts = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Set<TransactionEntity> transactions = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(double d) {
		this.solde = d;
	}

	public Set<UserEntity> getContacts() {
		return contacts;
	}

	public void setContacts(Set<UserEntity> contacts) {
		this.contacts = contacts;
	}

	public Set<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<TransactionEntity> transactions) {
		this.transactions = transactions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int compareTo(UserEntity o) {
		return getUsername().compareToIgnoreCase(o.getUsername());
	}

	public boolean hasContact(UserEntity userEntity) {
		for (UserEntity contact : contacts) {
			if (contact.getId().equals(userEntity.getId())) {
				return true;
			}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}