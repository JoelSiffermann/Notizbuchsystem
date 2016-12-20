package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
		        Showcase showcase = new BearbeiteNutzer();

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
