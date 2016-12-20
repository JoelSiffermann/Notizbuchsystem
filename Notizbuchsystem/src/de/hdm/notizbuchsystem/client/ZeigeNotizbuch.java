package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ZeigeNotizbuch extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Notizbuecher:";
	}
	private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button bearbeiteNotizbuchButton = new Button("Notizbuch Bearbeiten");
	  final Button notizbuchloeschenButton = new Button("Notizbuch Loeschen");
	  final Button notizbuchnotizhinzufuegenButton = new Button("Notiz Hinzufuegen");

	@Override
	protected void run() {

		buttonPanel.add(bearbeiteNotizbuchButton);
		buttonPanel.add(notizbuchloeschenButton);
		buttonPanel.add(notizbuchnotizhinzufuegenButton);
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		
		bearbeiteNotizbuchButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
		        Showcase showcase = new BearbeiteNotizbuch();

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
		
		notizbuchnotizhinzufuegenButton.addClickHandler(new ClickHandler() {
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
		
	}

}

