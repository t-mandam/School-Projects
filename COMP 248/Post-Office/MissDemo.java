import java.util.Scanner;
public class MissDemo {

	private static final int MIN_REGISTRIES = 0, MIN_LABELS = 0;
	private static int nmbRegistries = 5;
	static Scanner kb = new Scanner(System.in);

	
	public static void main(String[] args) {
		// --------------------------------------------------------------
		// Assignment 4
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This program simulates an application to manage the registry 
		// of a Montreal university's department/facility. It allows the
		// user to manage their inter-campus parcel shipments (stamps,
		// pre-paid subscription labels, ...)
		// --------------------------------------------------------------
		
		// Create Variables
		boolean quit = false;
		int userChoice, registryChoice, labelChoice;
		int idUnit, expDay, expMonth;
		int catA, catB, catC, catD, catE;
		String typeParcel;
		
		// Create Registries, Stamps, Labels (and their respective arrays)
		Registry R0 = new Registry(new Stamps(4, 0, 0, 4, 1), new Label[] {new Label("Standard", 98825164, 25, 12), new Label("Confidential", 98703195, 3, 12)});
		Registry R1 = new Registry(new Stamps(4, 0, 0, 4, 1), new Label[] {new Label("Fragile", 98825164, 7, 12), new Label("Standard", 98596387, 24, 8)});
		Registry R2 = new Registry(new Stamps(9, 4, 0, 2, 1), 
				new Label[] {new Label("Express", 98432806, 1, 6), new Label("Small", 98087913, 18, 12), new Label("Oversize", 98735421, 5, 4)});
		Registry R3 = new Registry(new Stamps(3, 2, 4, 1, 0), new Label[0]);
		Registry R4 = new Registry(new Stamps(3, 2, 4, 1, 0), new Label[0]);
		
		// Create array of registries
		Registry[] allRegistries = {R0, R1, R2, R3, R4};
		
		// Welcome user
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
				"\n|\t" + "Welcome to Montreal Intercampuses Shipping Service (MISS) Application" + "\t\t|\n" +
				"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		do {
			// Menu of options (0 to 9)
			System.out.print("| What would you like to do?" + "\t\t\t\t\t\t\t\t|\n" +
					"| 1  >> See the content of all Registries" + "\t\t\t\t\t\t|\n" +
					"| 2  >> See the content of one Registry" + "\t\t\t\t\t\t\t|\n" +
					"| 3  >> List Registries with same $ amount of shipment Stamps" + "\t\t\t\t|\n" +
					"| 4  >> List Registries with same number of shipment Stamps types" + "\t\t\t|\n" +
					"| 5  >> List Registries with same $ amount of Stamps and same number of prepaid labels " + "\t|\n" +
					"| 6  >> Add a prepaid label to an existing Registry" + "\t\t\t\t\t|\n" +
					"| 7  >> Remove an existing prepaid label from a Registry" + "\t\t\t\t|\n" +
					"| 8  >> Update the expiry date of an existing prepaid label" + "\t\t\t\t|\n" +
					"| 9  >> Add Stamps to a Registry" + "\t\t\t\t\t\t\t|\n" +
					"| 0  >> To quit" + "\t\t\t\t\t\t\t\t\t\t|\n" +
					"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n" +
					"Please enter your choice and press <Enter>: ");
			
			// Take user's choice of action
			userChoice = kb.nextInt();
			switch (userChoice) {
				// 1. See all registries
				case 1:
					System.out.println("Content of each Registry:" + "\n----------------------");
					for (int i = 0; i < nmbRegistries; i++)
						System.out.println("Registry #" + i + ":\n" + allRegistries[i].toString());
					break;
				
				// 2. See a specific registry
				case 2:
					System.out.print("Which registry you want to see the content of? (Enter number 0 to " + (nmbRegistries-1) + "): ");
					registryChoice = verifyRegistry(kb.nextInt());
					System.out.println(allRegistries[registryChoice].toString());
					break;
				
				// 3. Registries with same total Stamps value
				case 3:
					System.out.println("List of Registries with same total $ Stamps:" + "\n");
					for (int i = 0; i < nmbRegistries-1; i++)
						for (int j = i + 1; j < nmbRegistries; j++)
							if (allRegistries[i].equalsStampsTotal(allRegistries[j]))
								System.out.println("\tRegistries " + i + " and " + j + " both have " + allRegistries[i].getStampsValue());
					System.out.println();
					break;
					
				// 4. Registries with same Stamps breakdown
				case 4:
					System.out.println("List of Registries with same Stamps categories:" + "\n");
					for (int i = 0; i < nmbRegistries-1; i++)
						for (int j = i + 1; j < nmbRegistries; j++)
							if (allRegistries[i].equalsStamps(allRegistries[j]))
								System.out.println("\tRegistries " + i + " and " + j + " both have " + allRegistries[i].stampBreakdown());
					System.out.println();
					break;
					
				// 5. Registries with same total value and number of labels
				case 5:
					System.out.println("List of Registries with same $ amount of Stamps and same number of Labels:" + "\n");
					for (int i = 0; i < nmbRegistries-1; i++)
						for (int j = i + 1; j < nmbRegistries; j++)
							if (allRegistries[i].equals(allRegistries[j]))
								System.out.println("\tRegistries " + i + " and " + j);
					System.out.println();
					break;
				
				// 6. Add pre-paid Label to Registry
				case 6:
					System.out.print("Which registry do you want to add a Label to? (Enter number 0 to " + (nmbRegistries-1) + ") : ");
					registryChoice = verifyRegistry(kb.nextInt());
					// Take new Label information
					System.out.println("Please enter the following information so that we may complete the Label-");
					System.out.print(" --> Type of Label (Confidential, Small, Oversize, Express, Standard, Fragile): ");
					typeParcel = kb.next();
					System.out.print(" --> Id of the prepaid Label possessor: ");
					idUnit = kb.nextInt();
					System.out.print(" --> Expiry day number and month (separate by a space): ");
					expDay = kb.nextInt(); expMonth = kb.nextInt();
					// Confirmation
					System.out.println("You now have " + allRegistries[registryChoice].addLabel(typeParcel, idUnit, expDay, expMonth) + " Label(s)" + "\n");
					break;
					
				// 7. Remove Label from Registry
				case 7:
					System.out.print("Which registry do you want to remove a Label from? (Enter number 0 to " + (nmbRegistries-1) + ") : ");
					registryChoice = verifyRegistry(kb.nextInt());
					if (allRegistries[registryChoice].getLabels() == 0)
						System.out.println("Sorry that Registry has no Labels" + "\n");
					else {
						System.out.print("Enter number 0 to " + (allRegistries[registryChoice].getLabels()-1 + ": "));
						labelChoice = verifyLabel(kb.nextInt(), allRegistries[registryChoice]);
						// Confirmation
						if (allRegistries[registryChoice].removeLabel(labelChoice))
							System.out.println("Label was removed succesfully" + "\n");
						else System.out.println("Label wasn't found" + "\n");
					}
					break;
					
				// 8. Update expiry date of Label
				case 8:
					System.out.print("Which registry do you want to update a Label from? (Enter number 0 to " + (nmbRegistries-1) + ") : ");
					registryChoice = verifyRegistry(kb.nextInt());
					if (allRegistries[registryChoice].getLabels() == 0)
						System.out.println("Sorry that Registry has no Labels" + "\n");
					else {
						System.out.print("Which Label do you want to update? (Enter number 0 to " + (allRegistries[registryChoice].getLabels()-1) + "): ");
						labelChoice = verifyLabel(kb.nextInt(), allRegistries[registryChoice]);
						// Set new expiry date
						System.out.print(" --> Enter new expiry date (separate by a space): ");
						expDay = kb.nextInt();
						expMonth = kb.nextInt();
						allRegistries[registryChoice].setExpDate(labelChoice, expDay, expMonth);
						// Confirm
						System.out.println("Expiry Date updated." + "\n");
					}
					break;
					
				// 9. Add Stamps to Registry
				case 9:
					System.out.print("Which registry do you want to add Stamps to? (Enter number 0 to " + (nmbRegistries-1) + ") : ");
					registryChoice = verifyRegistry(kb.nextInt());
					// Take new increments of Stamps
					System.out.print("How many category_A($2), category_B($5), category_C($10), category_D($15), and" +
							"\n" + " category_E($20) parcel stamps do you want to add?" + "\n" +
							"Enter 5 numbers (separated by a space): ");
					catA = kb.nextInt(); catB = kb.nextInt();
					catC = kb.nextInt(); catD = kb.nextInt();
					catE = kb.nextInt();
					// Confirm
					System.out.println("You now have $" + (allRegistries[registryChoice].addStamps(catA, catB, catC, catD, catE)*1.0) + "\n");
					break;
				
				// 0. To quit
				case 0:
					quit = true;
					break;
				
				// Invalid choice
				default:
					System.out.println("Sorry that is not a valid choice. Try again.");
					break;
			}
			
		}
		while (!quit);
		
		// Thank user and close Scanner
		System.out.print("Thank you for using Montreal Intercampuses Service (MISS) Application!");
		kb.close();
	}

	
	// Return valid Registry
	public static int verifyRegistry(int registryChoice) {
		while (registryChoice < MIN_REGISTRIES || registryChoice >= nmbRegistries) {
			System.out.print("Sorry but there is no Registry number " + registryChoice + "\n" +
					"--> Try again: (Enter number 0 to " + (nmbRegistries-1) + "): ");
			registryChoice = kb.nextInt();
		}
		return registryChoice;
	}
	
	
	// Return valid Label
		public static int verifyLabel(int labelChoice, Registry R) {
			while (labelChoice < MIN_LABELS || labelChoice >= R.getLabels()) {
				System.out.print("Sorry but there is no Label number " + labelChoice + "\n" +
						"--> Try again: (Enter number 0 to " + (R.getLabels()-1) + "): ");
				labelChoice = kb.nextInt();
			}
			return labelChoice;
		}
}
