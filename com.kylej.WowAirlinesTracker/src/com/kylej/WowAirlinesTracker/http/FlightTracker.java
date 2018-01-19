package com.kylej.WowAirlinesTracker.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FlightTracker {

	// An upper bound on the arrival date to the return trip, usually entered return
	// date + 3 days
	private String arrivalDateTo;

	// The airport the user wishes to depart from
	private String departingAirportCode;

	// The lower bound on the departure date, usually 3 days prior to the selected
	// departure date
	private String departDateFrom;

	// The destination airport the user selects
	private String destinationAirportCode;
	
	// The actual entered return date from the user
	private String returnDate;
	
	// The lower bound on the arrival date of the flight, usually the entered arrival date minus 3
	private String arrivalDateFrom;
	private String departureDateString;
	private String departureDateTo;

	private boolean roundTrip;
	private int numAdults;
	private int numChildren;

	public static void main(String args[]) {

		String urlString = "https://booking.wowair.com/api/midgardur/v4/flight?arrivalDateTo=2018-01-31&isCampaignSearch=undefined&fromCityCode=BWI&fareTypeCategories=1&useFlexDates=undefined&apiKey=&fareTypeCategory=1&origin=BWI&allInclusive=undefined&departDateFrom=2018-01-25&infants=0&destination=SXF&culture=us&lang=en_US&returnDateString=2018-01-30&interline=undefined&currency=USD&arrivalDateFrom=2018-01-29&departureDateString=2018-01-26&toCityCode=SXF&roundTrip=true&adults=1&promocode=&departDateTo=2018-01-27&children=0";
		// String urlString = "https://jsonplaceholder.typicode.com/posts";
		// String urlString = "https://wowair.is/api/wow/routes";
		HttpsURLConnection con = null;
		String jsonString;
		JSONObject myResponse = null;
		try {
			URL url = new URL(urlString);
			con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			HttpGet request = new HttpGet(urlString);
			HttpResponse response;
			HttpClient client = HttpClientBuilder.create().build();

			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			String result = convertStreamToString(inputStream);
			// JSONArray results = new JSONArray(result);
			JSONObject results = new JSONObject(result);
			JSONArray flights = results.getJSONArray("flights");
			System.out.println(flights.toString());

			for (int x = 0; x < flights.length(); x++) {
				JSONObject currFlight = (JSONObject) flights.get(x);
				currFlight.getString("departureTime");
				currFlight.getString("arrivalTime");
			}

			// System.out.println(results);
		} catch (MalformedURLException e) {
			System.out.println("URL not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
