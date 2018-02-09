package com.kylej.WowAirlinesTracker.http;

import java.util.Calendar;

import com.kylej.WowAirlinesTracker.constants.Utils;

public class QueryParameters {

	// The date the user enters to return
	public final String RETURN_DATE;

	// Whether or not the search is confined to the entered parameters, or to use
	// expanded parameters
	public final boolean expandedSearch;

	// An upper bound on the arrival date to the return trip, usually entered return
	// date + 3 days
	public final String ARRIVAL_DATE_TO;

	// The airport the user wishes to depart from
	public final String FROM_CITY_CODE;

	// Usually same as FROM_CITY_CODE
	public final String ORIGIN;

	// The lower bound on the departure date, usually 3 days prior to the selected
	// departure date
	public final String DEPART_DATE_FROM;

	// DESTINATION city code
	public final String DESTINATION;

	// The DESTINATION airport the user selects, usually same as DESTINATION
	public final String TO_CITY_CODE;

	// The lower bound on the arrival date of the flight, usually the entered
	// arrival date minus 3
	public final String ARRIVAL_DATE_FROM;

	// The actual entered departure date from user
	public final String DEPARTURE_DATE_STRING;

	// The upper bound of the departure date, usually 3 days for extended search
	public final String DEPARTURE_DATE_TO;

	// Whether the trip is round trip or one-way
	public final String ROUND_TRIP;

	// Number of adults in the query
	public final int NUM_ADULTS;

	// Number of children in the query
	public final int NUM_CHILDREN;

	/**
	 * The Query for a one-way flight over a period of days
	 * 
	 * @param leaveDate
	 *            the departure date expected in YYYY-MM-DD format
	 * @param NUM_ADULTS
	 *            number of passengers
	 * @param NUM_CHILDREN
	 *            number of children
	 * @param ORIGIN
	 *            departing airport
	 * @param DESTINATION
	 *            DESTINATION airport
	 */
	public QueryParameters(String leaveDate, int numAdults, int numChildren, String origin, String destination,
			int numDays) {
		Calendar departure = computeDateFromString(leaveDate);

		// need a return date for the query fields but it has no effect of the query
		// results
		Calendar dummyReturn = computeDateFromDate(departure, numDays + 7);
		Calendar depart = computeDateFromString(leaveDate);
		Calendar departEnd = computeDateFromDate(depart, 90);

		DEPARTURE_DATE_STRING = leaveDate;
		DEPART_DATE_FROM = leaveDate;
		DEPARTURE_DATE_TO = Utils.dateStringFromCalendar(departEnd);
		ROUND_TRIP = "false";
		NUM_ADULTS = numAdults;
		NUM_CHILDREN = numChildren;
		RETURN_DATE = Utils.dateStringFromCalendar(dummyReturn);
		expandedSearch = false;
		ARRIVAL_DATE_TO = Utils.dateStringFromCalendar(dummyReturn);
		ARRIVAL_DATE_FROM = leaveDate;
		FROM_CITY_CODE = Utils.airports.get(origin);
		TO_CITY_CODE = Utils.airports.get(destination);
		ORIGIN = FROM_CITY_CODE;
		DESTINATION = TO_CITY_CODE;

	}

	private Calendar computeDateFromDate(Calendar date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));
		calendar.add(Calendar.DATE, offset);

		return calendar;
	}

	private Calendar computeDateFromString(String dateString) {

		int year = Integer.parseInt(dateString.substring(0, 4));
		int month = Integer.parseInt(dateString.substring(5, 7));
		int day = Integer.parseInt(dateString.substring(8));

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		return calendar;
	}

}
