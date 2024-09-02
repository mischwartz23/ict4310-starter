/////////////////////////////
// Author: Michael Schwartz
// Test of CCResponseData class
/////////////////////////////
package edu.du.ict4310;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.du.ict4310.communications.CCRequestData;
import edu.du.ict4310.communications.CCResponseData;

class CCResponseDataTest {

	private String[] testStrings = { "1234-5768-9012-3456", "2345-6789-0123-4567" };
	private boolean[] values = { true, false };

	@Test
	void equality() {
		for (String s : testStrings) {
			for (boolean value : values) {
				CCResponseData l = new CCResponseData(s, value);
				CCResponseData r = new CCResponseData(s, value);
				assertTrue(l.equals(r), "Calling equals fails");
				assertEquals(l, r, "Asserting equals fails");
			}
		}
	}

	@Test
	void inequality() {
		for (String s : testStrings) {
			CCResponseData l = new CCResponseData(s, values[0]);
			CCResponseData r = new CCResponseData(s, values[1]);
			assertFalse(l.equals(r), "Calling equals fails");
			assertNotEquals(l, r, "Asserting equals fails");
		}
		for (boolean value : values) {
			CCResponseData l = new CCResponseData(testStrings[0], value);
			CCResponseData r = new CCResponseData(testStrings[1], value);
			assertFalse(l.equals(r), "Calling equals fails");
			assertNotEquals(l, r, "Asserting equals fails");
		}
	}

	@Test
	void convert() {
		for (String s : testStrings) {
			for (boolean value : values) {
				CCResponseData request = new CCResponseData(s, value);
				String jsonString = request.toJson();
				System.out.println(request + " <-> " + jsonString);
				CCResponseData rebuild = CCResponseData.fromJsonString(jsonString);
				assertEquals(request, rebuild,
						"Rebuilt request is not the same as the original request: " + request + " vs. " + rebuild);
			}
		}
	}

}
