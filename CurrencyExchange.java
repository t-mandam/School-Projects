import java.util.Scanner;
public class CurrencyExchange {

	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Assignment 2
		// Written by: Theo-Dayan Mandamiento Rodriguez 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program trades foreign currencies and CAD with the user.
		// It offers buying and selling USD, EUR, GBP, JYP, and AUD.
		// --------------------------------------------------------------
		
		// Welcome the user
		System.out.println("*******************************************"+
				"*********************************************"+"\n\t\t\t"
				+ "Montreal Currency Exchange Shop!" +"\n"+
				"*******************************************" +
				"*********************************************" + "\n\n"
				+ "Welcome to the Montreal Currency Exchange Shop!");
		
		// Declare the Scanner
		Scanner kb = new Scanner(System.in);
		
		// Declare exchange rates
		// Buy rates
		final double USD_b=1.30, EUR_b=1.55, GBP_b=1.80, 
				JPY_b=0.012, AUD_b=1.00;
		// Sell rates
		final double USD_s=1.25, EUR_s=1.50, GBP_s=1.75, 
				JPY_s=0.01, AUD_s=0.95;
		
		// Declare transaction, currency, amount and price variables
		String transaction, currency;
		double amount, price = 0;
		
		// Prompt the user for transaction intention
		System.out.print("Do you want to buy foreign currency (B)" +
						" or sell foreign currency (S)? ");
		transaction = kb.next();
		
		// Buying foreign currency
		if (transaction.equalsIgnoreCase("B"))
		{
			// Prompt for foreign currency and desired foreign amount
			System.out.print("Enter the currency you want to buy"+
							" (USD, EUR, GBP, JPY, AUD): ");
			currency = kb.next().toUpperCase();
			System.out.print("Enter the amount of " + currency +
							" you want to buy: ");
			amount = kb.nextDouble();
			// Calculate price of desired foreign amount
			switch (currency)
			{
				case "USD":
					price = amount * USD_b;
					break;
				case "EUR":
					price = amount * EUR_b;
					break;
				case "GBP":
					price = amount * GBP_b;
					break;
				case "JPY":
					price = amount * JPY_b;
					break;
				case "AUD":
					price = amount * AUD_b;
					break;
			}
			// Displaying transaction
			System.out.printf("You need to spend %.2f CAD to receive %.2f "
							+currency+ "\n", price, amount);
		}
		
		// Selling foreign currency
		if (transaction.equalsIgnoreCase("S"))
		{
			// Prompt for desired CAD amount and target currency
			System.out.print("Enter the CAD amount you would like "
							+ "to receive: ");
			amount = kb.nextDouble();
			System.out.print("Enter the target currency to sell"+
							" (USD, EUR, GBP, JPY, AUD): ");
			currency = kb.next().toUpperCase();
			// Calculate price of desired CAD amount
			switch (currency)
			{
				case "USD":
					price = amount / USD_s;
					break;
				case "EUR":
					price = amount / EUR_s;
					break;
				case "GBP":
					price = amount / GBP_s;
					break;
				case "JPY":
					price = amount / JPY_s;
					break;
				case "AUD":
					price = amount / AUD_s;
					break;
			}
			System.out.printf("You will need to spend %.2f " +currency+
					" to receive %.2f CAD \n", price, amount);
		}
		
		// Thank the user
		System.out.print("Thank you for visiting!");
		
		// close Scanner
		kb.close();
	}

}
