
// ---------------------------------------------
// Assignment 0
// Part I
// Written by: Theo-Dayan Mandamiento Rodriguez
// ---------------------------------------------

public class Book {
	
	// Variables
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int numOfBooks = 0;
	
	// Unique Constructor
	public Book(String title, String author, long ISBN, double price) {
		this.title = title;
		this.author = author;
		this.ISBN = (ISBN > 1000000000) ? ISBN : 1000000000;
		this.price = (price > 0) ? price : 0.0;
		numOfBooks++;
	}
	
	// Setters
		// Modify title
		public void setTitle(String newTitle) {
			this.title = newTitle;
		}
		
		// Modify author
		public void setAuthor(String newAuthor) {
			this.author = newAuthor;
		}
		
		// Modify ISBN
		public void setISBN(long newISBN) {
			this.ISBN = (newISBN > 1000000000) ? newISBN : 1000000000;
		}
		
		// Modify price
		public void setPrice(double newPrice) {
			this.price = (newPrice > 0) ? newPrice : 0.0;
		}
		
	// Getters
		// Get title
		public String getTitle() {
			return this.title;
		}
		
		// Get author
		public String getAuthor() {
			return this.author;
		}
		
		// Get ISBN
		public long getISBN() {
			return this.ISBN;
		}
		
		// Get price
		public double getPrice() {
			return this.price;
		}
	
	// Utilities
		// Get number of books
		public static int findNumberOfCreatedBooks() {
			return numOfBooks;
		}
		
		// Compare two books
		public boolean equals(Book b2) {
			return this.ISBN == b2.ISBN &&
					Math.abs(this.price - b2.price) < 0.01;
		}
		
		// Get the string
		public String toString() {
			return this.title + " by " + this.author + ", " + this.price + ", " + this.ISBN;
		}
}
