package de.hdm.notizbuchsystem.client;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String logoutUrl;
	private String loginUrl;
	private String email;
	private String nickname;
	
	private boolean loggedIn = false;
	
	/**
	 * Getter, der die e-Mail Adresse des Nutzers zurueckliefert
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
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
	 * Getter, der den Nickname des Nutzers zurueckliefert
	 * 
	 * @return Nickname
	 */
	public String getNickname() {
		return nickname;
	}

	
	
	/**
	 * Legt die e-Mail Adresse des Nutzers fest
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * Legt einen nickname fuer den Nutzer fest
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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

