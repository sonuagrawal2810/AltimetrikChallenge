package com.altimetrik.AgentFlightBookingApplication.model.airport;

import java.util.ArrayList;

/**
 * 
 * @author Sonu Agrawal
 *
 */
public class Airports extends ArrayList<Airport> {
	private static final long serialVersionUID = 1L;

	private static Airports mInstance;
	private ArrayList<String> list = null;

	public static Airports getInstance() {
		if (mInstance == null)
			mInstance = new Airports();

		return mInstance;
	}

	private Airports() {
		list = new ArrayList<String>();
	}

	// retrieve array from anywhere
	public ArrayList<String> getArray() {
		return this.list;
	}

	// Add element to array
	public void addToArray(String value) {
		list.add(value);
	}

}
