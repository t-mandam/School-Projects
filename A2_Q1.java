import java.util.Scanner;
public class A2_Q1 {

	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Assignment 2
		// Written by: Theo-Dayan Mandamiento Rodriguez 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program gives the user some clothing recommendation to
		// go outside and a safety tip using the current temperature
		// and weather condition.
		// --------------------------------------------------------------
		
		// Welcome the user
		System.out.println("#########################################" +
				"##################################" + "\n\t\t\t" +
				"Fall Adventure Planner!!!" + "\n" +
				"####################################################" +
				"#######################" + "\n\n" +
				"Welcome to the Fall Adventure Planner!");
		
		// Declare Scanner
		Scanner kb = new Scanner(System.in);
		
		// Declare temperature, weather, clothes and safety tip variables
		int temperature;
		String weather, clothes = "", safety_tip = "";
		
		// Prompt the user for the temperature and the weather condition
		System.out.print("Enter the current temperature (°C): ");
		temperature = kb.nextInt();
		System.out.print("Enter the weather condition (sunny/rainy/snowy): ");
		weather = kb.next();
		
		// Estimate recommended clothing and safety tip
			// Weather is rainy
			if (weather.equalsIgnoreCase("rainy"))
				{
				clothes = "Waterproof clothing.";
				safety_tip = "Be cautious of slippery paths!";
				}
			
			// Weather is snowy
			if (weather.equalsIgnoreCase("snowy"))
				{
				clothes = "Heavy winter clothing.";
				safety_tip = "Stay warm and watch out for icy conditions!";
				}
			
			// Weather is sunny
			if (weather.equalsIgnoreCase("sunny"))
				{
				safety_tip = "Don't forget sunscreen and stay hydrated!";
				if (temperature > 20)
					clothes = "Light clothing (t-shirt and shorts).";
				else if (temperature < 10)
					clothes = "Warm clothing (sweater and coat).";
				else
					clothes = "A light jacket.";
				}
		
		// Display the recommended clothes, safety tip, and thank the user
		System.out.print("\n"+ "Recommended clothing: " + clothes +"\n"+
						"Safety tip: " + safety_tip + "\n" +
						"Thank you for using the Fall Adventure Planner!");
		
		// Close the Scanner
		kb.close();
	}

}
