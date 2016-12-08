package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/*
 * Dieses Interface beschreibt die asynchrone Schnittstelle fuer das Login.
 */

public interface LoginServiceAsync {
	
	void login(String requestUri, AsyncCallback<LoginInfo> callback);
	
}
