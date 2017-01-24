package de.hdm.notizbuchsystem.client;



import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.apphosting.utils.config.AppYaml.AdminConsole;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.notizbuchsystem.server.NotizbuchAdministrationImpl;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministration;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

//Klasse zum Erstellen eines neuen Notizbuchs

public class ErstelleNotizbuch extends Showcase {

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	private NotizSystemAdministrationAsync admin = ClientsideSettings
			.getNotizSystemAdministration();
	protected String getHeadlineText() {
    return "Notizbuch Erstellen:";
  }
  
  private VerticalPanel verPanel = new VerticalPanel();
  private HorizontalPanel buttonPanel = new HorizontalPanel();
  
  
	private FlexTable notizbuchFlexTable = new FlexTable();
	private TextBox titelTextBox = new TextBox();
//	private TextBox subtitelTextBox = new TextBox(); Brauchen wir Subtitel??
	private Label ErstelldatumInhalt = new Label();

	private DateBox erstelldatumDatebox = new DateBox();
//	private DateBox modifikationsdatumDateBox = new DateBox();
	private DateTimeFormat erstelldatumFormat = DateTimeFormat
			.getFormat("dd.MM.yyyy");
	
	
	private Button erstelleNotizbuchButton = new Button("Notizbuch Anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");
//	private Label reqLabel2 = new Label("* Pflichtfeld");
//	private Label reqLabel3 = new Label("* Pflichtfeld");
	private Label warnLabel = new Label();
	private Label testLabel = new Label();
  
 
  protected void run() {
    // Ankündigung, was nun geschehen wird.
    this.append("Erstellen eines Notizbuches.");
    
  //verPanel wird aufgebaut
    verPanel.add(notizbuchFlexTable);
    verPanel.add(titelTextBox);
//    verPanel.add(subtitelTextBox);
    
    
    // Erstell- und Modifkationsdatumsboxen ausgeklammert, werden angepasst
   verPanel.add(erstelldatumDatebox);
 
   verPanel.add(testLabel);
//    verPanel.add(modifikationsdatumDateBox);
    
    reqLabel1.setStyleName("red_label");
//  	reqLabel2.setStyleName("red_label");
//  	reqLabel3.setStyleName("red_label");
  	warnLabel.setStyleName("red_label");
  	
  	notizbuchFlexTable.addStyleName("FlexTable");
  	notizbuchFlexTable.setCellPadding(6);
  	notizbuchFlexTable.getColumnFormatter().addStyleName(0,
  			"TableHeader");
  	
  	/**
	 * Erste Spalte der Tabelle festlegen.
	 */
	notizbuchFlexTable.setText(0, 0, "Titel");
	notizbuchFlexTable.setText(1, 0, "Erstelldatum");

	/**
	 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
	 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
	 * gesetzt.
	 */
	notizbuchFlexTable.setWidget(0, 2, titelTextBox);
	notizbuchFlexTable.setWidget(0, 3, reqLabel1);
//	notizbuchFlexTable.setWidget(1, 3, reqLabel2);
	
	buttonPanel.add(erstelleNotizbuchButton);
	buttonPanel.add(abbrechenButton);


  // button panel mit der HTML host page verbinden.
  //verPanel panel mit der HTML host page verbinden.
  RootPanel.get("Details").add(verPanel);
  RootPanel.get("Details").add(buttonPanel);
  

  erstelleNotizbuchButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			pruefeEingabe();
		}
	});
  
  abbrechenButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			
			      
		          Showcase showcase = new VerwalteNotizbuch();
		     
		          RootPanel.get("Details").clear();
		          RootPanel.get("Details").add(showcase);
		        }
		      });
  
  erstelldatumDatebox.setFormat(new DateBox.DefaultFormat(
		  erstelldatumFormat));
  erstelldatumDatebox.getDatePicker()
			.setYearAndMonthDropdownVisible(true);
  erstelldatumDatebox.getDatePicker().setVisibleYearCount(20);

  erstelldatumDatebox
			.addValueChangeHandler(new ValueChangeHandler<Date>() {
				public void onValueChange(ValueChangeEvent<Date> event) {
					Date geburtsdatum = event.getValue();
					String geburtsdatumString = DateTimeFormat.getFormat(
							"dd.MM.yyyy").format(geburtsdatum);
					ErstelldatumInhalt.setText(geburtsdatumString);

					if (event.getValue().after(aktuellesDatum())) {
						erstelldatumDatebox.setValue(aktuellesDatum(), false);
					}
				}
			});

	String todayString = DateTimeFormat.getFormat("dd.MM.yyyy").format(
			aktuellesDatum());
	ErstelldatumInhalt.setText(todayString);
	erstelldatumDatebox.setValue(new Date());
	notizbuchFlexTable.setWidget(1, 2, erstelldatumDatebox);
  
	erstelldatumDatebox.setValue(aktuellesDatum());
	erstelldatumDatebox.setEnabled(false);
	
  }
  
  // Methode zum Prüfen der Vollständigkeit der Eingabemaske für ein neues Notizbuch
  
  public void pruefeEingabe(){
	  
	  if (titelTextBox.getText().length() == 0) {
		  warnLabel.setText("Bitte geben Sie einen Titel an!"); 
		  notizbuchFlexTable.setWidget(0, 3, warnLabel);

	  }	else {
		  notizbuchAnlegen();
		  
	  }

 
  
  
	
  
}
  
 // Methode zum Anlegen eines neuen Notizbuchs und dessen Speicherung in der DB
 public void notizbuchAnlegen(){
admin.erstelleNotizbuch(titelTextBox.getText(), email, aktuellesDatum(), aktuellesDatum(), notizbuchAnlegenExecute());
}
  
 
 private AsyncCallback<Notizbuch> notizbuchAnlegenExecute()
 { AsyncCallback<Notizbuch> asynCallback = new AsyncCallback<Notizbuch>() {

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		warnLabel.setText("failed");
	}

	@Override
	public void onSuccess(Notizbuch result) {
		warnLabel.setText("Erfolgreich");
		
	}
};
	 return asynCallback;
 }
 
 // Methode zum Bestimmen der aktuellen DateTime
 

 
 private static Date aktuellesDatum() {
		return zeroTime(new Date()); 
     }
 
 private static Date zeroTime(final Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd").parse(
				DateTimeFormat.getFormat("yyyyMMdd").format(date));
	}
}
  
  

