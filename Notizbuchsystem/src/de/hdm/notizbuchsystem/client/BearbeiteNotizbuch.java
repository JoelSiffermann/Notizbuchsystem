package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BearbeiteNotizbuch extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Notizbuch Bearbeiten:";
	}

	private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  private Label pfadLabelNA = new Label("Zurueck zu: Meine Notizbuecher");
	  
	  final Button speichernButton = new Button("Notizbuch Speichern");
	  final Button abbrechenButton = new Button("Abbrechen");
	
	@Override
	protected void run() {
		
		pfadLabelNA.addStyleName("notizbuchsystem-zurueckbutton");
		
		pfadLabelNA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcase = new ZeigeNotizbuch();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}

		});
		verPanel.add(pfadLabelNA);
		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		
	
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
	}

}
