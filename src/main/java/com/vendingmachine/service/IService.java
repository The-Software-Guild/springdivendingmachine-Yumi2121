package com.vendingmachine.service;

import java.util.List;

import com.vendingmachine.dto.Item;

public interface IService {
	public Item getItemById(int id);
	public List<Item> getAllItemswithInventory();
	
	public void addItem(List<Item> list);
	public void updateItemQuantity(int itemId, int NewQuatity);	
	public void soldItemById(int id);
}
