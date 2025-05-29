// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package client;
import vehicles.Vehicle;

public class Client {

	protected int clientID;
	protected String clientName;
	protected int nmbLeasedVehicles = 0;
	private final int LEASE_LIMIT = 999;
	protected Vehicle[] inventoryLease = new Vehicle[LEASE_LIMIT];
	// How I see it: Each position in the array is a vehicle for each client.
	private static int nextID = 101;

	// Parameterized constructor
	public Client(String clientName, Vehicle[] inventoryLease) {
		this.clientID = nextID++;
		this.clientName = clientName;
		this.inventoryLease = inventoryLease;
	}

	// Do we want a Copy constructor? Copying a client seems weird

	public Client(String clientName) {
		this.clientName = clientName;
		this.clientID = nextID++;
		this.nmbLeasedVehicles = 0;
		this.inventoryLease = new Vehicle[LEASE_LIMIT];
	}

	// Getters and Setters
	public String getClientName() {
		return this.clientName;
	}
	public int getClientID() {
		return this.clientID;
	}
	public int getNmbLeasedVehicles() {
		return this.nmbLeasedVehicles;
	}
	public Vehicle[] getInventoryLease() {
		Vehicle[] temp = new Vehicle[this.inventoryLease.length];
		for (int i = 0; i < this.inventoryLease.length; i++)
			temp[i] = (Vehicle) this.inventoryLease[i].clone();
		return temp;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	// toString
	@Override
	public String toString() {
		String listLeasedVehicles = "";
		for (int i = 0; i < this.nmbLeasedVehicles; i++)
			listLeasedVehicles += "\n\t" + inventoryLease[i].toString();
		return this.clientName + " (ID: " + this.clientID + ")" + listLeasedVehicles;
	}
	// equals
	@Override
	public boolean equals(Object o2) {
		if (o2 == null || o2.getClass() != this.getClass())
			return false;
		Client c2 = (Client) o2;
		return this.clientID == c2.clientID;
	}

	// Methods
	// Method 1: Verification Vehicle is Leased by Client
	public boolean leasing(String plateNmb) {
		for (int i = 0; i < nmbLeasedVehicles; i++) {
			if (this.inventoryLease[i].getPlateNmb().equalsIgnoreCase(plateNmb))
			    return true; // The Vehicle is already leased by the Client
		}
		return false;
	}
	// Method 2: Lease a Vehicle
	public void leaseVehicle(Vehicle vehicleToLease) {
		this.inventoryLease[this.nmbLeasedVehicles++] = vehicleToLease; 
		vehicleToLease.setIsLeased(true);
	}

	// Method 3: Return a Vehicle
	public void returnVehicle(Vehicle vehicleToReturn) {
		for (int i = 0; i < this.nmbLeasedVehicles; i++) {  // Fixed loop syntax here
   			if (inventoryLease[i].getPlateNmb().equalsIgnoreCase(vehicleToReturn.getPlateNmb())) {
      				this.inventoryLease[i].setIsLeased(false);
      				this.inventoryLease[i] = this.inventoryLease[this.nmbLeasedVehicles - 1];
	  			this.inventoryLease[--this.nmbLeasedVehicles] = null;
	  			break;
      			}
	 	}
	}
}



