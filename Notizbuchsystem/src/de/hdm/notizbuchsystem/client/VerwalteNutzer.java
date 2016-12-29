package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class VerwalteNutzer extends Showcase {
	
	/**
	   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
	   * Methode zu erstellen ist.
	   * 
	   * @see Showcase#getHeadlineText()
	   */
	  @Override
	  protected String getHeadlineText() {
	    return "Nutzer Verwalten:";
	  }

	  private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button erstelleNutzerButton = new Button("Nutzer Anlegen");
	  final Button meinProfilAnzeigenButton = new Button("Mein Profil Anzeigen");
	  
	  /**
		 * Variable fuer die ProfilId erstellen.
		 */
		private int profilId;

		/**
		 * Variable fuer den Profiltyp erstellen.
		 */
		private String profiltyp;

		/**
		 * Konstruktor erstellen.
		 * 
		 * @param profilId
		 *            Die Profil-ID des Nutzerprofils, das angezeigt werden soll.
		 * @param profiltyp
		 *            Der Profiltyp (Nutzerprofil).
		 */
		public VerwalteNutzer(int profilId, String profiltyp) {
			this.profilId = profilId;
			this.profiltyp = profiltyp;
			run();
		}
	  
		
		@Override
		protected void run() {
			// TODO Auto-generated method stub
			
			buttonPanel.add(erstelleNutzerButton);
			buttonPanel.add(meinProfilAnzeigenButton);
			
			
			RootPanel.get("Details").add(verPanel);
			RootPanel.get("Details").add(buttonPanel);
			
			erstelleNutzerButton.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			    	  //Was kommt in den die Klammern bei erstelle Nutzer, beim aufruf des Konstruktors??
			        Showcase showcase = new ErstelleNutzer(null);

			        /*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    });}
			
			
		
			public void onClick(ClickEvent event) {
					/*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ZeigeNutzer(profilId,profiltyp);

			        /*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    

			
		}
	  
	


