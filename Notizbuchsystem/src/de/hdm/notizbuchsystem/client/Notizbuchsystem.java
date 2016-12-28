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
	private Label begruessenN2 = new Label(
			"Klicke dich nun durch dein eigenes Notizbuchsystem");

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

	/**
	 * Diese Klasse sichert die Implementierung des Interface EntryPoint. Daher
	 * benoetigen wir die Methode public void onModuleLoad(). Diese ist das
	 * GWT-Pendant der main()-Methode normaler Java-Applikationen.
	 */
	public void onModuleLoad() {
		setStyles();

		/**
		 * Login-Methode aufrufen und anschlie�end auf die Hostpage leiten.
		 */
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName,
				loginExecute());
		
		/*
	     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
	     * einige Schaltfl�chen (Buttons) f�r die Ausf�hrung von Unterprogrammen
	     * enthalten soll.
	     * 
	     * Die jeweils ausgef�hrten Unterprogramme sind Demonstratoren
	     * exemplarischer Anwendungsf�lle des Systems. Auf eine professionelle
	     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
	     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
	     * exemplarische GUI-Realisierung findet sich separat.
	     * 
	     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
	     * Grund existiert auch eine Basisklasse f�r s�mtliche Showcase-Klassen
	     * namens Showcase.
	     */

	    /*
	     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
	     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
	     */
			
	    VerticalPanel navPanel = new VerticalPanel();

	    /*
	     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
	     * zugeh�rigen HTML-Datei zugewiesen und erh�lt so seinen Darstellungsort.
	     */
	    RootPanel.get("Navigator").add(navPanel);

	    /*
	     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
	     */

	    /*
	     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
	     */
	    
	    final Button nutzerButton = new Button("Verwalte Nutzer");

	    /*
	     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     */
	    nutzerButton.setStylePrimaryName("test-menubutton");

	    /*
	     * Hinzuf�gen des Buttons zum VerticalPanel.
	     */
	    navPanel.add(nutzerButton);

	    /*
	     * Nat�rlich ben�tigt der Button auch ein Verhalten, wenn man mit der Maus
	     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugeh�rigen Button aufgerufen
	     * wird.
	     */
	    nutzerButton.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	        /*
	         * Showcase instantiieren.
	         */
	        Showcase showcase = new VerwalteNutzer();

	        /*
	         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
	         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
	         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
	         * Elemente dieses DIV.
	         */
	        RootPanel.get("Details").clear();
	        RootPanel.get("Details").add(showcase);
	      }
	    });
	    /*
	     * Ab hier folgen weitere Button-Definitionen, die nach exakt der gleichen
	     * Methode erfolgen wie beim ersten Button.
	     * 
	     * Da das Muster dazu sich mehrfach wiederholt, k�nnte man hier schon von
	     * einem unerw�nschte Code Clone sprechen. Um dies stilistisch zu optimieren
	     * w�re z.B. die Verwendung des Factory oder Builder Pattern denkbar. 
	     * Hierauf wurde jedoch bewusst verzichtet, um den Komplexit�tsgrad dieses
	     * Demonstrators nicht unn�tig zu erh�hen. 
	     */
	    
	    final Button notizbuchButton = new Button("Verwalte Notizbuch");

	    /*
	     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     */
	    notizbuchButton.setStylePrimaryName("test-menubutton");

	    /*
	     * Hinzuf�gen des Buttons zum VerticalPanel.
	     */
	    navPanel.add(notizbuchButton);

	    /*
	     * Nat�rlich ben�tigt der Button auch ein Verhalten, wenn man mit der Maus
	     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugeh�rigen Button aufgerufen
	     * wird.
	     */
	    notizbuchButton.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	        /*
	         * Showcase instantiieren.
	         */
	        Showcase showcase = new VerwalteNotizbuch();

	        /*
	         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
	         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
	         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
	         * Elemente dieses DIV.
	         */
	        RootPanel.get("Details").clear();
	        RootPanel.get("Details").add(showcase);
	      }
	    });

	    /*
	     * Ab hier folgen weitere Button-Definitionen, die nach exakt der gleichen
	     * Methode erfolgen wie beim ersten Button.
	     * 
	     * Da das Muster dazu sich mehrfach wiederholt, k�nnte man hier schon von
	     * einem unerw�nschte Code Clone sprechen. Um dies stilistisch zu optimieren
	     * w�re z.B. die Verwendung des Factory oder Builder Pattern denkbar. 
	     * Hierauf wurde jedoch bewusst verzichtet, um den Komplexit�tsgrad dieses
	     * Demonstrators nicht unn�tig zu erh�hen. 
	     */
	    final Button notizButton = new Button("Verwalte Notiz");

	    /*
	     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     */
	    notizButton.setStylePrimaryName("test-menubutton");

	    /*
	     * Hinzuf�gen des Buttons zum VerticalPanel.
	     */
	    navPanel.add(notizButton);

	    /*
	     * Nat�rlich ben�tigt der Button auch ein Verhalten, wenn man mit der Maus
	     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugeh�rigen Button aufgerufen
	     * wird.
	     */
	    notizButton.addClickHandler(new ClickHandler() {
	      @Override
		public void onClick(ClickEvent event) {
	        /*
	         * Showcase instantiieren.
	         */
	        Showcase showcase = new VerwalteNotiz();

	        /*
	         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
	         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
	         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
	         * Elemente dieses DIV.
	         */
	        RootPanel.get("Details").clear();
	        RootPanel.get("Details").add(showcase);
	      }
	    });

	    final Button logoutButton = new Button("Logout");

	    /*
	     * Unter welchem Namen k�nnen wir den Button durch die CSS-Datei des
	     * Projekts formatieren?
	     */
	    logoutButton.setStylePrimaryName("test-menubutton");

	    /*
	     * Hinzuf�gen des Buttons zum VerticalPanel.
	     */
	    navPanel.add(logoutButton);

	    /*
	     * Nat�rlich ben�tigt der Button auch ein Verhalten, wenn man mit der Maus
	     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
	     * onClick()-Methode beim Mausklick auf den zugeh�rigen Button aufgerufen
	     * wird.
	     */
	   // logoutButton.addClickHandler(new ClickHandler() {
	    //  @Override
		//public void onClick(ClickEvent event) {
	        /*
	         * Showcase instantiieren.
	         */
	     //   Showcase showcase = new ErstelleNotizbuch();

	        /*
	         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
	         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
	         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
	         * Elemente dieses DIV.
	         */
	      //  RootPanel.get("Details").clear();
	      //  RootPanel.get("Details").add(showcase);
	    //  }
	   // });

	}
	
	/**
	 * AsyncCallback f�r die Login-Methode. Bei erhalt der LoginInfos wir die Methode
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
	 * AsyncCallback f�r die Methode pruefeObNutzerNeu(). Falls der Wert false ist wird die Methode
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
					RootPanel.get("Details").add(begruessenN2);

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
	 * AsyncCallback f�r die Methode getNutzerByEmail(). Wenn ein Nutzer zurueckgeliefert wird,
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
		

		/**
		 * Methode erzeugt ruft das Panel auf, durch welches die Menubar sichtbar wird.
		 *
		public static void getMenu() {
			RootPanel.get("Navigator").add(new Navigator(np));
		}
		*/

   


  }
	private void setStyles() {
		begruessenN.setStyleName("welcome-label");
		begruessenN2.setStyleName("welcome-label2");
		}
	
	
}

