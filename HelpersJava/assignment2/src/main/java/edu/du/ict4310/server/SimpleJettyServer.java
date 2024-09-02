/////////////////////////////
// Author: Michael Schwartz from Eclipse Javadoc
// SimpleJettyServer
// Configures a Jetty server on the configured port,
// sets up ONE path (/check)
// Server can be started and stopped through public methods (for testability)
/////////////////////////////
package edu.du.ict4310.server;

import java.io.IOException;

import java.util.logging.Logger;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class SimpleJettyServer {

	// Set up logging format
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
	}
	// Set up a logger
    private static final Logger logger = Logger.getLogger(SimpleJettyServer.class.getName());

	private static int defaultPORT = 8090;
	private final int port;

	private Server server;

	public SimpleJettyServer() throws IOException {
		this(defaultPORT);
	}

	public SimpleJettyServer(int port) throws IOException {
		this.port = port;
		logger.info("Created a Jetty server");
	}

	public void start() throws Exception {
		server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		server.setConnectors(new Connector[] { connector });

		ServletHandler servletHandler = new ServletHandler();
		server.setHandler(servletHandler);
		servletHandler.addServletWithMapping(SynchronousJsonServlet.class, "/check");
		logger.info("Started Jetty server on port "+port);

		server.start();
	}

	public void stop() throws Exception {
		server.stop();
		logger.info("Stopped Jetty server");
	}

	public static void main(String[] args) throws Exception {
		SimpleJettyServer server = new SimpleJettyServer();
		server.start();
	}
}
