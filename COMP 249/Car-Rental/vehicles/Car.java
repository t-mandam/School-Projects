// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public abstract class Car extends Vehicle {
	private int maxNbrOfPassengers; 
	
	// Default constructor
	public Car() {
		super(); // Calling the default constructor of the Vehicle class
		this.maxNbrOfPassengers = 0;
	}
	// Parameterized constructor
	public Car(String make, String model, int yearOfProduction, int maxNbrOfPassengers) {
		super(make, model, yearOfProduction); // Calling the parent parameterized constructor
		this.maxNbrOfPassengers = maxNbrOfPassengers;
	}
	// Copy constructor
	public Car(Car anotherCar) {
		super(anotherCar); // Calling the parent copy  constructor (Vehicle class)
		this.maxNbrOfPassengers = anotherCar.maxNbrOfPassengers;
	}
	
	// Getters and setters
	public int getMaxNbrOfPassengers() {
		return this.maxNbrOfPassengers;
	}
	public void setMaxNbrOfPassengers(int maxNbrOfPassengers) {
		this.maxNbrOfPassengers = maxNbrOfPassengers;
	}
	
	// toString method
	@Override
	public String toString() {
		return super.toString() + ", Seats: " + this.maxNbrOfPassengers;
	}
	
	// equals method
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			Car otherCar = (Car) other;
			return this.maxNbrOfPassengers == otherCar.maxNbrOfPassengers;
		}
		return false;	
	}

}
