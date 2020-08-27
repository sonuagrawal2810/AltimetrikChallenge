package com.altimetrik.AgentFlightBookingApplication.driver;

import java.text.ParseException;
import java.util.Scanner;

import com.altimetrik.AgentFlightBookingApplication.controller.SearchController;
import com.altimetrik.AgentFlightBookingApplication.dao.ServerInterface;

/**
 * 
 * @author Sonu Agrawal
 *
 */
public class Driver {

	public static void main(String[] args) throws ParseException {

		// import Airports
		ServerInterface.INSTANCE.getAirports("Sonu");

		// Try to get a list of airplanes
		ServerInterface.INSTANCE.getAirplanes("Sonu");

		// Begin search
		String input;
		Scanner scan = new Scanner(System.in);
		boolean validInput = false;

		do {
			System.out.println("***************Do you want to start a new search?(yes/no)***************");
			input = scan.nextLine();

			if (input.toUpperCase().equals("YES")) {
				validInput = true;
				new SearchController();
			} else if (input.toUpperCase().equals("NO")) {
				System.out.println("**********Thanks for your visiting!***********");
				System.exit(-1);
			}
		} while (validInput);

	}
}
