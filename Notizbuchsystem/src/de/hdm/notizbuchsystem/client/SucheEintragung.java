package de.hdm.notizbuchsystem.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

public class SucheEintragung extends Showcase{

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Suche nach Notizen und Notizbuechern per Titel";
	}
	
	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	private VerticalPanel verPanel = new VerticalPanel();
	private TextBox suchetextbox = new TextBox();
	private Button nbsuchenbutton = new Button("Nach Notizbuechern suchen");
	private Button notizsuchenbutton = new Button("Nach Notizen suchen");
	private FlexTable NBuebersicht = new FlexTable();
	private FlexTable notizergebnis = new FlexTable();
	
	
	
	
	
	
	@Override
	protected void run() {
		verPanel.add(suchetextbox);
		verPanel.add(nbsuchenbutton);
		verPanel.add(notizsuchenbutton);
		
		RootPanel.get("Details").add(verPanel);
		
		nbsuchenbutton.addClickHandler(new ClickHandler() {
		      
			public void onClick(ClickEvent event) {
		        
				befuellenbtabelle();
		          }
		    });
		
		notizsuchenbutton.addClickHandler(new ClickHandler() {
		      
			public void onClick(ClickEvent event) {
		        
				befuellenotiztabelle();
		          }
		    });
		
	}
	
	public void befuellenbtabelle(){
		ClientsideSettings.getNotizSystemAdministration().getNotizbuchByTitel(suchetextbox.getText(),
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
					
					final int nbid = n.getId();
					
					reihe++;
					
					NBuebersicht.setText(reihe, 0, n.getTitel());
					NBuebersicht.setText(reihe, 1, n.getEigentuemer());	
					NBuebersicht.setText(reihe, 2, n.getErstelldatum().toString());
					
					Button anzeigenButton = new Button("Anzeigen");
					Button zeigeNotizenButton = new Button("Notizen anzeigen");
					Button teilenbutton = new Button("Teilen");
					Button loeschenbutton = new Button("Notizbuch loeschen");
					
					
					NBuebersicht.setWidget(reihe, 4, zeigeNotizenButton);
					NBuebersicht.setWidget(reihe, 3, anzeigenButton);
					NBuebersicht.setWidget(reihe, 6, teilenbutton);
					NBuebersicht.setWidget(reihe, 7, loeschenbutton);
					
					anzeigenButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
						RootPanel.get("Details").clear();		
						Showcase showcase2 = new ZeigeNotizbuch();
						Showcase showcase = new ZeigeausgewaehltesNB(nbid);
						RootPanel.get("Details").add(showcase2);
						RootPanel.get("Details").add(showcase);
						}});
					
					zeigeNotizenButton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
						RootPanel.get("Details").clear();		
						Showcase showcase2 = new ZeigeNotizbuch();
						Showcase showcase = new ZeigeNotizenAusNotizbuch(nbid);
						RootPanel.get("Details").add(showcase2);
						RootPanel.get("Details").add(showcase);
						}});
					
					teilenbutton.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
						RootPanel.get("Details").clear();		
						Showcase showcase2 = new ZeigeNotizbuch();
						Showcase showcase = new Eintragungteilen(nbid);
						RootPanel.get("Details").add(showcase2);
						RootPanel.get("Details").add(showcase);
						}});
					
					loeschenbutton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								ClientsideSettings.getNotizSystemAdministration()
								.loescheNotizbuch(nbid, email,
										new AsyncCallback<Void>() {

									public void onFailure(Throwable caught) {
									}

									public void onSuccess(Void result) {
										Window.alert("Notizbuch erfolgreich geloescht");
	
									}
								});	
							
											

						}});
					
					
					
				}}});}

	private void befuellenotiztabelle(){

	}
	}
	
	
	
	
