package de.hdm.notizbuchsystem.client;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String logoutUrl;
	private String loginUrl;
	private String emailAddress;
	private String vorname;
	private String name;
	private boolean loggedIn = false;
	
	/**
	 * Getter, der die e-Mail Adresse des Nutzers zurueckliefert
	 * 
	 * @return email
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Getter, der die Google Logout-URL zurueckliefert
	 * 
	 * @return logout
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Getter, der den Vorname des Nutzers zurueckliefert
	 * 
	 * @return vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Getter, der den Nachname des Nutzer zurueckliefert
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Legt die e-Mail Adresse des Nutzers fest
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Legt die Google Logout-URL fest
	 * 
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Legt einen vorname fuer den Nutzer fest
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Legt einen Nachname fuer den Nutzer fest
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Methode gibt zurueck ob ein Nutzer eingeloggt ist.
	 * @return loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * 
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Getter fuer die LoginUrl
	 * @return loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}
	
	/**
	 * Setter fuer die LoginUrl
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
}

