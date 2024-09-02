/////////////////////////////
// Author: Michael Schwartz from Eclipse Javadoc
// SynchronousJsonServlet handles both GET and POST requests
//    on the configured resource.
// Logs request and returns an OK status.
/////////////////////////////
package edu.du.ict4310.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SynchronousJsonServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SynchronousJsonServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Processing GET request");

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("{ \"status\": \"ok\"}");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Processing POST request");

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("{ \"status\": \"ok\"}");
	}

}
