// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public abstract class Truck extends Vehicle {
	protected int maxWeight;

	// Default constructor 
	public Truck() {
		super();
		maxWeight = 0;
	}
	// Parameterized constructor
	public Truck(String make, String model, int yearOfProduction, int maxWeight) {
		super(make, model, yearOfProduction);
		this.maxWeight = maxWeight; 
		}
	// Copy constructor
	public Truck(Truck otherTruck) {
		super(otherTruck);
		this.maxWeight = otherTruck.maxWeight;
	}
	
	// getters and setters
	public int getMaxWeight() {
		return this.maxWeight;
	}
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
		
	// toString method
	@Override
	public String toString() {
		return super.toString() +  ", Max weight (kg): " + this.maxWeight;
	}
	
	// equals method
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			Truck otherTruck = (Truck) other;
			return this.maxWeight == otherTruck.maxWeight;
		}
		return false;
	}
}
