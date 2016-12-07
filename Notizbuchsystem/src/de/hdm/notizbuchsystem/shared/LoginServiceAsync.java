package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.notizbuchsystem.client.LoginInfo;

/**
 * Asynchrone Schnittstelle fuer den Login.
 */
public interface LoginServiceAsync {
	
	/**
	 * @see de.hdm.notizbuchsystem.server.LoginServiceImpl#login(String)
	 */	
	public void login(String requestUri, AsyncCallback<LoginInfo> callback);

}
