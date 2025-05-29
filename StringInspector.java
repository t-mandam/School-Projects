import java.util.Scanner;
public class StringInspector {

	public static void main(String[] args) {
		// ------------------------------------------------------
		// Assignment 1
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program asks the user for a sentence, for a word,
		// and a separator. Then, it does some string operations 
		// on these and also gives some characteristics about them.
		// ------------------------------------------------------
		
		// Welcome the user
		System.out.println("********* String Inspector *********");
		// Declare Scanner
		Scanner kb = new Scanner(System.in);
		
		// Ask for a sentence
		System.out.print("Enter the given sentence (longer" + 
						" than 5 characters): ");
		String sentence = kb.nextLine();
		
		// Ask for a word
		System.out.print("Enter the given word: ");
		String word = kb.next();
		
		// Ask for a separator
		System.out.print("Enter a separator to join the two strings: ");
		String separator = kb.next();
		System.out.println();
		
		// Does the sentence contain the word?
		System.out.println("Given sentence contains the given word: "
							+ sentence.contains(word));
		
		//Does the sentence start with 'i'?
		System.out.println("Given sentence starts with an 'i': "
							+ sentence.startsWith("i"));
		
		// Replace all 'a' in the sentence by 'e'
		System.out.println("Sentence with 'a' replaced by 'e': "
							+ sentence.replaceAll("a", "e"));
		
		// Join the sentence and the word with the separator
		System.out.println("Joined string: " + 
							String.join(separator, sentence, word));
		
		// Give the index of the first 'a' in the sentence
		System.out.println("'a' appears at index " + 
							sentence.indexOf('a') +
							" in the given sentence.");
		
		// Give the 3rd character in the sentence
		System.out.println("Character at 3rd position in the given" +
							" sentence: " + sentence.charAt(2));
		System.out.println();
		
		// Thank the user
		System.out.print("Thank you for using the String Inspector" +
							" tool. Have a great day!");
		// Close Scanner
		kb.close();
	}

}
