/////////////////////////////
// Author: Michael Schwartz
// Test of CCRequestData class
/////////////////////////////
package edu.du.ict4310;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.du.ict4310.communications.CCRequestData;

class CCRequestDataTest {

	private String[] testStrings = {
		"1234-5768-9012-3456",
		"2345-6789-0123-4567"
	};

	@Test
	void equality() {
		for ( String s: testStrings) {
			CCRequestData l = new CCRequestData(s);
			CCRequestData r = new CCRequestData(s);
			assertTrue(l.equals(r),"Calling equals fails");
			assertEquals(l,r,"Asserting equals fails");
		}
	}

	@Test
	void inequality() {
		CCRequestData l = new CCRequestData(testStrings[0]);
		CCRequestData r = new CCRequestData(testStrings[1]);
		assertFalse(l.equals(r),"Calling equals fails");
		assertNotEquals(l,r,"Asserting equals fails");
	}

	@Test
	void convert() {
		for (String s: testStrings) {
			CCRequestData request = new CCRequestData(s);
			String jsonString = request.toJson();
			System.out.println(request+" <-> "+jsonString);
			CCRequestData rebuild = CCRequestData.fromJsonString(jsonString);
			assertEquals(request,rebuild, "Rebuilt request is not the same as the original request: "+request+" vs. "+rebuild);
		}
	}

}
