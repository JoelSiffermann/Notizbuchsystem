package de.hdm.notizbuchsystem.client;

import java.sql.Date;
import java.util.Vector;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.notizbuchsystem.shared.bo.Notiz;

public class ZeigeNotiz extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Notizen";
	}

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel anzeigePanel = new VerticalPanel();
	
	
	
	private FlexTable Nuebersicht = new FlexTable();
	private Label pfadLabelNA = new Label("Zurueck zu: Verwalte Notizen");
	final private Button abbrechenButton = new Button("Abbrechen");
	private Button anzeigenbutton;
	private Button teilenbutton;
	private Button zuweisenbutton;
	
	
	@Override
	protected void run() {

		verPanel.add(Nuebersicht);
		verPanel.add(buttonPanel);		
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(anzeigePanel);
		
		
		
		pfadLabelNA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Showcase showcase = new VerwalteNotiz();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showcase);
			}

		});
		
		
		
		
		pfadLabelNA.addStyleName("notizbuchsystem-zurueckbutton");
		Nuebersicht.addStyleName("FlexTable");
		Nuebersicht.setCellPadding(6);
		Nuebersicht.getCellFormatter().addStyleName(0, 0, "TableHeader");
		Nuebersicht.getCellFormatter().setStyleName(0, 1, "TableHeader");
		Nuebersicht.getCellFormatter().setStyleName(0, 2, "TableHeader");
		Nuebersicht.getCellFormatter().setStyleName(0, 3, "TableHeader");
		Nuebersicht.getCellFormatter().setStyleName(0, 4, "Tableheader");
		
		Nuebersicht.setText(0, 0, "Titel");
		Nuebersicht.setText(0, 1, "Subtitel");
		Nuebersicht.setText(0, 2, "Eigentuemer");
		Nuebersicht.setText(0, 3, "Faelligkeitsdatum");
		Nuebersicht.setText(0, 4, "");
		Nuebersicht.setText(0, 5, "");
		
		
		
  
		
		abbrechenButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        
		    	  Showcase showcase = new VerwalteNotiz();
				     
		    	  RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(showcase); 
		          }
		    });
		

	
		befuelleNotizTabelle();
	}
	
	public void befuelleNotizTabelle(){
		ClientsideSettings.getNotizSystemAdministration().getNotizenByNutzer(email, 
				new AsyncCallback<Vector<Notiz>>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Notiz> result) {
						// TODO Auto-generated method stub
						
						int reihe = 0;
						
						for(Notiz n : result) {
							
							final int nid = n.getId();
							
							reihe++;
							
														
							Nuebersicht.setText(reihe, 0, n.getTitel());
							Nuebersicht.setText(reihe, 1, n.getSubtitel());
							Nuebersicht.setText(reihe, 2, n.getEigentuemer());
							Nuebersicht.setText(reihe, 3, n.getErstelldatum().toString());
							
							anzeigenbutton = new Button("Anzeigen");
							teilenbutton = new Button("Teilen");
							zuweisenbutton = new Button("Zuweisen");
							
							Nuebersicht.setWidget(reihe, 4, anzeigenbutton);
							Nuebersicht.setWidget(reihe, 5, teilenbutton);
							Nuebersicht.setWidget(reihe, 6, zuweisenbutton);
							
							anzeigenbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotiz();
								Showcase showcase = new ZeigeAusgewaehlteNotiz(nid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								
								}
							});
							
							
							teilenbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotiz();
								Showcase showcase = new Eintragungteilen(nid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								
								}
							});
							
							zuweisenbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotiz();
								Showcase showcase = new WeiseNotizNotizbuchZu(nid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								
								}
							});
							}}
					});}
	
					
	

}




