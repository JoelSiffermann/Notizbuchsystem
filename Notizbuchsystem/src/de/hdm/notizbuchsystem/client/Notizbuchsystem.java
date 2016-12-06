package de.hdm.notizbuchsystem.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.client.LoginInfo;
import de.hdm.notizbuchsystem.shared.LoginServiceAsync;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.shared.ReportGeneratorAsync;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
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
			"Herzlich Willkommen bei Ihrem Notizbuch. ");
	private Label begruessenN2 = new Label(
			"Verwalten Sie nun Ihr Notizbuch");

		
	/**
	 * Deklaration fuer den Login und den Logout
	 */
	private static Nutzer np = null;
	private static LoginInfo loginInfo = null;
	
	private static String editorHtmlName = "Partnerboerse.html";

	private NotizSystemAdministrationAsync admin = ClientsideSettings.
			getNotizSystemAdministration();
	private LoginServiceAsync loginService = ClientsideSettings
			.getLoginService();
	

	/**
	 * Diese Klasse sichert die Implementierung des Interface EntryPoint. Daher
	 * benoetigen wir die Methode public void onModuleLoad(). Diese ist das
	 * GWT-Pendant der main()-Methode normaler Java-Applikationen.
	 */
	public void onModuleLoad() {
		setStyles();

		/**
		 * Login-Methode aufrufen und anschließend auf die Hostpage leiten.
		 */
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName,
				loginExecute());

	}
	
	/**
	 * AsyncCallback für die Login-Mathode. Bei erhalt der LoginInfos wir die Methode
	 * pruefeObMutzerNeu() aufgerufen.
	 * 
	 * @return
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
					admin.pruefeObNutzerNeu(result.getEmailAddress(),
							pruefeObNutzerNeuExecute(result
									.getEmailAddress()));


				} else {
					
					Window.Location.replace(result.getLoginUrl());
				}
			}
		};
		return asynCallback;
	}
	
	/**
	 * AsyncCallback für die Methode pruefeObMutzerNeu(). Falls der Wert false ist wird die Methode
	 * getNutzerByEmail() aufgerufen, sonst wird der Nutzer auf CreateNutzerprofil() weitergeleitet.
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
			
					admin.getNuterprofilByEmail(loginInfo.getEmailAddress(),
							getNutzerprofilByEmailExecute(loginInfo.getEmailAddress()));
					
					RootPanel.get("Details").add(begruessenN);
					RootPanel.get("Details").add(begruessenN2);

				} else {
					
					CreateNutzer createNutzerprofil = new CreateNutzer("Np");
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(createNutzerprofil);
					
				}

			}
		};
		return asynCallback;
	}

	/**
	 * Gibt das aktuell-eingeloggte Nutzerprofil zurueck
	 * 
	 * @return Nutzerprofil
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
	 * AsyncCallback für die Methode getNuterprofilByEmail(). Wenn ein Nutzerprofil zurückgeliefert wird,
	 * wird die Methode getMenu() aurgerufen und das zurückgelieferte Nutzerprofil in die Variable np 
	 * gespeichert.
	 * @return
	 */
	private AsyncCallback<Nutzer> getNuterprofilByEmailExecute(
			String email) {
		AsyncCallback<Nutzer> asynCallback = new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Nutzer result) {
				
				np = result;
			getMenu();

			}
		};
		return asynCallback;
	}

	/**
	 * Methode legt die CSS-Styles für verschiedene Labels fest.
	 */
	private void setStyles() {
		begruessenN.setStyleName("welcome-label");
		begruessenN2.setStyleName("welcome-label2");

	}

	/**
	 * Methode erzeugt ruft das Panel auf, durch welches die Menubar sichtbar wird.
	 */
	public static void getMenu() {
		RootPanel.get("Navigator").add(new Navigator(np));
	}

}

	
	
	
	
