// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public class ElectricCar extends Car {
	private int autonomyRange;
	private String plateNmb;
	private static int plateRef = 1001;
	
	// Default constructor
	public ElectricCar() {
		super();
		this.autonomyRange = 0;
		this.plateNmb = "EC" + plateRef;
		plateRef++;
	}
	// Parameterized constructor
	public ElectricCar(String make, String model, int yearOfProduction, int maxNbrOfPassengers, int autonomyRange) {
		super(make, model, yearOfProduction, maxNbrOfPassengers);
		this.autonomyRange = autonomyRange;
		this.plateNmb = "EC" + plateRef;
		plateRef++;
	}
	
	// Copy constructor
	public ElectricCar(ElectricCar anotherECar) {
		super(anotherECar);
		this.autonomyRange = anotherECar.autonomyRange;
		this.plateNmb = "EC" + plateRef;
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
	public boolean equals(Object other) {
		if (super.equals(other)) {
			ElectricCar otherECar = (ElectricCar) other;
			return this.autonomyRange == otherECar.autonomyRange;
		}
		return false;
	}
	
	@Override
	public Object clone() {
		return new ElectricCar(this.getMake(), this.getModel(), this.getYearOfProduction(), this.getMaxNbrOfPassengers(), this.getAutonomyRange());
	}
}
