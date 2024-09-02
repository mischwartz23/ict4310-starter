/////////////////////////////
// Author: Michael Schwartz
// Test of client and server interaction
/////////////////////////////
package edu.du.ict4310;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.du.ict4310.client.SimpleClient;
import edu.du.ict4310.server.SimpleJettyServer;

class ClientServerTest {

	private SimpleJettyServer server;
	private int port = 8001;
	String query = "/check";

	@BeforeEach
	void setup() throws Exception {
		server = new SimpleJettyServer(port);
		server.start();
	}

	@Test
	void connectTest() throws Exception {
		SimpleClient client;
		client = new SimpleClient();
		String uri = "http://localhost:" + port + query;
		client.get(uri);
		HttpResponse<String> response = client.getPreviousResponse();
		assertTrue(response.statusCode() == 200);
		assertEquals("{ \"status\": \"ok\"}\n",response.body());
	}

	@AfterEach
	void teardown() throws Exception {
		server.stop();
	}

}
