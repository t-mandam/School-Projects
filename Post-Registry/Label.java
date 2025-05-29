// ---------------------------------------------------------------------
		// Assignment 4
		// Written by: Theo-Dayan Mandamiento Rodriguez, 40310410
		// For COMP 248 Section P - Fall 2024
		//
		// This code allows you to manage  all pre-paid shipment 
		// subscription labels in a registry. Some essential tasks such
		// as checking the parcel or its expiry date are defined here.
		// -------------------------------------------------------------

public class Label {

	// Attributes: type of parcel, Unit ID, expiry date
	private String typeParcel;
	private int idUnit, expDay, expMonth;
	static private final int MIN_DATE = 0, MAX_DAY = 31, MAX_MONTH = 12;
	
	
	// Constructors
		// Default
		public Label() {
			typeParcel = "Standard";
			idUnit = 98000000;
			expDay = 0;
			expMonth = 0;
		}
		
		// Set complete Label
		public Label(String type, int id, int day, int month) {
			this.typeParcel = type;
			this.idUnit = id;
			if (day <= MAX_DAY && day > MIN_DATE)
				this.expDay = day;
			else this.expDay = 0;
			if (month <= MAX_MONTH && month > MIN_DATE)
				this.expMonth = month;
			else this.expMonth = 0;
		}
		
		// Copy label
		public Label(Label ogLabel) {
			this.typeParcel = ogLabel.typeParcel;
			this.idUnit = ogLabel.idUnit;
			this.expDay = ogLabel.expDay;
			this.expMonth = ogLabel.expMonth;
		}
		
		
	// Accessors
		// Type of Parcel
		public String getParcel() {
			return typeParcel;
		}
		
		// ID of the unit
		public int getID() {
			return idUnit;
		}
		
		// Expiry day
		public int getExpDay() {
			return expDay;
		}
		
		// Expiry month
		public int getExpMonth() {
			return expMonth;
		}
	
		
	// Mutators
		// Expiry day
		public void setExpDay(int newDay) {
			if (newDay <= MAX_DAY && newDay > MIN_DATE)
				this.expDay = newDay;
			else this.expDay = 0;
		}
		
		// Expiry month
		public void setExpMonth(int newMonth) {
			if (newMonth <= MAX_MONTH && newMonth > MIN_DATE)
				this.expMonth = newMonth;
			else this.expMonth = 0;
		}
		
		
	// Utilities
		// Label description
		public String toString() {
			if (expDay < 10 && expMonth < 10)
				return typeParcel + " - " + idUnit + " - 0" + expDay + "/0" + expMonth;
			else if (expDay < 10)
				return typeParcel + " - " + idUnit + " - 0" + expDay + "/" + expMonth;
			else if (expMonth < 10)
				return typeParcel + " - " + idUnit + " - " + expDay + "/0" + expMonth;
			else 
				return typeParcel + " - " + idUnit + " - " + expDay + "/" + expMonth;
		}
		
		// Compare 2 labels' information
		public boolean equals(Label L2) {
			return this.typeParcel.equals(L2.typeParcel) && this.idUnit == L2.idUnit
					&& this.expDay == L2.expDay && this.expMonth == L2.expMonth;
		}
}
