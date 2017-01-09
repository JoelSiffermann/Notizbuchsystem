package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
	
	private FlexTable NBuebersicht = new FlexTable();
	private Label pfadLabelNA = new Label("Zurueck zu: Meine Notizbuecher");
	private Button abbrechenButton = new Button("Abbrechen");
	  

	@Override
	protected void run() {

		buttonPanel.add(abbrechenButton);
		buttonPanel.add(pfadLabelNA);
		verPanel.add(NBuebersicht);
				
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		

		
		pfadLabelNA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcase = new VerwalteNotizbuch();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}

		});
		
		pfadLabelNA.addStyleName("notizbuchsystem-zurueckbutton");
		NBuebersicht.addStyleName("FlexTable");
		NBuebersicht.setCellPadding(6);
		NBuebersicht.getColumnFormatter().addStyleName(0,
				"TableHeader");
		
		NBuebersicht.setText(0, 1, "Titel");
		NBuebersicht.setText(0, 2, "Subtitel");
		NBuebersicht.setText(0, 3, "Anzahl der Notizen");
		NBuebersicht.setText(0, 4, "Anzeigen");
		
				
		abbrechenButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        
		    	  Showcase showcase = new VerwalteNotizbuch();
				     
		          RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(showcase);

		      }
		    });
		
	}
	
	
	public void NBauflisten(){
	//
	}
	

}

