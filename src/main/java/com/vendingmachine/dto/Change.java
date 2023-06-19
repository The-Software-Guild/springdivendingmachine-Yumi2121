package com.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

//PENNIES(1), NICKLE(5), DIME(10), QUARTER(25), DOLLAR(100); 
public class Change {
	private int dollar;
	private int quarters;
	private int dimes;
	private int nickels;
	private int pennies;
	

	public Change(BigDecimal balance) {
		balance = balance.multiply(new BigDecimal(100));
		int balance_in_pennies = balance.intValue();
		
		if (balance_in_pennies > 1) {
			dollar = balance_in_pennies / Coin.DOLLAR.getValue();
			balance_in_pennies %= Coin.DOLLAR.getValue();
			quarters = balance_in_pennies / Coin.QUARTER.getValue();
			balance_in_pennies %= Coin.QUARTER.getValue();
			dimes = balance_in_pennies / Coin.DIME.getValue();
			balance_in_pennies %= Coin.DIME.getValue();
			nickels = balance_in_pennies / Coin.NICKLE.getValue();
			pennies %= Coin.NICKLE.getValue();
		
		} else if (balance_in_pennies < 1) {
			quarters = balance_in_pennies / Coin.QUARTER.getValue();
			balance_in_pennies %= Coin.DOLLAR.getValue();
			dimes = balance_in_pennies / Coin.DIME.getValue();
			balance_in_pennies %= Coin.DIME.getValue();
			nickels = balance_in_pennies / Coin.NICKLE.getValue();
			pennies %= Coin.NICKLE.getValue();
		}
		
	}

	public int getQuarters() {
		return quarters;
	}

	public int getDimes() {
		return dimes;
	}

	public int getNickels() {
		return nickels;
	}

	public int getPennies() {
		return pennies;
	}

	@Override
	public String toString() {
		return "Change [dollar=" + dollar + ", quarters=" + quarters + ", dimes=" + dimes + ", nickels=" + nickels
				+ ", pennies=" + pennies + "]";
	}
	
	
	
	
}
