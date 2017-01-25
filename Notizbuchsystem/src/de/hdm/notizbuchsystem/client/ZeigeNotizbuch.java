package de.hdm.notizbuchsystem.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notiz;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

public class ZeigeNotizbuch extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Notizbuecher:";
		

	}

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	private FlexTable NBuebersicht = new FlexTable();
	private Label pfadLabelNA = new Label("Zurueck zu: Verwalte Notizbuecher");
	private Button abbrechenButton = new Button("Abbrechen");
	private Button anzeigenButton;
	private Button zeigeNotizenButton;
	private Button teilenbutton;
	  

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
		
		NBuebersicht.getCellFormatter().addStyleName(0, 0, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 1, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 2, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 3, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 4, "TableHeader");
		
		NBuebersicht.setText(0, 0, "Titel");
		NBuebersicht.setText(0, 1, "Eigentuemer");
		NBuebersicht.setText(0, 2, "Erstelldatum");
		NBuebersicht.setText(0, 3, "Anzeigen");
		NBuebersicht.setText(0, 4, "Notizen Anzeigen");
		
		
		
		
				
		abbrechenButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        
		    	  Showcase showcase = new VerwalteNotizbuch();
				     
		          RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(showcase);

		      }
		    });
		
		
		
		
		befuelleTabelle();
		
	}
	
	
	public void befuelleTabelle(){
		ClientsideSettings.getNotizSystemAdministration().getNotizbuecherByNutzer(email, 
				new AsyncCallback<Vector<Notizbuch>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Notizbuch> result) {
						// TODO Auto-generated method stub
						
						int reihe = 0;
						
						for(Notizbuch n : result) {
							
							final int eintragungid = n.getId();
							
							reihe++;
							
							NBuebersicht.setText(reihe, 0, n.getTitel());
							NBuebersicht.setText(reihe, 1, n.getEigentuemer());	
							NBuebersicht.setText(reihe, 2, n.getErstelldatum().toString());
							
							anzeigenButton = new Button("Anzeigen");
							zeigeNotizenButton = new Button("Notizen anzeigen");
							teilenbutton = new Button("Teilen");
							
							NBuebersicht.setWidget(reihe, 4, zeigeNotizenButton);
							NBuebersicht.setWidget(reihe, 3, anzeigenButton);
							NBuebersicht.setWidget(reihe, 6, teilenbutton);
							
							anzeigenButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotizbuch();
								Showcase showcase = new ZeigeausgewaehltesNB(eintragungid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								}});
							
							zeigeNotizenButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotizbuch();
								Showcase showcase = new ZeigeNotizenAusNotizbuch(eintragungid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								}});
							
							teilenbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotizbuch();
								Showcase showcase = new Eintragungteilen(eintragungid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								}});
							
							
						}}});}
	

}

