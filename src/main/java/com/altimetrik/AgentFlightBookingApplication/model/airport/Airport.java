package com.altimetrik.AgentFlightBookingApplication.model.airport;

import java.util.Comparator;
import java.util.Objects;

public class Airport implements Comparable<Airport>, Comparator<Airport> {
	private String mName; // Full name of the airport
	private String mCode; // Three character code of the airport
	private String mTimeZone; // Time zone of airport

	/**
	 * Default constructor
	 * 
	 * Constructor without params. Requires object fields to be explicitly set
	 * using setter methods
	 * 
	 */
	public Airport() {
		mName = "";
		mCode = "";
	}

	/**
	 * Initializing constructor.
	 * 
	 * All attributes are initialized with input values
	 * 
	 * @param name
	 *            The human readable name of the airport
	 * @param code
	 *            The 3 letter code for the airport
	 * 
	 * @throws IllegalArgumentException
	 *             is any parameter is invalid
	 */
	public Airport(String name, String code) {
		if (!isValidName(name))
			throw new IllegalArgumentException(name);
		if (!isValidCode(code))
			throw new IllegalArgumentException(code);

		mName = name;
		mCode = code;
	}

	/**
	 * Convert object to printable string of format "Code, (lat, lon), Name"
	 * 
	 * @return the object formatted as String to display
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(mCode).append(", ");
		sb.append(mName).append(", ");
		sb.append("Time Zone: ").append(mTimeZone);

		return sb.toString();
	}

	/**
	 * Set the airport name
	 * 
	 * @param name
	 *            The human readable name of the airport
	 * @throws IllegalArgumentException
	 *             is name is invalid
	 */
	public void name(String name) {
		if (isValidName(name))
			mName = name;
		else
			throw new IllegalArgumentException(name);
	}

	/**
	 * get the airport name
	 * 
	 * @return Airport name
	 */
	public String name() {
		return mName;
	}

	/**
	 * set the airport 3 letter code
	 * 
	 * @param code
	 *            The 3 letter code for the airport
	 * @throws IllegalArgumentException
	 *             is code is invalid
	 */
	public void code(String code) {
		if (isValidCode(code))
			mCode = code;
		else
			throw new IllegalArgumentException(code);
	}

	/**
	 * Get the 3 letter airport code
	 * 
	 * @return The 3 letter airport code
	 */
	public String code() {
		return mCode;
	}

	/**
	 * Set the time zone for the airport
	 * 
	 * @param timeZone
	 *            The timezone of the airport
	 * @throws IllegalArgumentException
	 *             is timezone is invalid
	 */
	public void timeZone(String timeZone) {
		mTimeZone = timeZone;
	}

	/**
	 * get the timezone for the airport
	 * 
	 * @return The timezone of the airport
	 */
	public String timezone() {
		return mTimeZone;
	}

	/**
	 * Compare two airports based on 3 character code
	 * 
	 * This implementation delegates to the case insensitive version of string
	 * compareTo
	 * 
	 * @return results of String.compareToIgnoreCase
	 */
	public int compareTo(Airport other) {
		return this.mCode.compareToIgnoreCase(other.mCode);
	}

	/**
	 * Compare two airports for sorting, ordering
	 * 
	 * Delegates to airport1.compareTo for ordering by 3 character code
	 * 
	 * @param airport1
	 *            the first airport for comparison
	 * @param airport2
	 *            the second / other airport for comparison
	 * @return -1 if airport ordered before airport2, +1 of airport1 after
	 *         airport2, zero if no different in order
	 */
	public int compare(Airport airport1, Airport airport2) {
		return airport1.compareTo(airport2);
	}

	/**
	 * Determine if two airport objects are the same airport
	 * 
	 * Compare another object to this airport and return true if the other
	 * object specifies the same airport as this object
	 * 
	 * @param obj
	 *            is the object to compare against this object
	 * @return true if the param is the same airport as this, else false
	 */
	@Override
	public boolean equals(Object obj) {
		// every object is equal to itself
		if (obj == this)
			return true;

		// null not equal to anything
		if (obj == null)
			return false;

		// can't be equal if obj is not an instance of Airport
		if (!(obj instanceof Airport))
			return false;

		// if all fields are equal, the Airports are the same
		Airport rhs = (Airport) obj;
		if ((rhs.mName.equals(mName)) && (rhs.mCode.equals(mCode))) {
			return true;
		}

		return false;
	}

	/**
	 * Determine if object instance has valid attribute data
	 * 
	 * Verifies the name is not null and not an empty string. Verifies code is 3
	 * characters in length.
	 * 
	 * @return true if object passes above validation checks
	 * 
	 */
	public boolean isValid() {

		// If the name isn't valid, the object isn't valid
		if ((mName == null) || (mName == ""))
			return false;

		// If we don't have a 3 character code, object isn't valid
		if ((mCode == null) || (mCode.length() != 3))
			return false;

		return true;
	}

	/**
	 * Check for invalid 3 character airport code
	 * 
	 * @param code
	 *            is the airport code to validate
	 * @return false if null or not 3 characters in length, else assume valid
	 *         and return true
	 */
	public boolean isValidCode(String code) {
		// If we don't have a 3 character code it can't be valid valid
		if ((code == null) || (code.length() != 3))
			return false;
		return true;
	}

	/**
	 * Check that input is a valid airport from list of airports
	 * 
	 * @param code
	 *            is the airport code to validate
	 * @return false if null or not 3 characters in length, else assume valid
	 *         and return true
	 */
	public static boolean isValidInputCode(String code) {
		for (Airport airport : Airports.getInstance()) {
			String airportCode = airport.code();
			if (airportCode.equals(code)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check for invalid airport name.
	 * 
	 * @param name
	 *            is the name of the airport to validate
	 * @return false if null or empty string, else assume valid and return true
	 */
	public boolean isValidName(String name) {
		// If the name is null or empty it can't be valid
		if ((name == null) || (name == ""))
			return false;
		return true;
	}

	public String quickTimeZone() {
		String timeZone = "invalid";
		String LA = "America/Los_Angeles";
		String NY = "America/New_York";
		String CH = "America/Chicago";
		String HI = "Pacific/Honolulu";
		String DEN = "America/Denver";
		String ANC = "America/Anchorage";
		String PHX = "America/Phoenix";

		if (Objects.equals(mCode, "ANC")) {
			timeZone = ANC;
		} else if (Objects.equals(mCode, "ATL")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "AUS")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "BDL")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "BNA")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "BOS")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "BWI")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "CLE")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "CLT")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "CMH")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "CVG")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "DCA")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "DEN")) {
			timeZone = DEN;
		} else if (Objects.equals(mCode, "DFW")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "DTW")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "EWR")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "FLL")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "HNL")) {
			timeZone = HI;
		} else if (Objects.equals(mCode, "HOU")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "IAD")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "IAH")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "IND")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "JFK")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "LAS")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "LAX")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "LGA")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "MCI")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "MCO")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "MDW")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "MEM")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "MIA")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "MSP")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "MSY")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "OAK")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "ONT")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "ORD")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "PDX")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "PHL")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "PHX")) {
			timeZone = PHX;
		} else if (Objects.equals(mCode, "PIT")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "RDU")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "RSW")) {
			timeZone = NY;
		} else if (Objects.equals(mCode, "SAN")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "SAT")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "SEA")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "SFO")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "SJC")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "SLC")) {
			timeZone = DEN;
		} else if (Objects.equals(mCode, "SMF")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "SNA")) {
			timeZone = LA;
		} else if (Objects.equals(mCode, "STL")) {
			timeZone = CH;
		} else if (Objects.equals(mCode, "TPA")) {
			timeZone = NY;
		}

		return timeZone;
	}

}
