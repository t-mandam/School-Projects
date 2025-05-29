// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package driver;
import java.util.Scanner;
import vehicles.*;
import client.*;

public class Main {

	static Scanner input = new Scanner(System.in);

	final static int MAX_CLIENTS = 1000;
	static int nmbClients = 0;
	static Client[] listClients = new Client[MAX_CLIENTS];
	
	static int nmbVehicles = 0, nmbGC = 0, nmbEC = 0, nmbDT = 0, nmbET = 0;
	static Vehicle[] inventory = new Vehicle [Vehicle.MAX_VEHICLES];
	static Vehicle[] inventoryGC = new GasolineCar[Vehicle.MAX_VEHICLES];
	static Vehicle[] inventoryEC = new ElectricCar[Vehicle.MAX_VEHICLES];
	static Vehicle[] inventoryDT = new DieselTruck[Vehicle.MAX_VEHICLES];
	static Vehicle[] inventoryET = new ElectricTruck[Vehicle.MAX_VEHICLES];
	
	public static void main(String[] args) {
		// Variables declaration
		boolean exitChoice = true;

		// Introduction
		System.out.println("############## WELCOME TO ROYAL RENTALS ##############");

		boolean versionChoice = true;
		// Principal Menu
		do {
		System.out.print("Versions for testing:"
			+ "\n1. User input version"
			+ "\n2. Hardcoded version"
			+ "\n3. Exit"
			+ "\nWhich version would you like to test (1-2): ");
		int version = input.nextInt();
		if (version == 1) {
		// USER INPUT VERSION
			do {
				System.out.println("\n" + "| | | | PRINCIPAL MENU | | | |"
						+ "\n1. Vehicle Management"
						+ "\n2. Client Management"
						+ "\n3. Leasing Operations"
						+ "\n4. Additional Operations");
				System.out.print("What would you like to do (1-4): ");
				int choice = input.nextInt();
				switch (choice) {
					// 1. Vehicle Management Menu
					case 1: 
						System.out.print("\n----- VEHICLE MANAGEMENT MENU: -----"
								+ "\n 1. Add a vehicle."
								+ "\n 2. Delete a vehicle."
								+ "\n 3. Update a vehicle's information."
								+ "\n 4. List all vehicles by category");
								System.out.print("\nWhat would you like to do (1-4): ");
						int choice1 = input.nextInt();
						switch (choice1) {
							// 1.1 Add a vehicle
							case 1:
								if (nmbVehicles < Vehicle.MAX_VEHICLES) {
									System.out.println("---- Add A Vehicle -----");
									System.out.print("Maker of the vehicle: ");
										String make1 = input.nextLine(); // Erase empty line
										make1 = input.nextLine();
									System.out.print("Model of the vehicle: ");
										String model1 = input.nextLine();
									System.out.print("Year of production: ");
										int year1 = input.nextInt();
									int typeVehicle = Main.askTypeVehicle();
									switch (typeVehicle) {
										// 1.1.1 Add Gasoline Car
										case 1:
											System.out.print("Passenger capacity: ");
											int passengersGC = input.nextInt();
											GasolineCar newCarGC = new GasolineCar(make1, model1, year1, passengersGC);
											inventory[nmbVehicles++] = newCarGC;
											inventoryGC[nmbGC++] = newCarGC;
											//inventory[nmbVehicles++] = inventoryGC[nmbGC++] = new GasolineCar(make1, model1, year1, passengersGC);
											System.out.println("Gasoline Car added successfully: " + "\n\t" + inventoryGC[nmbGC - 1]);
											break;
											
										// 1.1.2 Add Electric Car
										case 2:
											System.out.print("Passenger capacity: ");
											int passengersEC = input.nextInt();
											System.out.print("Autonomy of the vehicle (km): ");
											int autonomyEC = input.nextInt();
											ElectricCar newCarEC = new ElectricCar(make1, model1, year1, passengersEC, autonomyEC);
											inventory[nmbVehicles++] = newCarEC;
											inventoryEC[nmbEC++] = newCarEC;
											//inventory[nmbVehicles++] = inventoryEC[nmbEC++] = new ElectricCar(make1, model1, year1, passengersEC, autonomyEC);
											System.out.println("Electric Car added successfully: " + "\n\t" + inventoryEC[nmbEC - 1]);
											break;
											
										// 1.1.3 Add Diesel Truck
										case 3:
											System.out.print("Weight capacity (kg): ");
											int weightDT = input.nextInt();
											System.out.print("Fuel tank capacity (L): ");
											int tankDT = input.nextInt();
											DieselTruck newTruckDT = new DieselTruck(make1, model1, year1, weightDT, tankDT);
											inventory[nmbVehicles++] = newTruckDT;
											inventoryDT[nmbDT++] = newTruckDT;
											//inventory[nmbVehicles++] = inventoryDT[nmbDT++] = new DieselTruck(make1, model1, year1, weightDT, tankDT);
											System.out.println("Diesel Truck added successfully: " + "\n\t" + inventoryDT[nmbDT - 1]);
											break;
											
										// 1.1.4 Add Electric Truck
										case 4:
											System.out.print("Weight capacity (kg): ");
											int weightET = input.nextInt();
											System.out.print("Autonomy of the vehicle (km): ");
											int autonomyET = input.nextInt();
											ElectricTruck newTruckET = new ElectricTruck(make1, model1, year1, weightET, autonomyET);
											inventory[nmbVehicles++] = newTruckET;
											inventoryET[nmbET++] = newTruckET;
											//inventory[nmbVehicles++] = inventoryET[nmbET++] = new ElectricTruck(make1, model1, year1, weightET, autonomyET);
											System.out.println("Electric Truck added successfully: " + "\n\t" + inventoryET[nmbET - 1]);
											break;
											
										// Wrong input
										default: break;
									}
								}
								// Inventory of vehicles FULL
								else System.out.println("Your inventory is full. Delete a vehicle before adding a new one.");
								break;
								
							// 1.2 Delete a Vehicle
							case 2:
								if (nmbVehicles > 0) {
									System.out.println("----- Delete A Vehicle -----");
									int typeVehicle = Main.askTypeVehicle();
									System.out.print("Plate number of vehicle: ");
									String plate = input.nextLine(); // Erasing empty line
									plate = input.nextLine();
									boolean deleted = false;
									switch (typeVehicle) {
										// 1.2.1 Delete Gasoline Car
										case 1:
											if (nmbGC > 0) {
												for (int i = 0; i < nmbGC; i++) {
													if (inventoryGC[i].getPlateNmb().equalsIgnoreCase(plate) && !(inventoryGC[i].getIsLeased())) {
														inventoryGC[i] = inventoryGC[nmbGC - 1];
														inventoryGC[nmbGC - 1] = null;
														nmbGC--;
														Main.deleteVehicle(plate);
														System.out.println("Gasoline Car deleted successfully.");
														deleted = true;
														break;
													}
												}
												if (!deleted) System.out.println("Plate not found in inventory or is currently leased.");
											}
											else System.out.println("No gasoline cars in inventory.");
											break;
											
										// 1.2.2 Delete Electric Car
										case 2:
											if (nmbEC > 0) {
												for (int i = 0; i < nmbEC; i++) {
													if (inventoryEC[i].getPlateNmb().equalsIgnoreCase(plate) && !(inventoryEC[i].getIsLeased())) {
														inventoryEC[i] = inventoryEC[nmbEC - 1];
														inventoryEC[nmbEC - 1] = null;
														nmbEC--;
														Main.deleteVehicle(plate);
														System.out.println("Electric Car deleted successfully.");
														deleted = true;
														break;
													}
												}
												if (!deleted) System.out.println("Plate not found in inventory or is currently leased.");
											}
											else System.out.println("No electric cars in inventory.");
											break;
											
										// 1.2.3 Delete Diesel Truck
										case 3:
											if (nmbDT > 0) {
												for (int i = 0; i < nmbDT; i++) {
													if (inventoryDT[i].getPlateNmb().equalsIgnoreCase(plate) && !(inventoryDT[i].getIsLeased())) {
														inventoryDT[i] = inventoryDT[nmbDT - 1];
														inventoryDT[nmbDT - 1] = null;
														nmbDT--;
														Main.deleteVehicle(plate);
														System.out.println("Diesel Truck deleted successfully.");
														deleted = true;
														break;
													}
												}
												if (!deleted) System.out.println("Plate not found in inventory or is currently leased.");
											}
											else System.out.println("No diesel trucks in inventory.");
											break;

										// 1.2.4 Delete Electric Truck
										case 4:
											if (nmbET > 0) {
												for (int i = 0; i < nmbET; i++) {
													if (inventoryET[i].getPlateNmb().equalsIgnoreCase(plate) && !(inventoryET[i].getIsLeased())) {
														inventoryET[i] = inventoryET[nmbET - 1];
														inventoryET[nmbET - 1] = null;
														nmbET--;
														Main.deleteVehicle(plate);
														System.out.println("Electric Truck deleted successfully.");
														deleted = true;
														break;
													}
												}
												if (!deleted) System.out.println("Plate not found in inventory or is currently leased.");
											}
											else System.out.println("No electric trucks in inventory.");
											break;
											
										// Wrong input
										default: break;
									}
								}
								else System.out.println("Your inventory is empty.");
								break;

							// 1.3 Update vehicle information
							case 3:
								if (nmbVehicles > 0) {
									System.out.print("----- Update Vehicle Information -----"
											+ "\n 1. All: maker, model, year"
											+ "\n 2. Car: passenger limit, autonomy range"
											+ "\n 3. Truck: weight capacity, fuel tank capacity, autonomy range"
											+ "\nType of information to edit (1-3): ");
									int infoToEdit = input.nextInt();
									String plate3;
									boolean edited = false;
									switch(infoToEdit) {
										// 1.3.1 Update maker/model/year
										case 1:
											System.out.print("----- Update maker/model/year -----"
													+ "\n 1. Maker"
													+ "\n 2. Model"
													+ "\n 3. Year"
													+ "\nEnter info to edit (1-3): ");
											infoToEdit = input.nextInt();
											System.out.print("Enter plate number of vehicle: ");
											plate3 = input.nextLine();
											plate3 = input.nextLine();
											// 1.3.1.1 Update Maker
											if (infoToEdit == 1) {
												for (int i = 0; i < nmbVehicles; i++)
													if (inventory[i].getPlateNmb().equalsIgnoreCase(plate3)) {
														System.out.print("Enter new maker: ");
														String newMaker = input.nextLine();
														inventory[i].setMake(newMaker);
														System.out.println("Maker edited successfully.");
														edited = true; break;
													}
												if (!edited) System.out.println("Plate number not found in inventory.");
											}
											// 1.3.1.2 Update Model
											if (infoToEdit == 2) {
												for (int i = 0; i < nmbVehicles; i++)
													if (inventory[i].getPlateNmb().equalsIgnoreCase(plate3)) {
														System.out.print("Enter new model: ");
														String newModel = input.nextLine();
														inventory[i].setModel(newModel);
														System.out.println("Model edited successfully.");
														edited = true; break;
													}
												if (!edited) System.out.println("Plate number not found in inventory.");
											}
											// 1.3.1.1 Update Year
											if (infoToEdit == 3) {
												for (int i = 0; i < nmbVehicles; i++)
													if (inventory[i].getPlateNmb().equalsIgnoreCase(plate3)) {
														System.out.print("Enter new year: ");
														int newYear = input.nextInt();
														inventory[i].setYearOfProduction(newYear);
														System.out.println("Year edited successfully.");
														edited = true; break;
													}
												if (!edited) System.out.println("Plate number not found in inventory.");
											}
											break;
										// 1.3.2 Update Car's passenger limit/autonomy range
										case 2:
											System.out.println("----- Update passenger limit/autonomy range -----"
													+ "\n 1. Passenger limit"
													+ "\n 2. Autonomy range"
													+ "\nType of information to edit (1-2): ");
											infoToEdit = input.nextInt();
											System.out.print("Enter plate number of car: ");
											plate3 = input.nextLine();
											plate3 = input.nextLine();
											// 1.3.2.1 Update passenger limit
											if (infoToEdit == 1) {
												System.out.print("----- Update passenger limit -----"
														+ "\n 1. Gasoline Car"
														+ "\n 2. Electric Car"
														+ "\nType of car to edit (1-2): ");
												int carToEdit = input.nextInt();
												System.out.print("Enter new passenger limit: ");
												int newPassengers = input.nextInt();
												if (carToEdit == 1) {
													for (int i = 0; i < nmbGC; i++)
														if (inventoryGC[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															GasolineCar tempGC = (GasolineCar) inventoryGC[i];
															tempGC.setMaxNbrOfPassengers(newPassengers);
															System.out.println("Max number of passengers edited successfully.");
															edited = true; 
															break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
												if (carToEdit == 2) {
													for (int i = 0; i < nmbEC; i++)
														if (inventoryEC[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															ElectricCar tempEC = (ElectricCar) inventoryEC[i];
															tempEC.setMaxNbrOfPassengers(newPassengers);
															System.out.println("Max number of passengers edited successfully.");
															edited = true; break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
											}
											// 1.3.2.2 Update Electric Car's autonomy range
											if (infoToEdit == 2) {
												if (nmbEC > 0) {
													for (int i = 0; i < nmbEC; i++)
														if (inventoryEC[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															System.out.print("Enter new autonomy range (km): ");
															int newAutonomy = input.nextInt();
															ElectricCar tempEC = (ElectricCar) inventoryEC[i];
															tempEC.setAutonomyRange(newAutonomy);
															System.out.println("Autonomy range edited successfully.");
															edited = true; break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
												else System.out.println("There are no Electric Cars in inventory.");
											}
											break;
										// 1.3.3 Update Truck's weight capacity/fuel tank capacity/autonomy range
										case 3:
											System.out.println("----- Update weight capacity/fuel tank capacity/autonomy range -----"
													+ "\n 1. Weight capacity"
													+ "\n 2. Fuel tank capacity"
													+ "\n 3. Autonomy range"
													+ "\nType of information to edit (1-3): ");
											infoToEdit = input.nextInt();
											System.out.print("Enter plate number of truck: ");
											plate3 = input.nextLine();
											plate3 = input.nextLine();
											// 1.3.3.1 Update weight capacity
											if (infoToEdit == 1) {
												System.out.print("----- Update weight capacity -----"
														+ "\n 1. Diesel Truck"
														+ "\n 2. Electric Truck"
														+ "\nType of truck to edit (1-2): ");
												int truckToEdit = input.nextInt();
												System.out.print("Enter new weight capacity (kg): ");
												int newWeight = input.nextInt();
												// 1.3.3.1.1 Update Diesel Truck's weight capacity
												if (truckToEdit == 1) {
													for (int i = 0; i < nmbDT; i++)
														if (inventoryDT[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															DieselTruck tempDT = (DieselTruck) inventoryDT[i];
															tempDT.setMaxWeight(newWeight);
															System.out.println("Weight capacity edited successfully.");
															edited = true; break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
												// 1.3.3.1.2 Update Electric Truck's weight capacity
												if (truckToEdit == 2) {
													for (int i = 0; i < nmbET; i++)
														if (inventoryET[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															ElectricTruck tempET = (ElectricTruck) inventoryET[i];
															tempET.setMaxWeight(newWeight);
															System.out.println("Weight capacity edited successfully.");
															edited = true; break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
											}
											// 1.3.3.2 Update Diesel Truck's fuel tank
											if (infoToEdit == 2) {
												if (nmbDT > 0) {
													for (int i = 0; i < nmbDT; i++)
														if (inventoryDT[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															System.out.print("Enter new fuel tank capacity (L): ");
															int newFuelTank = input.nextInt();
															DieselTruck tempDT = (DieselTruck) inventoryDT[i];
															tempDT.setFuelTankCapacity(newFuelTank);
															System.out.println("Fuel tank capacity edited successfully.");
															edited = true; 
															break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
												else System.out.println("There are no Diesel Trucks in inventory.");
											}
											// 1.3.3.3 Update Electric Truck's autonomy range
											if (infoToEdit == 3) {
												if (nmbET > 0) {
													for (int i = 0; i < nmbET; i++)
														if (inventoryET[i].getPlateNmb().equalsIgnoreCase(plate3)) {
															System.out.print("Enter new autonomy range (km): ");
															int newAutonomy = input.nextInt();
															ElectricTruck tempET = (ElectricTruck) inventoryET[i];
															tempET.setAutonomyRange(newAutonomy);
															System.out.println("Autonomy range edited successfully.");
															edited = true; break;
														}
													if (!edited) System.out.println("Plate number not found in inventory.");
												}
												else System.out.println("There are no Electric Trucks in inventory.");
											}
											break;
										default: break;
									}
								}
								else System.out.println("There are no vehicles in inventory to edit.");
								break;
							
							// 1.4 List all vehicles by category
							case 4:
								// Print Gasoline Cars
								System.out.println("Gasoline Cars inventory:");
								if (nmbGC > 0) {
									for (int i = 0; i < nmbGC; i++)
										System.out.println("\t" + (i+1) + ". " + inventoryGC[i]);
								}
								else System.out.println("\t No Gasoline Cars");
								
								// Print Electric Cars
								System.out.println("Electric Cars inventory:");
								if (nmbEC > 0) {
									for (int i = 0; i < nmbEC; i++)
										System.out.println("\t" + (i+1) + ". " + inventoryEC[i]);
								}
								else System.out.println("\t No Electric Cars");

								// Print Diesel Trucks
								System.out.println("Diesel Trucks inventory:");
								if (nmbDT > 0) {
									for (int i = 0; i < nmbDT; i++)
										System.out.println("\t" + (i+1) + ". " + inventoryDT[i]);
								}
								else System.out.println("\t No Diesel Trucks");

								// Print Electric Trucks
								System.out.println("Electric Trucks inventory:");
								if (nmbET > 0) {
									for (int i = 0; i < nmbET; i++)
										System.out.println("\t" + (i+1) + ". " + inventoryET[i]);
								}
								else System.out.println("\t No Electric Trucks");
								break;
						}
						break;
		
					// 2. Client Management
					case 2:
						System.out.println("\n----- CLIENT MANAGEMENT MENU -----"
								+ "\n 1. Add a client."
								+ "\n 2. Edit a client."
								+ "\n 3. Delete a client.");
								System.out.print("What would you like to do: ");
						int choice2 = input.nextInt();
						switch (choice2) {
							// 2.1 Add Client
							case 1:
								if (nmbClients < MAX_CLIENTS) {
									System.out.print("Name of new client: ");
									String name1 = input.nextLine();
									input.nextLine();
									listClients[nmbClients] = new Client(name1);
									System.out.println("Creation of new client confirmed: " + listClients[nmbClients]);
									nmbClients++;
								}
								else System.out.println("You have reached the maximum number of clients.");
								break;
							
							// 2.2 Edit Client
							case 2:
								boolean edited = false;
								if (nmbClients > 0) {
									System.out.print("ID of client you wish to edit: ");
									int clientID = input.nextInt();
									for (int i = 0; i < nmbClients; i++) {
										if (listClients[i].getClientID() == clientID) {
											System.out.print("New name of the client: ");
											String name2 = input.nextLine();
											input.nextLine();
											listClients[i].setClientName(name2);
											edited = true;
										}
									}
									if (!edited) System.out.println("Client ID not found.");
								}
								else System.out.println("No clients currently exist.");
								break;

							// 2.3 Delete Client
							case 3:
								boolean deleted = false;
								if (nmbClients > 0) {
									System.out.print("Enter ID of client you wish to delete: ");
									int clientID = input.nextInt();
									for (int i = 0; i < nmbClients; i++) {
										if (listClients[i].getClientID() == clientID) {
											listClients[i] = listClients[nmbClients - 1];
											listClients[nmbClients - 1] = null;
											nmbClients--;
											System.out.println("Client deleted successfully.");
											deleted = true;
										}
									}
									if (!deleted) System.out.println("Client ID not found.");
								}
								else System.out.println("No clients currently exist.");
								break;

							// Wrong input
							default: break;
						}
						break;
		
					// 3. Leasing Operations
					case 3:
						System.out.println("\n----- LEASING OPERATIONS MENU -----"
								+ "\n 1. Lease a vehicle to a client."
								+ "\n 2. Return a vehicle from a client."
								+ "\n 3. Show all vehicles leased by a client."
								+ "\n 4. Show all leased vehicles by all clients.");
						System.out.print("What would you like to do (1-4): ");
						int choice3 = input.nextInt();
						boolean clientExists = false;
						switch (choice3) {
							// 3.1 Lease Vehicle
							case 1:
								boolean leased = false;
								if (nmbVehicles > 0) {
								    System.out.print("Client ID to lease a vehicle: ");
								    int clientID = input.nextInt();
								    clientExists = false;
								    for (int i = 0; i < nmbClients; i++) { 
								        if (listClients[i].getClientID() == clientID) {
								            clientExists = true;
								            System.out.print("Enter the plate of the vehicle you wish to lease: ");
								            String plate1 = input.nextLine();
								            plate1 = input.next(); 
									            for (int k = 0; k < nmbVehicles; k++) { 
									                if (inventory[k].getPlateNmb().equalsIgnoreCase(plate1) && !inventory[k].getIsLeased()) {
									                    listClients[i].leaseVehicle(inventory[k]); 
									                    System.out.println("Vehicle leased successfully.");
									                    leased = true;
									                    break; 
									                }
									            }
								            if (!leased) System.out.println("The plate either does not exist or is already leased by another client.");
								            break; 
								        }
								    }
								    if (!clientExists) System.out.println("Client ID does not exist.");
								} else {
								    System.out.println("There are no vehicles to lease.");
								}
								break;

							// 3.2 Return a vehicle
							case 2:
								boolean returned = false;
								if (nmbVehicles > 0) {
								    System.out.print("Client ID to return a vehicle: ");
								    int clientID = input.nextInt();
								    boolean clientExists1 = false;
								    for (int i = 0; i < nmbClients; i++) { 
								        if (listClients[i].getClientID() == clientID) {
								            clientExists1 = true;
								            System.out.print("Enter the plate of the vehicle you wish to return: ");
								            String plate2 = input.nextLine();
								            plate2 = input.next(); 
								            for (int j = 0; j < nmbVehicles; j++) { 
								                // Checking if the vehicle is leased by this client
								                if (inventory[j].getPlateNmb().equalsIgnoreCase(plate2) && listClients[i].leasing(plate2)) {
								                    listClients[i].returnVehicle(inventory[j]); 
								                    System.out.println("Vehicle returned successfully.");
								                    returned = true;
								                    break; 
								                }
								            }
								            if (!returned) System.out.println("The plate either does not exist or is not leased by this client.");
								            break; 
								        }
								    }
								    if (!clientExists1) System.out.println("This Client ID does not exist.");
								} else {
								    System.out.println("There are no vehicles currently leased.");
								}
								break;

							// 3.3 All vehicles leased by specific Client
							case 3:
								if (nmbClients > 0 && nmbVehicles > 0) {
									System.out.print("ID of the client: ");
									int clientID = input.nextInt();
									boolean clientExists331 = false;
									for (int i = 0; i < nmbClients; i++) {
										if (listClients[i].getClientID() == clientID) {
											clientExists331 = true;
											if (listClients[i].getNmbLeasedVehicles() > 0)
												System.out.println(listClients[i]);
											else System.out.println("No leased vehicles by " + listClients[i].getClientName());
										}
									if (!clientExists331) System.out.println("Client ID does not exist.");
									}
								}
								else System.out.println("There are no clients/vehicles in inventory.");
								break;

							// 3.4 All Leased vehicles
							case 4:
								leased = false;
								if (nmbVehicles > 0 && nmbClients > 0) {
									System.out.println("All leased vehicles:");
									for (int i = 0; i < nmbVehicles; i++) {
										if (inventory[i].getIsLeased()) {
											System.out.println("\t" + inventory[i]);
											leased = true;
										}
									}
									if (!leased) System.out.println("No leased vehicles.");
								}
								else System.out.println("There are no clients or no vehicles in inventory.");
								break;
							default: break;
						}
						break;
									
					// 4. Additional Operations
					case 4:
						System.out.print("\n----- ADDITIONAL OPERATIONS -----"
								+ "\n 1. Display the truck with the largest capacity."
								+ "\n 2. Create a copy of an inventory."
								+ "\nSelect an operation (1-2): ");
						int choice4 = input.nextInt();
						switch (choice4) {
							// 4.1 Display Largest Capacity Truck
							case 1:
								System.out.print("----- Display Truck With Largest Capacity -----"
									+ "\n 1. Diesel Truck"
									+ "\n 2. Electric Truck"
									+ "\nWhich inventory do you want to check (1-2): ");
								int inventoryChoice = input.nextInt();
								// 4.1.1 Largest Diesel Truck
								if (inventoryChoice == 1) {
									if (nmbDT > 0) {
										Vehicle largestDiesel = Main.getLargestTruck((Truck[]) inventoryDT);
										System.out.println("Largest Diesel Truck: " + "\n\t" + largestDiesel);
									}
									else System.out.println("No Diesel Trucks in inventory.");
								}
								// 4.1.2 Largest Electric Truck
								if (inventoryChoice == 2 && nmbET > 0) {
									if (nmbET > 0) {
										Vehicle largestElectric = Main.getLargestTruck((Truck[]) inventoryET);
										System.out.println("Largest Electric Truck: " + "\n\t" + largestElectric);
									}
									else System.out.println("No Diesel Trucks in inventory.");
								}
								break;

							// 4.2 Copy Inventory
							case 2:
								System.out.println("----- Copy Inventory -----");
								int vehicleType = Main.askTypeVehicle();
								switch (vehicleType) {
									// 4.2.1 Copy Gasoline Car inventory
									case 1:
										if (nmbGC > 0) {
											Vehicle[] copyGC = Main.copyVehicles(inventoryGC);
											System.out.println("Copy of Gasoline Cars inventory successful.");
											for (int i = 0; i < nmbGC; i++)
												System.out.println(copyGC[i]);
										}
										else System.out.println("No Gasoline Cars in inventory.");
										break;

									// 4.2.2 Copy Electric Car inventory
									case 2:
										if (nmbEC > 0) {
											Vehicle[] copyEC = Main.copyVehicles(inventoryEC);
											System.out.println("Copy of Electric Cars inventory successful.");
											for (int i = 0; i < nmbEC; i++)
												System.out.println(copyEC[i]);
										}
										else System.out.println("No Electric Cars in inventory.");
										break;

									// 4.2.3 Copy Diesel Truck inventory
									case 3:
										if (nmbDT > 0) {
											Vehicle[] copyDT = Main.copyVehicles(inventoryDT);
											System.out.println("Copy of Diesel Trucks inventory successful.");
											for (int i = 0; i < nmbDT; i++)
												System.out.println(copyDT[i]);
										}
										else System.out.println("No Diesel Trucks in inventory.");
										break; 

									// 4.2.4 Copy Electric Truck inventory
									case 4:  
										if (nmbET > 0) {
											Vehicle[] copyET = Main.copyVehicles(inventoryET);
											System.out.println("Copy of Electric Trucks inventory successful.");
											for (int i = 0; i < nmbET; i++)
												System.out.println(copyET[i]);
										}
										else System.out.println("No Electric Trucks in inventory.");
										break;
										
									default: break;
								}
								break;
							
							// Invalid choice
							default: break;
						}
						break;
					
					// Invalid choice
					default:
						System.out.println("The choice you entered is not valid. Please try again.");
				}
			} while (exitChoice);
		}
		else if (version == 2) {
		// HARDCODED VERSION
			
			// 1. Create at least 3 Vehicle of each type of vehicle (GC, EC, DT, ET) and 3 clients
			inventory[nmbVehicles++] = inventoryGC[nmbGC++] = new GasolineCar("Toyota", "Corolla", 2019, 5);
			inventory[nmbVehicles++] = inventoryGC[nmbGC++] = new GasolineCar("Honda", "Civic", 2018, 5);
			inventory[nmbVehicles++] = inventoryGC[nmbGC++] = new GasolineCar("Toyota", "Corolla", 2019, 5);

			inventory[nmbVehicles++] = inventoryEC[nmbEC++] = new ElectricCar("Tesla", "Model S", 2020, 5, 500);
			inventory[nmbVehicles++] = inventoryEC[nmbEC++] = new ElectricCar("Nissan", "Leaf", 2019, 5, 400);
			inventory[nmbVehicles++] = inventoryEC[nmbEC++] = new ElectricCar("Chevrolet", "Bolt", 2018, 5, 450);

			inventory[nmbVehicles++] = inventoryDT[nmbDT++] = new DieselTruck("Ford", "F-150", 2019, 2000, 100);
			inventory[nmbVehicles++] = inventoryDT[nmbDT++] = new DieselTruck("Chevrolet", "Silverado", 2018, 2500, 120);
			inventory[nmbVehicles++] = inventoryDT[nmbDT++] = new DieselTruck("Dodge", "Ram", 2017, 3000, 150);

			inventory[nmbVehicles++] = inventoryET[nmbET++] = new ElectricTruck("Tesla", "CyberTruck", 2020, 5000, 500);
			inventory[nmbVehicles++] = inventoryET[nmbET++] = new ElectricTruck("Nikola", "One", 2019, 4500, 450);
			inventory[nmbVehicles++] = inventoryET[nmbET++] = new ElectricTruck("Rivian", "R1T", 2018, 4000, 400);

			listClients[nmbClients++] = new Client("Yarick Pedo");
			listClients[nmbClients++] = new Client("Miguelito Torres");
			listClients[nmbClients++] = new Client("Iska La Malcriada");
			
			boolean choiceVersion2 = true;
			do {
			System.out.print("\n----- HARD-CODED VERSION MENU -----" +
							"\n 1. Display all vehicles in inventory."+
							"\n 2. Compare vehicles."+
							"\n 3. Display the largest truck." +
							"\n 4. Create a copy of electric trucks." +
							"\n 5. Go back to version menu.");
			System.out.print("What would you like to do (1-4): ");
			int choice = input.nextInt();
			if (choice < 0 || choice > 5) {
				System.out.println("Incorrect entry. Please try again.");
			}
			switch (choice) {
			case 1:
				// 2. Display all Vehicles
				System.out.println("\nAll " + nmbVehicles + " vehicles in inventory:");
				for (int i = 0; i < nmbVehicles; i++)
					System.out.println("\t" + inventory[i]);
				break;
			case 2:
				/*3. Test equals() method with following cases:
				 Compare two objects from different classes
				 Compare two objects of the same class with different attribute values
				 Compare two objects of the same class with identical attribute values */
				System.out.println("Which example of comparison would you like to see?" + 
									"\n 1. 2 vehicles different vehicles" + 
									"\n 2. 2 vehicles of the same type.");
						int choice2 = input.nextInt();
						switch (choice2) {
						case 1:
							System.out.println("\nComparing two Vehicles from different classes: " + inventoryGC[0].getPlateNmb() + " and " + inventoryET[0].getPlateNmb());
							System.out.println(inventoryGC[0].equals(inventoryET[0]));
							break;
						case 2:
							System.out.println("Comparing two Vehicles of the same class with different attribute values: " + inventoryGC[0].getPlateNmb() + " and " + inventoryGC[1].getPlateNmb());
							System.out.println(inventoryGC[0].equals(inventoryGC[1]));
							System.out.println("Comparing two Vehicles of the same class with identical attribute values: " + inventoryGC[0].getPlateNmb() + " and " + inventoryGC[2].getPlateNmb());
							System.out.println(inventoryGC[0].equals(inventoryGC[2]));
							break;
						}
				break;
			case 3:
				// 4. Call getLargestTruck() with inventoryDT[] and display the result
				System.out.println("\nThe largest Diesel Truck is: " + Main.getLargestTruck((Truck[]) inventoryDT));
				break;
			case 4:
				//5. Call the copyVehicles() method on the array of electric trucks and display the result
				Vehicle[] copyET = Main.copyVehicles(inventoryET);
				System.out.println("\nCopy of Electric Trucks inventory:");
				for (int i = 0; i < nmbET; i++)
					System.out.println("\t" + copyET[i]);
				break;
			case 5:
				choiceVersion2 = false;
				break;
			}
			} while (choiceVersion2);
		} else if (version == 3) {
			versionChoice = false;
			System.exit(0);
		}
		else {
			System.out.println("Incorrect entry. Please try again.");
		}
	} while (versionChoice);
		// Close Scanner
				input.close();
	} 
	
// METHODS	
	// Method: Ask the type of vehicle
	public static int askTypeVehicle() {
		System.out.print(" 1. Gasoline Car"
				+ "\n 2. Electric Car"
				+ "\n 3. Diesel Truck"
				+ "\n 4. Electric Truck");
		System.out.print("\nWhat is the vehicle type (1-4): ");
		int typeVehicle = input.nextInt();
		return typeVehicle;
	}

	// Method: Delete vehicle from the principal Inventory
	public static void deleteVehicle(String plate) {
		for (int i = 0; i < nmbVehicles; i++) {
			if (inventory[i].getPlateNmb().equalsIgnoreCase(plate)) {
				inventory[i] = inventory[nmbVehicles - 1];
				inventory[nmbVehicles - 1] = null;
				nmbVehicles--;
				return;
			}    
		}
	}

	// Method: Get Largest Truck
	public static Truck getLargestTruck(Truck[] truckArray) {
		if (truckArray[0] instanceof DieselTruck) {
			Truck largestTruck = truckArray[0];
			for (int i = 1; i < nmbDT; i++) {
				if (largestTruck.getMaxWeight() < truckArray[i].getMaxWeight())
					largestTruck = truckArray[i];
			}
			return largestTruck;
		}
		else {
			Truck largestTruck = truckArray[0];
			for (int i = 1; i < nmbET; i++) {
				if (largestTruck.getMaxWeight() < truckArray[i].getMaxWeight())
					largestTruck = truckArray[i];
			}
			return largestTruck;
		}			
	}
	
	// Method: Copy an Inventory
	public static Vehicle[] copyVehicles(Vehicle[] inventoryToCopy) {
		Vehicle[] copy = new Vehicle[nmbVehicles];
		for (int i = 0; i < nmbVehicles; i++)
			if (inventoryToCopy[i] != null)
				copy[i] = (Vehicle) inventoryToCopy[i].clone();
		return copy;
		}
    }

