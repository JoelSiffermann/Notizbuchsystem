package de.hdm.notizbuchsystem.server;

import de.hdm.notizbuchsystem.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
