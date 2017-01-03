package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerwalteNotizbuch extends Showcase {

	@Override
	protected String getHeadlineText() {
		return "Notizbuch Verwalten:";
	}
	
	 private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button erstelleNotizbuchButton = new Button("Notizbuch Anlegen");
	  final Button alleNBButton = new Button("Meine Notizbuecher Anzeigen");
	 
	
	  
		
		@Override
		protected void run() {
			// TODO Auto-generated method stub
			
			buttonPanel.add(erstelleNotizbuchButton);
			buttonPanel.add(alleNBButton);
			
			
			RootPanel.get("Details").add(verPanel);
			RootPanel.get("Details").add(buttonPanel);
			
			erstelleNotizbuchButton.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ErstelleNotizbuch();

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
			
			alleNBButton.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ZeigeNotizbuch();

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

			
		}
	  
	

}
