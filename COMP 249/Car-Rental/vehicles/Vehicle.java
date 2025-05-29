// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;
public abstract class Vehicle {
	
	protected String plateNmb;
	protected String make;
	protected String model;
	protected int yearOfProduction;
	protected static Vehicle[] inventory;
	protected boolean isLeased = false;
	
	// You are right. I decided to put it static instead so that its easy to search/delete etc.
	public static final int MAX_VEHICLES = 100000; // Reasonable maximum

	// Parameterized constructor
	public Vehicle(String make, String model, int yearOfProduction) {
		this.make = make;
		this.model = model;
		this.yearOfProduction = yearOfProduction;
	}
	
	// Default constructor 
	public Vehicle() { 
		this("Unknown make", "Unknown model", 0000);
	}
	
	// Copy constructor
	public Vehicle(Vehicle other) {
		this(other.make, other.model, other.yearOfProduction);
	}
	
	// Getters and setters methods
	public String getMake() {
	return this.make;
	}
	public String getModel() {
	return this.model;
	}
	public int getYearOfProduction() {
	return this.yearOfProduction;
	}
	public abstract String getPlateNmb();
	public boolean getIsLeased() {
		return this.isLeased;
	}
	
	public void setMake(String make) {
	this.make = make;
	}
	public void setModel(String model) {
	this.model = model;
	}
	public void setYearOfProduction(int yearOfProduction) {
	this.yearOfProduction = yearOfProduction;
	}
	public void setIsLeased(boolean isLeased) {
        this.isLeased = isLeased;
    }
	@Override
	public String toString() {
	return make + " " + model + ", " + yearOfProduction;
	}
	
	@Override
	public boolean equals(Object other) { 
		if (this == other)
			return true;
		if (other == null || this.getClass() != other.getClass()) {
			return false;
		}
		Vehicle otherVehicle = (Vehicle) other;
		return this.make.equalsIgnoreCase(otherVehicle.make) &&
		this.model.equalsIgnoreCase(otherVehicle.model) &&
		this.yearOfProduction == otherVehicle.yearOfProduction;
	}

	@Override
	public abstract Object clone();
}

