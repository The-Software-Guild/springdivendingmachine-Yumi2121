package com.vendingmachine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.vendingmachine.dao.IDao;
import com.vendingmachine.dto.Item;

@Service
public class VendingService implements IService {
	
	@Autowired
	private IDao dao;

	public Item getItemById(int id) {
		Item item = dao.getItemById(id);
		return item;
	}

	public void updateItemQuantity(int itemId, int NewQuatity) {
		dao.updateItemQuantity(itemId, NewQuatity);

	}

	@Override
	public List<Item> getAllItemswithInventory() {
		List<Item> itemList = new ArrayList<>();
		itemList = dao.getAllItemsWithInventory();
		return itemList;
	}

	@Override
	public void soldItemById(int id) {
		dao.soldItemById(id);
		
	}

	@Override
	public void addItem(List<Item> list) {
		
		for(Item item : list) {
			dao.addItem(item);
		}
 		
		
	}



}
