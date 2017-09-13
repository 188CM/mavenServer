package com.gscdn.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.gscdn.page.EntryPoint;

public class mainServer {

	private static int PORT = 8080;

	public static void main(String[] args) throws Exception {
	
		Ignite ignite = Ignition.start();
				
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(PORT);
		server.addConnector(connector);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
					
		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",EntryPoint.class.getCanonicalName());


		try {
			server.start();
			server.join();
		} catch (Exception ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			server.destroy();
			ignite.close();
		}

	}
}
