package com.vendingmachine.exception;

// thrown when the user tries to purchase an item but doesn't deposit enough money 
public class InsufficientFundsException extends Exception {
	public InsufficientFundsException(String message) {
		super(message);
	}
}
