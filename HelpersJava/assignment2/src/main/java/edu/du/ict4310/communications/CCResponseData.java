/////////////////////////////
// Author: Michael Schwartz
// CCResponseData class (CC# string and approval T/F)
// Creates class and converts to and from JSON form.
/////////////////////////////
package edu.du.ict4310.communications;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CCResponseData {
	private String ccNumber;
	private Boolean approved;

	public CCResponseData (String number, boolean approved) {
		ccNumber = number;
		this.approved = approved;
	}

	public String toJson() {
		Gson jsonFormatter = new GsonBuilder().create();
		return jsonFormatter.toJson(this);
	}

	public static CCResponseData fromJsonString(String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(jsonString, CCResponseData.class);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CCResponseData :");
		sb.append(" ccNumber: ");
		sb.append(ccNumber);
		sb.append(" approved: ");
		sb.append(approved);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (obj instanceof CCResponseData) {
			CCResponseData res = (CCResponseData) obj;
			result = result && ccNumber.equals(res.ccNumber);
			result = result && approved.equals(res.approved);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ccNumber,approved);
	}

	public String getCcNumber() {
		return ccNumber;
	}
	public Boolean getApproved() {
		return approved;
	}
}
