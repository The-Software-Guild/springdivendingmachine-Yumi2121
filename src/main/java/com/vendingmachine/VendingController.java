package com.vendingmachine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.vendingmachine.dto.Change;
import com.vendingmachine.dto.Item;
import com.vendingmachine.exception.InsufficientFundsException;
import com.vendingmachine.exception.NoItemInventoryException;
import com.vendingmachine.service.IService;

@Controller
public class VendingController {
	
	static Scanner sc = new Scanner(System.in);
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
	static IService service= context.getBean("service", IService.class);
	
	private BigDecimal balance;
	static float userPayment = 0;
	
	
	public static void displayItemList() {
		
		List<Item> list = service.getAllItemswithInventory();
		list.forEach(n -> System.out.println(n));
	}
	
	
	public static float checkPayment() {
		
		do {
			System.out.println("Please insert coins before we start!");
			userPayment = sc.nextFloat();
			
			List<Item> list = service.getAllItemswithInventory();
			
			list.sort(Comparator.comparing(Item::getCost));	
			BigDecimal cheapest = list.get(0).getCost();
			
			
			BigDecimal userPaymentbd = new BigDecimal(Float.toString(userPayment));
			if (userPaymentbd.compareTo(cheapest) < 0) {
				System.out.println("Insufficient fund, please try again!");
			} else {
				return userPayment;
			}
			
		} while(true);
		
	}
	
	public static void selectAndPay(float userPayment) throws InsufficientFundsException, NoItemInventoryException {
		Item item;
		
		do {
			
			System.out.println("Please select the item id to start the purchase: (Only one item can be vended at a time)");
			int itemId = sc.nextInt();	
			item = service.getItemById(itemId);
			System.out.println("please choose valid item id.");
			
		} while(item == null);
			
		
			if (item!= null && item.getInventory() != 0) {
		
				BigDecimal itemPrice = item.getCost();
				BigDecimal userPaymentD = BigDecimal.valueOf(userPayment);
				
				int compareResult = userPaymentD.compareTo(itemPrice);
				
				if (compareResult == -1) {
					System.out.println("Insufficient fund, please try again");
				} else {
					BigDecimal difference = userPaymentD.subtract(itemPrice);
					service.soldItemById(item.getId());
					Change change = new Change(difference);
					System.out.println("please take the change: " + change.toString());
					System.out.println("Thank you for the purchase!");
				}
			}
			else {
				System.out.println("please choose valid item id and the item got enough inventory.");
				
			}
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		IService service= context.getBean("service", IService.class);
		
//		List<Item> list = new ArrayList<>();
//		BigDecimal b1 = new BigDecimal(0.55).setScale(2, RoundingMode.DOWN);
//		BigDecimal b2 = new BigDecimal(0.70).setScale(2, RoundingMode.DOWN);
//		BigDecimal b3 = new BigDecimal(1.95).setScale(2, RoundingMode.DOWN);
//		BigDecimal b4 = new BigDecimal(0.65).setScale(2, RoundingMode.DOWN);
//		BigDecimal b5 = new BigDecimal(0.85).setScale(2, RoundingMode.DOWN);
//		
//		Item i1 = new Item(1, "item1", b1, 1 );
//		Item i2 = new Item(2, "item2", b2, 5 );
//		Item i3 = new Item(3, "item3", b3, 7 );
//		Item i4 = new Item(4, "item4", b4, 9 );
//		Item i5 = new Item(5, "item5", b5, 7 );
//		
//		list.add(i1);
//		list.add(i2);
//		list.add(i3);
//		list.add(i4);
//		list.add(i5);
//		
//		service.addItem(list);
		
//		VendingController vc = new VendingController();
		
//-------------------------------------------------------------------------------
//      testing code
//		service.soldItemById(2);
//		service.updateItemQuantity(3, 9);
//		
//		List<Item> list2 = service.getAllItemswithInventory();
//		System.out.println(list2);
//-------------------------------------------------------------------------------
		
		
	
			System.out.println("\n *** Welcome to My Vending machine ***");
			System.out.println("Below is our available item list");
			displayItemList();
			checkPayment();
			
//			List<Item> list2 = service.getAllItemswithInventory();
//			BigDecimal lowestCost = list2.sort(Comparator.comparing(Item::getCost)).get(0);
			
			
			try {
				selectAndPay(userPayment);
			} catch (InsufficientFundsException e) {
				System.out.println("Insufficient fund, please try again!");
			} catch (NoItemInventoryException e) {
				System.out.println("Please choose the item got enough inventory!");
			}
			
		
		
	}

}
