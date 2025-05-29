import java.util.Scanner;
public class InventoryManagement {

	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Assignment 3
		// Written by: Theo-Dayan Mandamiento Rodriguez 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program serves as an Inventory Management application.
		// It offers to create, edit, and check an inventory.
		// --------------------------------------------------------------
		
		// Declare Scanner and variables
		Scanner kb = new Scanner(System.in);
		final int NUMBER_OF_PRODUCTS = 5;
		int choice, min_QTY, number_product;
		String search_product;
		double max_price;
		boolean exit = false;
		
		// Declare arrays
		String[] products = new String[NUMBER_OF_PRODUCTS];
		double[] prices = new double[NUMBER_OF_PRODUCTS];
		int[] quantities = new int[NUMBER_OF_PRODUCTS];
		
		// Welcome user
		System.out.println("Welcome to Inventory Management System" + "\n");
		
		// Create and save inventory
		System.out.println("Enter details for 5 products (price, quantity, name):");
		for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
			System.out.println("Product " + (i+1) + ":\n" +
					"Enter product details (price, quantity, name):");
			prices[i] = kb.nextDouble();
			quantities[i] = kb.nextInt();
			products[i] = kb.nextLine();
		}
		
		// Menu of options
		do {
			System.out.print("\n" + "Inventory Management Menu:" +
					"\n\t" + "1. Display information of all products" +
					"\n\t" + "2. Update the quantity of a product" +
					"\n\t" + "3. Search for a product by name" +
					"\n\t" + "4. Find the product with the lowest quantity" +
					"\n\t" + "5. Find the product with the highest price" +
					"\n\t" + "6. Exit" + "\n" +
					"Enter your choice: ");
			choice = kb.nextInt();
			switch (choice) {
			
				// Display product list
				case 1:
					System.out.println("Product List:");
					for (int i = 0; i < NUMBER_OF_PRODUCTS; i++)
						System.out.printf("\t" + "Product " +(i+1)+ ":" +
								"\n\t" + "Name:" + products[i] +
								"\n\t" + "Price: $%.1f" + 
								"\n\t" + "Quantity: " + quantities[i] +
								"\n" + "-------------------------" + "\n",
								prices[i]);
					break;
					
				// Update product quantity
				case 2:
					System.out.print("Enter the product number (1-5) to update quantity: ");
					number_product = kb.nextInt() - 1;
					System.out.print("Enter new quantity for" + products[number_product] + ": ");
					quantities[number_product] = kb.nextInt();
					System.out.println("Quantity updated successfully!");
					break;
					
				// Product search by name
				case 3:
					System.out.println("Enter the name of the product to search for:");
					search_product = kb.nextLine();
					search_product = kb.nextLine();
					System.out.println();
					for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
						// Display product info
						if (products[i].equalsIgnoreCase(" " + search_product))
							System.out.printf("Product Found:" + "\n\t" +
									"Name:" + products[i] + "\n\t" +
									"Price: $%.1f" + "\n\t" +
									"Quantity: " + quantities[i] + "\n", prices[i]);
					}
					break;
					
				// Find lowest quantity product
				case 4:
					min_QTY = quantities[0];
					number_product = 0;
					for (int i = 1; i < NUMBER_OF_PRODUCTS; i++)
						if (min_QTY > quantities[i]) {
							min_QTY = quantities[i];
							number_product = i;
						}
					// Display product info
					System.out.printf("\n" + "Product with the Lowest Quantity:" +
							"\n\t" + "Name:" + products[number_product] + 
							"\n\t" + "Price: $%.1f" + 
							"\n\t" + "Quantity: " + quantities[number_product] + "\n",
							prices[number_product]);
					break;
					
				// Find most expensive product
				case 5:
					max_price = prices[0];
					number_product = 0;
					for (int i = 1; i < NUMBER_OF_PRODUCTS; i++)
						if (max_price < prices[i]) {
							max_price = prices[i];
							number_product = i;
						}
					// Display product info
					System.out.printf("\n" + "Product with the Highest Price:" +
							"\n\t" + "Name:" + products[number_product] + 
							"\n\t" + "Price: $%.1f" + 
							"\n\t" + "Quantity: " + quantities[number_product] + "\n",
							prices[number_product]);
					break;
					
				// Exit
				case 6:
					exit = true;
					break;
			}
		}
		while (!exit);
		
		// Goodbye
		System.out.print("\n" + "Goodbye!");
		
		// close Scanner
		kb.close();
	}

}
