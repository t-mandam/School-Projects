import java.util.Scanner;
public class SolarRoofEnergyCalculator {

	public static void main(String[] args) {
		// ------------------------------------------------------
		// Assignment 1
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program takes some basic information about the
		// user's solar panels and gives the energy production
		// on a daily and annual basis to the user.
		// ------------------------------------------------------
		
		
		// Welcome the user to the Calculator
		System.out.println("*********Solar Roof Energy Calculator"
							+ "*********");
		
		// Declare our scanner
		Scanner kb = new Scanner(System.in);
		
		// Ask for the number of solar panels
		System.out.print("Enter the number of solar panels: ");
		int numPanels = kb.nextInt();
		
		// Ask for the wattage of the solar panels
		System.out.print("Enter the wattage rating of each solar"
							+ " panel (in watts): ");
		int panelWattage = kb.nextInt();
		
		// Ask for the sunlight duration per day
		System.out.print("Enter the average number of sunlight" +
							" hours per day: ");
		double sunlightHours = kb.nextDouble();
		
		// Ask for the solar panels' efficiency
		System.out.print("Enter the efficiency of the solar" +
							" panels (as a percentage): ");
		double efficiency = kb.nextDouble();
		
		// Calculate the daily and annual energy productions
		double dailyProd =
		numPanels*panelWattage*sunlightHours*efficiency/(1000*100);
		double annualProd = dailyProd * 365;
		System.out.println();
		
		// Give the daily and annual energy productions
		System.out.printf("Daily Energy Production: %.4f", dailyProd);
		System.out.println(" kWh");
		System.out.printf("Annual Energy Production: %.4f", annualProd);
		System.out.println(" kWh");
		
		// Thank the user
		System.out.println("Thank you for using the Solar Roof" +
						 " Energy Calculator!");
		
		// Close the Scanner
		kb.close();
	}

}
