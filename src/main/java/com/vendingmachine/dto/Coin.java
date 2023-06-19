package com.vendingmachine.dto;

public enum Coin {
	PENNIES(1), 
	NICKLE(5), 
	DIME(10), 
	QUARTER(25), 
	DOLLAR(100); 
	

	private int value;

	Coin(int value) {
		this.value = value;
	} 
	
	public int getValue() {
		return value;
	}

}
