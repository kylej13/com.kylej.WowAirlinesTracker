package com.kylej.WowAirlinesTracker.http;

public class QueryParameters {

	// The date the user inputs to depart
	private String leaveDate;

	// The date the user enters to return
	private String returnDate;

	// Whether or not the search is confined to the entered parameters, or to use
	// expanded parameters
	private boolean expandedSearch;

	public QueryParameters(String leaveDate, String returnDate, boolean expandedSearch) {

		this.leaveDate = leaveDate;
		this.returnDate = returnDate;
		this.expandedSearch = expandedSearch;
	}
}
