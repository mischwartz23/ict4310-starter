/////////////////////////////
// Author: Michael Schwartz
// CCRequestData class (CC# string only)
// Creates class and converts to and from JSON form.
/////////////////////////////
package edu.du.ict4310.communications;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CCRequestData {
	private String ccNumber;

	public CCRequestData(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String toJson() {
		Gson jsonFormatter = new GsonBuilder().create();
		return jsonFormatter.toJson(this);
	}

	public static CCRequestData fromJsonString(String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonString, CCRequestData.class);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CCRequestData: ");
		sb.append("ccNumber: ");
		sb.append(ccNumber);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (obj instanceof CCRequestData) {
			CCRequestData req = (CCRequestData) obj;
			result = result && ccNumber.equals(req.ccNumber);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ccNumber);
	}

}
