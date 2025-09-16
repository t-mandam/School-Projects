import java.util.Scanner;
public class Bookstore {

	public static void main(String[] args) {
		// ---------------------------------------------
		// Assignment 0
		// Part I
		// Written by: Theo-Dayan Mandamiento Rodriguez
		// ---------------------------------------------

		// Greeting
		System.out.println("Welcome to the Digital Bookstore Manager!");
		
		// Variables
		Scanner kb = new Scanner(System.in);
		int choice,totalAttempts = 0;
		String passInput;
		final String password = "249";
		String garbage;
		
		// Maximum number of books and create inventory
		System.out.print("Please, enter the maximum number of books your bookstore can contain: ");
		final int maxBooks = kb.nextInt();
		Book[] inventory = new Book[maxBooks];
		
		// Menu
		do {
			System.out.println("\nWhat do you want to do?" + "\n\t" +
					"1.  Enter new books (password required)" + "\n\t" +
					"2.  Change information of a book (password required)" + "\n\t" +
					"3.  Display all books by a specific author" + "\n\t" +
					"4.  Display all books under a certain price" + "\n\t" +
					"5.  Quit");
			System.out.print("Please enter your choice > ");
			choice = kb.nextInt();
			garbage = kb.nextLine();
			switch (choice) {
				// 1. Enter new books
				case 1:
					int attempts1 = 0;
					do {
						System.out.print("Please do enter your password: ");
						passInput = kb.nextLine();
						// Password verification
						if (!password.equals(passInput)) {
							attempts1++;
							totalAttempts++;
							// Termination: too many password attempts
							if (totalAttempts == 12) {
								System.out.print("Program detected suspicious activities and will terminate immediately!");
								System.exit(0);
							}
						}
						// Entrance of new books
						else {
							System.out.print("How many books do you wish to enter: ");
							int numBooksInput = kb.nextInt();
							// Creation of new books in inventory
							if (numBooksInput <= maxBooks - Book.findNumberOfCreatedBooks()) {
								for (int i = 1; i <= numBooksInput; i++) {
									garbage = kb.nextLine();
									System.out.println("Enter the information of the book (title, author, ISBN, price):");
									String title = kb.nextLine();
									String author = kb.nextLine();
									long ISBN = kb.nextLong();
									double price = kb.nextDouble();
									inventory[Book.findNumberOfCreatedBooks()] = new Book(title, author, ISBN, price);
								}
							}
							else System.out.println("Only " + (maxBooks - Book.findNumberOfCreatedBooks()) + " book space(s) left.");
						}
					}
					while (attempts1 % 3 != 0 && !password.equals(passInput));
					break;
				// 2. Change information of a book
				case 2:
					int attempts2 = 0;
					do {
						System.out.print("Please do enter your password: ");
						passInput = kb.nextLine();
						// Password verification
						if (!password.equals(passInput))
							attempts2++;
						else {
							System.out.print("Which book number do you wish to update (0-" + (Book.findNumberOfCreatedBooks()-1) + "): ");
							int bookNumber = kb.nextInt();
							// Show actual book information
							if (bookNumber >= 0 && bookNumber < Book.findNumberOfCreatedBooks()) {
								System.out.println("\nBook #" + bookNumber + "\n\t" +
										"Author: " + inventory[bookNumber].getAuthor() + "\n\t" +
										"Title: " + inventory[bookNumber].getTitle() + "\n\t" +
										"ISBN: " + inventory[bookNumber].getISBN() + "\n\t" +
										"Price: $" + inventory[bookNumber].getPrice());
								int choice2;
								// Menu for information change
								do {
									// Menu for information update
									System.out.print("What information would you like to change?" + "\n\t" +
											"1. Author" + "\n\t" +
											"2. Title" + "\n\t" +
											"3. ISBN" + "\n\t" +
											"4. Price" + "\n\t" +
											"5. Quit" + "\n" +
											"Enter your choice > ");
									choice2 = kb.nextInt();
									garbage = kb.nextLine();
									switch (choice2) {
										case 1:
											// New Author
											System.out.print("Enter the new name of the author: ");
											inventory[bookNumber].setAuthor(kb.nextLine());
											break;
										case 2:
											// New Title
											System.out.print("Enter the new title: ");
											inventory[bookNumber].setTitle(kb.nextLine());
											break;
										case 3:
											// New ISBN
											System.out.print("Enter the new ISBN number: ");
											inventory[bookNumber].setISBN(kb.nextLong());
											break;
										case 4:
											// New price
											System.out.print("Enter the new price: ");
											inventory[bookNumber].setPrice(kb.nextDouble());
											break;
										case 5: break;
										default: System.out.println("Invalid choice, try again!");
									}
								}
								while (choice2 != 5);
								// Display new book's information
								System.out.println("\tBook #" + bookNumber + "\n\t" +
										"Author: " + inventory[bookNumber].getAuthor() + "\n\t" +
										"Title: " + inventory[bookNumber].getTitle() + "\n\t" +
										"ISBN: " + inventory[bookNumber].getISBN() + "\n\t" +
										"Price: $" + inventory[bookNumber].getPrice());
							}
						}
					}
					while (attempts2 % 3 != 0 && !password.equals(passInput));
					break;
				// 3. Display all books by a specific author
				case 3:
					// Prompt for author's name
					System.out.print("Enter the name of the author: ");
					String searchAuthor = kb.nextLine();
					// Display all books from same author
					for (int i = 0; i < Book.findNumberOfCreatedBooks(); i++)
						if (searchAuthor.equalsIgnoreCase(inventory[i].getAuthor()))
							System.out.println("\tBook #" + i + "\n\t" +
								"Title: " + inventory[i].getTitle() + "\n\t" +
								"ISBN: " + inventory[i].getISBN() + "\n\t" +
								"Price: $" + inventory[i].getPrice());
					break;
				// 4. Display books under certain price
				case 4:
					// Ask limit price
					System.out.print("Enter the limit price: ");
					double limitPrice = kb.nextDouble();
					// Display all books under limit price
					for (int i = 0; i < Book.findNumberOfCreatedBooks(); i++)
						if (limitPrice > inventory[i].getPrice())
							System.out.println("\tBook #" + i + "\n\t" +
								"Author: " + inventory[i].getAuthor() + "\n\t" +
								"Title: " + inventory[i].getTitle() + "\n\t" +
								"ISBN: " + inventory[i].getISBN() + "\n\t" +
								"Price: $" + inventory[i].getPrice());
					break;
				// 5. Quit
				case 5:
					// Thank the user and exit program
					System.out.println("Thank you very much and see you next time!");
					System.out.print("Digital Bookstore Manager is now closed.");
					System.exit(0);
				// Invalid entry
				default:
					System.out.println("This is an invalid choice, please try again!");
			}
		}
		while (choice != 5);
		
		kb.close();	
	}

}
