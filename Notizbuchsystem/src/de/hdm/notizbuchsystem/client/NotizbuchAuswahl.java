package de.hdm.notizbuchsystem.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

public class NotizbuchAuswahl extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Wählen Sie ein Notizbuch aus";
	}
	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	private VerticalPanel verPanel = new VerticalPanel();
	private FlexTable NBuebersicht = new FlexTable();
	
	private Button auswahlbutton = new Button("Diesem Notizbuch zuweisen");
	
	
	
	public NotizbuchAuswahl(String email){
		this.email = email;
		run();
	}
	
	
	@Override
	protected void run() {
		verPanel.add(NBuebersicht);
		
		NBuebersicht.getCellFormatter().addStyleName(0, 0, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 1, "TableHeader");
		NBuebersicht.getCellFormatter().addStyleName(0, 2, "TableHeader");
		
		NBuebersicht.setText(0, 0, "Titel");
		NBuebersicht.setText(0, 1, "Eigentuemer");
		NBuebersicht.setText(0, 2, "Erstelldatum");
		
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
							
							auswahlbutton = new Button("Anzeigen");
							
							NBuebersicht.setWidget(reihe, 3, auswahlbutton);
							
							auswahlbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								Showcase showcase2 = new ZeigeNotiz();
								Showcase showcase = new ZeigeausgewaehltesNB(eintragungid);
								RootPanel.get("Details").add(showcase2);
								RootPanel.get("Details").add(showcase);
								}});
							

							
							
						}}});}
	

}
