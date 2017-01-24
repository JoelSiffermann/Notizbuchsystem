package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.notizbuchsystem.shared.bo.Notiz;

public class ZeigeAusgewaehlteNotiz extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}
	private int id;
	
	private DateTimeFormat erstelldatumFormat = DateTimeFormat
			.getFormat("dd.MM.yyyy");
	
	// Widgets für das Anzeigen von Notizen
		
		private VerticalPanel verPanel = new VerticalPanel();
		
		private FlexTable anzeigeFlexTable = new FlexTable();
		private TextBox titelAnzeige = new TextBox();
		private TextBox subtitelAnzeige = new TextBox();
		private TextBox inhaltAnzeige = new TextBox();
		
		private DateBox faelligkeitdatebox = new DateBox();
		private Label modidatumlabel = new Label();
		private Label erstelldatumlabel = new Label();
		
		private Button bearbeitebutton = new Button("Bearbeiten");
		

		
		public ZeigeAusgewaehlteNotiz(int ID) {
			this.id = ID;
			run();
		}
		

	@Override
	protected void run() {
		
		anzeigeFlexTable.setText(0, 0, "Titel");
		anzeigeFlexTable.setText(1, 0, "Subtitel");
		anzeigeFlexTable.setText(2, 0, "Inhalt");
		anzeigeFlexTable.setText(3, 0, "Erstelldatum");
		anzeigeFlexTable.setText(4, 0, "Modifikationsdatum");
		anzeigeFlexTable.setText(5, 0, "Faelligkeit");
		
		anzeigeFlexTable.setWidget(0, 1, titelAnzeige);
		anzeigeFlexTable.setWidget(1, 1, subtitelAnzeige);
		anzeigeFlexTable.setWidget(2, 1, inhaltAnzeige);
		anzeigeFlexTable.setWidget(3, 1, erstelldatumlabel);
		anzeigeFlexTable.setWidget(4, 1, modidatumlabel);
		anzeigeFlexTable.setWidget(5, 1, faelligkeitdatebox);
		
		verPanel.add(anzeigeFlexTable);
		verPanel.add(bearbeitebutton);
		
		befuelleTabelle();
		
		
		RootPanel.get("Details").add(verPanel);
		
		  bearbeitebutton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Showcase showcase = new BearbeiteNotiz(id);
					Showcase showcase2 = new ZeigeNotiz();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase2);
					RootPanel.get("Details").add(showcase);
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
				subtitelAnzeige.setText(result.getSubtitel());
				inhaltAnzeige.setText(result.getInhalt());
				erstelldatumlabel.setText(result.getErstelldatum().toString());
				modidatumlabel.setText(result.getModifikationsdatum().toString());
				faelligkeitdatebox.setFormat(new DateBox.DefaultFormat(erstelldatumFormat));
				faelligkeitdatebox.getDatePicker().setYearAndMonthDropdownVisible(true);
				
				titelAnzeige.setEnabled(false);
				subtitelAnzeige.setEnabled(false);
				inhaltAnzeige.setEnabled(false);
				
				
				
			}
		
			
		});
				
	
	
	
	
	
	
	
	
	}

}
