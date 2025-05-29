// ------------------------------------------------
// Assignment #1 
// Written by: Najlaa Achouhal (40312761) and Theo-Dayan Mandamiento Rodriguez (40310410)
// ------------------------------------------------
package vehicles;

public class GasolineCar extends Car {
	private static int plateRef = 1001;
	
	// Parameterized constructor
	public GasolineCar(String make, String model, int yearOfProduction, int maxNbrOfPassengers) {
	        super(make, model, yearOfProduction, maxNbrOfPassengers);
	        this.plateNmb = "GC" + plateRef;
	        plateRef++;
	}
	
	// Default constructor
	public GasolineCar() {
		super();
		this.plateNmb = "GC" + plateRef;
		plateRef++;
	}
	
	// Copy constructor
	public GasolineCar(GasolineCar otherCar) {
		super(otherCar);
		this.plateNmb = "GC" + plateRef;
		plateRef++;
	}
	
	// Getter
	public String getPlateNmb() {
		return this.plateNmb;
	}
	
	@Override
	public String toString() {
		 return super.toString() + ", Plate: " + this.plateNmb;
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}

	@Override
	public Object clone() {
		return new GasolineCar(this.getMake(), this.getModel(), this.getYearOfProduction(), this.getMaxNbrOfPassengers());
	}
} 
