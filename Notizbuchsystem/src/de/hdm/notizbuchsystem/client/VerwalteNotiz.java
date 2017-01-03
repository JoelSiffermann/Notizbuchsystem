package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class VerwalteNotiz extends Showcase {

	@Override
	protected String getHeadlineText() {
		return "Notiz Verwalten:";
	}
	private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button erstelleNotizButton = new Button("Notiz Anlegen");
	  final Button meinNotizButton = new Button("Meine Notizen Anzeigen");
	  
	
	  
		
		@Override
		protected void run() {
			// TODO Auto-generated method stub
			
			buttonPanel.add(erstelleNotizButton);
			buttonPanel.add(meinNotizButton);
			
			
			RootPanel.get("Details").add(verPanel);
			RootPanel.get("Details").add(buttonPanel);
			
			erstelleNotizButton.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ErstelleNotiz();

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
			
			meinNotizButton.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ZeigeNotiz();

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

			
		}
	  
	

}
