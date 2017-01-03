package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BearbeiteNotiz extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Notiz Bearbeiten:";
	}

	private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  
	  final Button speichernButton = new Button("Notiz Speichern");
	  final Button abbrechenButton = new Button("Abbrechen");
	
	@Override
	protected void run() {

		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
	}

}
