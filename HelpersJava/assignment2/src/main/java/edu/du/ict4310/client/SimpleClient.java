/////////////////////////////
// Author: Michael Schwartz from Oracle Javadoc
// SimpleClient with logging
/////////////////////////////
package edu.du.ict4310.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Logger;

public class SimpleClient {
	private static int port=8001;

	private static String uriString = "http://localhost:"+port+"/app";
	private HttpResponse<String> previousResponse = null;

	// Set up logging format
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
	}
	// Set up a logger
    private static final Logger logger = Logger.getLogger(SimpleClient.class.getName());

	public void get(String uri) throws Exception {
	    HttpClient client = HttpClient.newHttpClient();
	    HttpRequest request = HttpRequest.newBuilder()
	          .uri(URI.create(uri))
	          .POST(BodyPublishers.ofString("{\"request\":\"Michael\"}\n"))
	          .build();

	    logger.info("Sending request "+request);

	    HttpResponse<String> response =
	          client.send(request, BodyHandlers.ofString());

	    // Response is expected in JSON
	    System.out.println(response.body());
	    previousResponse = response;
	}

	public static void main(String[] args) throws Exception {
		SimpleClient client = new SimpleClient();

		client.get(uriString);
	}

	public HttpResponse<String> getPreviousResponse() {
		return previousResponse;
	}
}
