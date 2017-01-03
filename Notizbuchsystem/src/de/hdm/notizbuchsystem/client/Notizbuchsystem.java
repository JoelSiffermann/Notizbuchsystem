package de.hdm.notizbuchsystem.client;


import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.client.ErstelleNutzer;
import de.hdm.notizbuchsystem.client.LoginInfo;
import de.hdm.notizbuchsystem.shared.LoginServiceAsync;



/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */
public class Notizbuchsystem implements EntryPoint {
	

	/**
	 * Neues Nutzerprofil erzeugen
	 */
	Nutzer nutzerprofil = new Nutzer();

	
	/**
	 * Deklaration der Labels fuer die Startseite(n)
	 */
	private Label begruessenN = new Label(
			"Herzlich Willkommen bei Ihrem Notizbuchsystem. ");
	
	/**
	 * Deklaration fuer den Login und den Logout
	 */
	private static Nutzer np = null;
	private static LoginInfo loginInfo = null;
	
	private static String editorHtmlName = "Notizbuchsystem.html";

	private NotizSystemAdministrationAsync admin = ClientsideSettings
			.getNotizSystemAdministration();
	private LoginServiceAsync loginService = ClientsideSettings
			.getLoginService();

	
	public void onModuleLoad() {
		setStyles();

		/**
		 * Login-Methode aufrufen und anschließend auf die Hostpage leiten.
		 */
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName,
				loginExecute());
	}
		
	
	/**
	 * AsyncCallback für die Login-Methode. Bei erhalt der LoginInfos wird die Methode
	 * pruefeObMutzerNeu() aufgerufen.
	 */
	
	private AsyncCallback<LoginInfo> loginExecute() {
		AsyncCallback<LoginInfo> asynCallback = new AsyncCallback<LoginInfo>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(LoginInfo result) {

				if (result.isLoggedIn()) {
					loginInfo = result;
					admin.pruefeObNutzerNeu(result.getEmail(),
							pruefeObNutzerNeuExecute(result
									.getEmail()));


				} else {
					
					Window.Location.replace(result.getLoginUrl());
				}
			}
		};

		return asynCallback;
	}
	
	/**
	 * AsyncCallback für die Methode pruefeObNutzerNeu(). Falls der Wert false ist wird die Methode
	 * getNutzerByEmail() aufgerufen, sonst wird der Nutzer auf ErstelleNutzer() weitergeleitet.
	 * 
	 * @return
	 */
	private AsyncCallback<Boolean> pruefeObNutzerNeuExecute(String email) {
		AsyncCallback<Boolean> asynCallback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Boolean result) {

				if (!result) {
			
					admin.getNutzerByEmail(loginInfo.getEmail(),
							getNutzerByEmailExecute(loginInfo.getEmail()));
					
					RootPanel.get("Details").add(begruessenN);
					
				} else {
					
					ErstelleNutzer erstelleNutzer = new ErstelleNutzer("np");
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(erstelleNutzer);
					
				}

			}
		};

		return asynCallback;
	}

	/**
	 * Gibt den aktuell-eingeloggten Nutzer zurueck
	 * 
	 * @return Nutzer
	 */
	public static Nutzer getNp() {
		return np;
	}

	/**
	 * Gibt die LoginInfos des aktuell-eingeloggten Nutzers zurueck
	 * 
	 * @return loginInfo LoginInfo
	 */
	public static LoginInfo getLoginInfo() {
		return loginInfo;
	}

	
	/**
	 * AsyncCallback für die Methode getNutzerByEmail(). Wenn ein Nutzer zurueckgeliefert wird,
	 * wird die Methode getMenu() aufgerufen und der zurueckgelieferte Nutzer in die Variable np 
	 * gespeichert.
	 * @return
	 */
	private AsyncCallback<Nutzer> getNutzerByEmailExecute(
			String email) {
		AsyncCallback<Nutzer> asynCallback = new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Nutzer result) {
				
				np = result;
				

			}
		};
		return asynCallback;
		
	}
		
		//  Methode erzeugt ruft das Panel auf, das den Navigator erzeugt
	
		public void getMenu() {
			Navigator navigator = new Navigator();
			RootPanel.get("Navigator").add(navigator);
		}
		

  
	private void setStyles() {
		begruessenN.setStyleName("welcome-label");
		}
	
	
}

