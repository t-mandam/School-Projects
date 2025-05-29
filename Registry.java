// --------------------------------------------------------------------
		// Assignment 4
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This code allows you to manage an entire registry including
		// the stamps and the labels of the registry.
		// ------------------------------------------------------------

public class Registry {

	// Attributes: stamps and labels
	private Stamps Stmp;
	private Label[] arrayLabels;
	private int nmbOfLabels;
	
	
	// Constructors
		// Default
		public Registry() {
			Stmp = new Stamps();
			arrayLabels = null;
			nmbOfLabels = 0;
		}
		
		// Stamps and set of Labels
		public Registry(Stamps S1, Label[] aL1) {
			this.Stmp = new Stamps(S1);
			this.nmbOfLabels = aL1.length;
			this.arrayLabels = new Label[this.nmbOfLabels];
			for (int i = 0; i < aL1.length; i++)
				this.arrayLabels[i] = new Label(aL1[i]);
		}
		
		
	// Accessors
		// Total value of stamps in a registry
		public int getStampsValue() {
			return this.Stmp.StampsTotal();
		}
		
		// Number of Labels
		public int getLabels() {
			return nmbOfLabels;
		}
		
		
	// Mutators
		// Add 1 Label
		public int addLabel(String typeParcel, int idUnit, int expDay, int expMonth) {
			Label[] tempLabel = new Label[this.nmbOfLabels];
			for (int i = 0; i < nmbOfLabels; i++)
				tempLabel[i] = this.arrayLabels[i];
			this.nmbOfLabels++;
			this.arrayLabels = new Label[nmbOfLabels];
			for (int i = 0; i < this.nmbOfLabels-1; i++)
				this.arrayLabels[i] = tempLabel[i];
			this.arrayLabels[this.nmbOfLabels-1] = new Label(typeParcel, idUnit, expDay, expMonth);
			return this.nmbOfLabels;
		}
		
		// Remove 1 Label
		public boolean removeLabel(int indexLabel) {
			boolean removed = false;
			if (nmbOfLabels == 0)
				return removed;
			else {
				this.arrayLabels[indexLabel] = this.arrayLabels[this.nmbOfLabels-1];
				this.nmbOfLabels--;
				Label[] tempLabel = new Label[this.nmbOfLabels];
				for (int j = 0; j < nmbOfLabels; j++)
					tempLabel[j] = this.arrayLabels[j];
				this.arrayLabels = new Label[nmbOfLabels];
				for (int i = 0; i < this.nmbOfLabels; i++)
					this.arrayLabels[i] = tempLabel[i];
				return true;
			}
		}
		
		// Add stamps
		public int addStamps(int A, int B, int C, int D, int E) {
			this.Stmp.addStamps(A, B, C, D, E);
			return this.Stmp.StampsTotal();
		}
		
		// Update Label's expiry date
		public void setExpDate(int indexLabel, int day, int month) {
			this.arrayLabels[indexLabel].setExpDay(day);
			this.arrayLabels[indexLabel].setExpMonth(month);
		}
		
		
	// Utilities
		// Compare 2 registries' stamps' total value
		public boolean equalsStampsTotal(Registry R2) {
			return this.Stmp.StampsTotal() == R2.Stmp.StampsTotal();
		}
		
		// Compare 2 registries' stamp breakdown
		public boolean equalsStamps(Registry R2) {
			return this.Stmp.equals(R2.Stmp);
		}
		
		// Compare 2 registries completely
		public boolean equals(Registry R2) {
			return this.Stmp.StampsTotal() == R2.Stmp.StampsTotal() &&
					this.nmbOfLabels == R2.nmbOfLabels;
		}
		
		// Stamp breakdown and labels
		public String toString() {
			if (nmbOfLabels != 0) {
				String labelsDescription = "";
				for (int i = 0; i < nmbOfLabels; i++)
					labelsDescription += this.arrayLabels[i].toString() + "\n";
				return this.Stmp.toString() + "\n" + labelsDescription;
			}
			else return this.Stmp.toString() + "\n" + "No Labels" + "\n";
		}
		
		// Breakdown of stamps
		public String stampBreakdown() {
			return this.Stmp.toString();
		}
}
