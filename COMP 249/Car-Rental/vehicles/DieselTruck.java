// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public class DieselTruck extends Truck {
	private int fuelTankCapacity; // in Liters
	private static int plateRef = 1001;

	// Parameterized constructor
	public DieselTruck(String make, String model, int yearOfProduction, int maxWeight, int fuelTankCapacity) {
		super(make, model, yearOfProduction, maxWeight);
		this.fuelTankCapacity = fuelTankCapacity;
		this.plateNmb = "DT" + plateRef;
		plateRef++;
	}
	
	// Default constructor
	public DieselTruck() {
		super();
		fuelTankCapacity = 0;
		this.plateNmb = "DT" + plateRef;
		plateRef++;
	}
	
	// Copy constructor
	public DieselTruck(DieselTruck other) {
		super(other);
		this.fuelTankCapacity = other.fuelTankCapacity;
		this.plateNmb = "DT" + plateRef;
		plateRef++;
	}
	
	// Getters and setters
	public int getFuelTankCapacity() {
		return this.fuelTankCapacity;
	}
	public String getPlateNmb() {
		return this.plateNmb;
	}
	public void setFuelTankCapacity(int fuelTankCapacity) {
		this.fuelTankCapacity = fuelTankCapacity;
	}
	
	// toString method
	@Override
	public String toString() {
		return super.toString() + ", Fuel Tank (L): " + this.fuelTankCapacity + ", Plate: " + this.plateNmb;
	}
	
	// equals method
	@Override
	public boolean equals(Object other) {
		if (super.equals(other)) {
			DieselTruck otherDTruck = (DieselTruck) other;
			return this.fuelTankCapacity == otherDTruck.fuelTankCapacity;
		}
		return false;
	}

	@Override
	public Object clone() {
		return new DieselTruck(this.getMake(), this.getModel(), this.getYearOfProduction(), this.getMaxWeight(), this.getFuelTankCapacity());
	}
}
