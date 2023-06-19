package com.vendingmachine.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vendingmachine.dto.Item;

@Component
public class VendingDao implements IDao {
	
	private List<Item> itemList;
	
	private void save() {
		try {
	        FileOutputStream fout;
			fout = new FileOutputStream("vendingmachine.txt");
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(itemList);
			oout.close();
	        fout.close();
		} 
		catch (Exception e) {
			// throw fail to save file exception
			System.out.println("Failed to save vending machine item list");
		}
	}

	private void open() {
		try {
			FileInputStream fin;
			fin = new FileInputStream("vendingmachine.txt");
			ObjectInputStream oin = new ObjectInputStream(fin);
			itemList = (ArrayList<Item>)oin.readObject();				
			oin.close();
			fin.close();
		}
		catch (Exception e) {
			System.out.println("Failed to load vending machine data. Creating a new one instead");
			itemList = new ArrayList<Item>();
		}
	}

	@Override
	public void updateItemQuantity(int itemId, int NewQuatity) {
		boolean found = false;
		open();
		
		for( int i=0; i<itemList.size(); i++) {
			var updatedItem = itemList.get(i);
			
			if (updatedItem.getId() == itemId) {
				updatedItem.setInventory(NewQuatity);
				found = true;
				break;
			}
		}
		if (found = false) {
			System.out.println("There is no matching item can be found! ");
		}
		save();

	}

	@Override
	public Item getItemById(int id) {
		open();
		
		for (Item item : itemList) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	
	@Override
	public List<Item> getAllItemsWithInventory() {
		open();
		
		List<Item> listWithInventory = new ArrayList<>();
		
		for (Item item : itemList) {
			if (item.getInventory() != 0) {
				listWithInventory.add(item);
			}
		}
		return listWithInventory;
		
	}

	@Override
	public void soldItemById(int id) {
		open();
		
		for (Item item : itemList) {
			if (item.getId() == id) {
				int newInventory = item.getInventory();
				item.setInventory(newInventory- 1);
			}
		}		
		save();	
	}

	@Override
	public void addItem(Item item) {
		open();
		itemList.add(item);
		save();
		
	}


}
