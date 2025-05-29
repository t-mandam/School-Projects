// -------------------------------------------------------------------
		// Assignment 4
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This code allows to manage all stamps in a registry. Some
		// operations offered are to add stamps, to check their total
		// value, or to have a full description of available stamps.
		// -----------------------------------------------------------

public class Stamps {

	// Attributes: Stamp categories (quantity and value)
	private int category_A, category_B, category_C, category_D, category_E;
	static private final int VALUE_A=2, VALUE_B=5, VALUE_C=10, VALUE_D=15, VALUE_E=20;
	
	
	// Constructors
		// Default
		public Stamps(){
			category_A = category_B = category_C = category_D = category_E = 0;
		}
		
		// Set 5 Stamps
		public Stamps(int A, int B, int C, int D, int E) {
			category_A = A;
			category_B = B;
			category_C = C;
			category_D = D;
			category_E = E;
		}
		
		// Copy another stamp registry
		public Stamps(Stamps ogStamp) {
			this.category_A = ogStamp.category_A;
			this.category_B = ogStamp.category_B;
			this.category_C = ogStamp.category_C;
			this.category_D = ogStamp.category_D;
			this.category_E = ogStamp.category_E;
		}
	
		
	// Accessors (A to E)
		public int getStampA() {
			return category_A;
		}
		
		public int getStampB() {
			return category_B;
		}
		
		public int getStampC() {
			return category_C;
		}
		
		public int getStampD() {
			return category_D;
		}
		
		public int getStampE() {
			return category_E;
		}
		
		
	// Mutators (A to E)
		public void setStampA(int newA) {
			this.category_A = newA;
		}
		
		public void setStampB(int newB) {
			this.category_B = newB;
		}
		public void setStampC(int newC) {
			this.category_C = newC;
		}
		public void setStampD(int newD) {
			this.category_D = newD;
		}
		public void setStampE(int newE) {
			this.category_E = newE;
		}
		
		
	// Utilities
		// Add stamps to each category
		public void addStamps(int addA, int addB, int addC, int addD, int addE) {
			category_A += addA; category_B += addB;
			category_C += addC; category_D += addD;
			category_E += addE;
		}
		
		// Get total shipment value
		public int StampsTotal() {
			return category_A*VALUE_A + category_B*VALUE_B + category_C*VALUE_C + 
					category_D*VALUE_D + category_E*VALUE_E;
		}
		
		// Breakdown of stamps in registry
		public String toString() {
			return category_A + " x $" + VALUE_A + " + " +
					category_B + " x $" + VALUE_B + " + " +
					category_C + " x $" + VALUE_C + " + " +
					category_D + " x $" + VALUE_D + " + " +
					category_E + " x $" + VALUE_E;
		}
		
		// Compare 2 registries' stamp breakdown
		public boolean equals(Stamps S2) {
			return this.category_A == S2.category_A && this.category_B == S2.category_B
					&& this.category_C == S2.category_C && this.category_D == S2.category_D
					&& this.category_E == S2.category_E;
		}
}
