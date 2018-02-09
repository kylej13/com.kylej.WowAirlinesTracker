package com.kylej.WowAirlinesTracker.constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Utils {
	public static final HashMap<String, String> airports;
	public static final HashMap<Integer, String> airportsNumbered;
	static {
		airports = new HashMap<String, String>();
		airportsNumbered = new HashMap<Integer, String>();
		airports.put("BWI", "one");

		BufferedReader br = null;
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader("airports.json");
			br = new BufferedReader(fr);

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				sb.append(currentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		}
		int num = 1;
		try {
			JSONArray airportsJSON = new JSONArray(sb.toString());
			for (int x = 0; x < airportsJSON.length(); x++) {
				JSONObject obj = airportsJSON.getJSONObject(x);
				String origin = (String) obj.get("origin");
				String origin_desc = (String) obj.get("origin_desc");
				airports.put(origin_desc, origin);
				airportsNumbered.put(num, origin_desc);
				num++;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public static String dateStringFromCalendar(Calendar date) {
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DATE);

		StringBuilder sb = new StringBuilder();
		sb.append(date.get(Calendar.YEAR) + "-");
		if (month < 10)
			sb.append("0" + month);
		else
			sb.append(month);
		sb.append("-");
		if (day < 10)
			sb.append("0" + day);
		else
			sb.append(day);

		return sb.toString();

	}

}
