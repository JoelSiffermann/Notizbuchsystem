package de.hdm.notizbuchsystem.client;

import java.util.Vector;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notizbuch;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;

public class WeiseNotizNotizbuchZu extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Notiz zu Notizbuch zuweisen:";
	}

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	private VerticalPanel verPanel = new VerticalPanel();
	private Button auswahlbutton = new Button("Diesem Notizbuch zuweisen");
	private FlexTable NBuebersicht = new FlexTable();
	private int nbid;
	private int nid;

	
	
	public WeiseNotizNotizbuchZu(int nid){
//		this.nbid = nbid;
		this.nid = nid;
		run();
	}
	
	@Override
	protected void run() {
		verPanel.add(NBuebersicht);
		
		RootPanel.get("Details").add(NBuebersicht);
		
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
							
							final int nbid = n.getId();
							
							reihe++;
							
							NBuebersicht.setText(reihe, 0, n.getTitel());
							NBuebersicht.setText(reihe, 1, n.getEigentuemer());	
							NBuebersicht.setText(reihe, 2, n.getErstelldatum().toString());
							
							auswahlbutton = new Button("Diesem Notizbuch zuweisen"	);
							
							NBuebersicht.setWidget(reihe, 3, auswahlbutton);
							
							auswahlbutton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent event) {
								RootPanel.get("Details").clear();		
								ClientsideSettings.getNotizSystemAdministration().zuweisungNotiz
								(nbid, nid, email, new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(Void result) {
										Window.alert("Notiz erfolgreich zugewiesen");
										
									}

							
									
								});
								
								}});
							

							
							
						}}});}
	
}