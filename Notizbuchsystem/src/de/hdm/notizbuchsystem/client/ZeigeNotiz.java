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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notiz;

public class ZeigeNotiz extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Meine Notizen";
	}

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	
	private FlexTable Nuebersicht = new FlexTable();
	private Label pfadLabelNA = new Label("Zurueck zu: Verwalte Notizen");
	final private Button abbrechenButton = new Button("Abbrechen");
	final Button notizNotizbuchZuweisenButton = new Button("Notizbuch Zuweisen");
	private Button anzeigenbutton;
	@Override
	protected void run() {

		verPanel.add(Nuebersicht);
		buttonPanel.add(abbrechenButton);
		buttonPanel.add(notizNotizbuchZuweisenButton);
		buttonPanel.add(pfadLabelNA);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		
		

		
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
		Nuebersicht.getColumnFormatter().addStyleName(0,
				"TableHeader");
		
		Nuebersicht.setText(0, 0, "Titel");
		Nuebersicht.setText(0, 1, "Subtitel");
		Nuebersicht.setText(0, 2, "Eigentuemer");
		Nuebersicht.setText(0, 3, "Faelligkeitsdatum");
		Nuebersicht.setText(0, 4, "Anzeigen");
		Nuebersicht.getRowFormatter().addStyleName(0, "TabelHeader");
		
	//	bearbeiteNotizButton.addClickHandler(new ClickHandler() {
	//	      @Override
	//		public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
	//	        Showcase showcase = new BearbeiteNotiz();

		        /*
		         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
		         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
		         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
		         * Elemente dieses DIV.
		         */
	//	        RootPanel.get("Details").clear();
	//	        RootPanel.get("Details").add(showcase);
	//	      }
	//	    });
		
		abbrechenButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        
		    	  Showcase showcase = new VerwalteNotiz();
				     
		          RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(showcase);

		          
		          
		      }
		    });
		
	
		
		notizNotizbuchZuweisenButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
		        Showcase showcase = new WeiseNotizNotizbuchZu();

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
							
							final int eintragungid = n.getId();
							
							reihe++;
							
							Nuebersicht.setText(reihe, 0, n.getTitel());
							Nuebersicht.setText(reihe, 1, n.getSubtitel());
							Nuebersicht.setText(reihe, 2, n.getEigentuemer());
							Nuebersicht.setText(reihe, 3, n.getErstelldatum().toString());
							
							anzeigenbutton = new Button("Anzeigen");
							
							Nuebersicht.setWidget(reihe, 4, anzeigenbutton);
							

						}
						
					}
			
		});
	}

}




