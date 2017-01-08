package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ZeigeNutzer extends Showcase {

	/**
	   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
	   * Methode zu erstellen ist.
	   * 
	   * @see Showcase#getHeadlineText()
	   */
	  @Override
	  protected String getHeadlineText() {
	    return "Mein Profil:";
	  }
	  
	  private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button bearbeiteNutzerButton = new Button("Mein Profil Bearbeiten");
	  final Button meinProfilloeschenButton = new Button("Mein Profil Loeschen");

	  /**
		 * Variable fuer die NutzerId erstellen.
		 */
		private int nutzerId;

		
		/**
		 * Konstruktor erstellen.
		 * 
		 * @param nutzerId
		 *            Die Nutzer-ID des Nutzer, das angezeigt werden soll.
		 */
		public ZeigeNutzer(int nutzerId) {
			this.nutzerId = nutzerId;
			
			run();
		}
	@Override
	protected void run() {

		buttonPanel.add(bearbeiteNutzerButton);
		buttonPanel.add(meinProfilloeschenButton);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		
		bearbeiteNutzerButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
		        Showcase showcase = new BearbeiteNutzer(nutzerId);

		        /*
		         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
		         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
		         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
		         * Elemente dieses DIV.
		         */
		        RootPanel.get("Details").clear();
		        RootPanel.get("Details").add(showcase);
		      }
		    });

/**
		 * ClickHandler fuer den Button zum Loeschen des Nutzerprofils erzeugen.
		 * Sobald der Button betaetigt wird, erscheint eine Bildschrimmeldung,
		 * die hinterfragt, ob das Nutzerprofil tatsaechlich geloescht werden
		 * soll. Wird diese mit "Ok" bestaetigt, wird das Nutzerprofil aus der
		 * Datenbank entfernt. Zudem wird der Nutzer ausgeloggt und auf die
		 * Login-Seite weitergeleitet.
		 */
		meinProfilloeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loescheNutzer();
			}
		});}

		/**
		 * Methode erstellen, die das eigene Nutzerprofil loescht.
		 */
		public void loescheNutzer() {
			if (Window.confirm("Möchten Sie Ihr Profil wirklich löschen?")) {

				ClientsideSettings.getNotizSystemAdministration().loescheNutzer(nutzerId, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Void result) {

						Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl());

					}
				});
		
		

			}
	
		
	}}

