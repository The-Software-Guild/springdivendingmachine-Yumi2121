package com.vendingmachine.dao;

import java.util.List;

import com.vendingmachine.dto.Item;

public interface IDao {
	public void addItem(Item item);
	public void updateItemQuantity(int itemId, int NewQuatity);
	public void soldItemById(int id);
	public Item getItemById(int id);
	
	public List<Item> getAllItemsWithInventory();
	
}
