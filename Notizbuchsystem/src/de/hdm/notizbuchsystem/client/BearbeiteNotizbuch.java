package de.hdm.notizbuchsystem.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.notizbuchsystem.shared.bo.Notiz;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

public class BearbeiteNotizbuch extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}
	private int id;
	
	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	private DateTimeFormat erstelldatumFormat = DateTimeFormat
			.getFormat("dd.MM.yyyy");
	
	// Widgets für das Anzeigen von Notizen
		
		private VerticalPanel verPanel = new VerticalPanel();
		
		private FlexTable anzeigeFlexTable = new FlexTable();
		private TextBox titelAnzeige = new TextBox();

		private DateBox modidatebox = new DateBox();
		private DateBox erstelldatumdatebox = new DateBox();
		
		private Button speichernButton = new Button("Speichern");
		

		
		public BearbeiteNotizbuch(int ID) {
			this.id = ID;
			run();
		}
		

	@Override
	protected void run() {
		
		anzeigeFlexTable.setText(0, 0, "Titel");
		anzeigeFlexTable.setText(1, 0, "Erstelldatum");
		anzeigeFlexTable.setText(2, 0, "Modifikationsdatum");
	
		
		anzeigeFlexTable.setWidget(0, 1, titelAnzeige);
		anzeigeFlexTable.setWidget(1, 1, erstelldatumdatebox);
		anzeigeFlexTable.setWidget(2, 1, modidatebox);
		
		erstelldatumdatebox.setEnabled(false);
		modidatebox.setEnabled(false);
		
		verPanel.add(anzeigeFlexTable);
		verPanel.add(speichernButton);
		
		befuelleTabelle();
		
		
		RootPanel.get("Details").add(verPanel);
		
		speichernButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        ClientsideSettings.getNotizSystemAdministration().bearbeiteNotizbuch(id, email, titelAnzeige.getText(),
		        		aktuellesDatum(), new AsyncCallback<Notizbuch>()
		        		{

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Notizbuch result) {
								titelAnzeige.setText(result.getTitel());
								erstelldatumdatebox.setValue(result.getErstelldatum());
								modidatebox.setValue(result.getModifikationsdatum());

							}}
		        		
		        		);
		    	  	
		          }
		    });
		
		
		
		}
	
	private void befuelleTabelle() {
		ClientsideSettings.getNotizSystemAdministration().getNotizbyID(id, new AsyncCallback<Notiz>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Notiz result) {
				
				titelAnzeige.setText(result.getTitel());
				erstelldatumdatebox.setValue(result.getErstelldatum());
				modidatebox.setValue(result.getModifikationsdatum());

			}
		
			
		});
	
	
	}
	
	
	private static Date aktuellesDatum() {
		return zeroTime(new Date()); 
     }
 
 private static Date zeroTime(final Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd").parse(
				DateTimeFormat.getFormat("yyyyMMdd").format(date));
	}

}

