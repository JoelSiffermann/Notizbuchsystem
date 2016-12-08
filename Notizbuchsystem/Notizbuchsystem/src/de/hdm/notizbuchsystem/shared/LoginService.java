package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginservice")

/*
 * Dieses Interface beschreibt die synchrone Schnittstelle fuer das Login.
 */

public interface LoginService extends RemoteService{

	public LoginInfo login(String requestUri) throws IllegalArgumentException;
	

}
