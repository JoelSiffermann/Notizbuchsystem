package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class VerwalteNutzer extends Showcase {
	
	/**
	   * Jeder Showcase besitzt eine einleitende �berschrift, die durch diese
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
		 * Variable fuer die NutzerId erstellen.
		 */
		private int nutzerId;

		
		/**
		 * Konstruktor erstellen.
		 * 
		 * @param nutzerId
		 *            Die NutzerId-ID des Nutzers, das angezeigt werden soll.
		 
		**/
		// Konstruktor leer gemacht wegen Error
		//public VerwalteNutzer(final int nutzerId) {
		//	this.nutzerId = nutzerId; 
		//	 
		//	run(); 
		//}
	
		public VerwalteNutzer() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * Methode erstellen, die den Aufbau der Seite startet. 
		 */
		
		
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
			        Showcase showcase = new ErstelleNutzer();

			        /*
			         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
			         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
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
			        Showcase showcase = new ZeigeNutzer(nutzerId);

			        /*
			         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
			         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    

			
		}
	  
	


