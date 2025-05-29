// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public class ElectricTruck extends Truck {
	private int autonomyRange;
	private static int plateRef = 1001;
	
	// Default constructor
	public ElectricTruck() {
		super();
		autonomyRange = 0;
		this.plateNmb = "ET" + plateRef;
		plateRef++;
	}
	
	// Parameterized constructor
	public ElectricTruck(String make, String model, int yearOfProduction, int maxWeight, int autonomyRange) {
		super(make, model, yearOfProduction, maxWeight);
		this.autonomyRange = autonomyRange;
		this.plateNmb = "ET" + plateRef;
		plateRef++;
	}
	
	// Copy constructor
	public ElectricTruck(ElectricTruck other) {
		super(other);
		this.autonomyRange = other.autonomyRange;
		this.plateNmb = "ET" + plateRef;
		plateRef++;
	}
	
	// Getters and setters
	public int getAutonomyRange() {
		return this.autonomyRange;
	}
	public String getPlateNmb() {
		return this.plateNmb;
	}
	public void setAutonomyRange(int autonomyRange) {
		this.autonomyRange = autonomyRange;
	}
	
	// toString method
	@Override
	public String toString() {
		return super.toString() + ", Autonomy (km): " + this.autonomyRange + ", Plate: " + this.plateNmb;
	}
	
	// equals method
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			ElectricTruck otherETruck = (ElectricTruck) other;
			return this.autonomyRange == otherETruck.autonomyRange;
		}
		return false;
	}

	@Override
	public Object clone() {
		return new ElectricTruck(this.getMake(), this.getModel(), this.getYearOfProduction(), this.getMaxWeight(), this.getAutonomyRange());
	}
}
