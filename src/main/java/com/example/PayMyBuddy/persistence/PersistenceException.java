package com.example.PayMyBuddy.persistence;

import org.springframework.http.HttpStatus;

public class PersistenceException extends Exception {

	private final HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public PersistenceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
}
