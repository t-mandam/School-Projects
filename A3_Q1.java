import java.util.Scanner;
public class A3_Q1 {

	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Assignment 3
		// Written by: Theo-Dayan Mandamiento Rodriguez 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program simulates an ATM machine. It offers simple
		// transactions such as Deposit, Withdrawal and Check Balance.
		// --------------------------------------------------------------
			
		// Declare variables
		int balance = 0, choice, amount, num_5$, num_10$, num_50$,
				num_100$, residual;
		Scanner kb = new Scanner(System.in);
		boolean exit = false;
		
		// Declare limits
		final int BILL_MULTIPLE = 5, BILL_100=100, BILL_50=50,
				BILL_10=10, BILL_5=5, DEPOSIT_MAX=10000,
				WITHDRAW_MAX=1000;
		
		// Welcome the user
		System.out.println("Welcome to the Best Bank ATM!");
		
		// Show menu and prompt for desired action
		do {
			System.out.println("--------------------------------------");
			System.out.print("Please choose an option:" + "\n\t"+
					"1. Check Balance" + "\n\t"+
					"2. Deposit" + "\n\t"+
					"3. Withdraw" + "\n\t"+
					"4. Exit" + "\n"+
					"Enter your choice: ");
			choice = kb.nextInt();
			
			switch (choice) {
				// 1. Check Balance
				case 1: 
					System.out.printf("Your balance is: $%.2f%n",
							(balance*1.0));
					break;
					
				// 2. Deposit
				case 2:
					System.out.print("Enter amount to deposit: ");
					amount = kb.nextInt();
					
					// Check criteria for deposit
					if (amount % BILL_MULTIPLE == 0 && 
							amount <= DEPOSIT_MAX && amount > 0) {
						// Deposit in account
						balance += amount;
						System.out.println("Deposit succesful!"+"\n");
					}
					
					// Criteria not met
					else
						System.out.println("Invalid deposit amount! "
								+"You can only deposit amounts that "
								+"are multiples of 5 and inferior to"
								+" 10,000$." + "\n");
					break;
					
				// 3. Withdraw
				case 3:
					System.out.print("Enter amount to withdraw "+
								"(multiples of 5, 10, 50 or 100): ");
					amount = kb.nextInt();
					
					// Check criteria for withdrawal
					if (amount%BILL_MULTIPLE == 0 && amount<=WITHDRAW_MAX 
							&& amount<balance && amount>0) {
						// Calculate bills to give
						num_100$ = amount / BILL_100;
						residual = amount - num_100$ * BILL_100;
						
						num_50$ = residual / BILL_50;
						residual %= BILL_50;
						
						num_10$ = residual / BILL_10;
						residual %= BILL_10;
						
						num_5$ = residual / BILL_5;
						
						System.out.println("You will receive:" + "\n\t"
								+num_100$ + " bill(s) of $100" + "\n\t"
								+ num_50$ + " bill(s) of $50" + "\n\t"
								+ num_10$ + " bill(s) of $10" + "\n\t"
								+ num_5$ + " bill(s) of $5");
						
						// Withdraw from account
						balance -= amount;
						System.out.print("Withdrawal succesful!");
					}
					
					// Criteria not met
					else {
							System.out.print("Invalid withdrawal amount!");
						if (amount > balance)
							System.out.print(" Insufficient funds.");
						if (amount % BILL_MULTIPLE != 0)
							System.out.print(" You can only withdraw "+
									"amounts that are multiples of 5, "
									+"10, 50, or 100.");
						if (amount > WITHDRAW_MAX)
								System.out.print(" You can only withdraw"
									+" amounts inferior to 1,000$.");
					}
					System.out.println("\n");
					break;
					
				// 4. Exit
				case 4:
					exit = true;
					break;
					
				// Unrecognized option
				default: 
					System.out.println("Invalid choice! Please try"
								+" again." + "\n");
			}
		}
		while (!exit);
		
		// Goodbye to user
		System.out.print("Goodbye!");
		
		// Close Scanner
		kb.close();
	}

}
