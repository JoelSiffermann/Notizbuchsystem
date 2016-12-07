package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.notizbuchsystem.client.LoginInfo;

/**
 * Synchrone Schnittstelle fuer den Login.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	
	/**
	 * @see de.hdm.notizbuchsystem.server.LoginServiceImpl#login(String)
	 */
	public LoginInfo login(String requestUri) throws IllegalArgumentException;
	
}
