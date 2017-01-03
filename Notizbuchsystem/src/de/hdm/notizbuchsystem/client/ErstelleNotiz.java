package de.hdm.notizbuchsystem.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
//import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.notizbuchsystem.shared.bo.Nutzer;


/**
 * In diesem Showcase wird eine neue Notiz erstellt.
 * 
 * @author teuta
 * @version 1.0
 * 
 */
public class ErstelleNotiz extends Showcase {

  /**
   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
   * Methode zu erstellen ist.
   * 
   * @see Showcase#getHeadlineText()
   */
  @Override
  protected String getHeadlineText() {
    return "Notiz Erstellen:";
  }
  
  private VerticalPanel verPanel = new VerticalPanel();
  private HorizontalPanel buttonPanel = new HorizontalPanel();
  
  
	private FlexTable notizFlexTable = new FlexTable();
	private TextBox titelTextBox = new TextBox();
	private TextBox subtitelTextBox = new TextBox();
	private TextArea inhaltTextArea = new TextArea();
	private DateBox faelligkeitDateBox = new DateBox();
	@SuppressWarnings("deprecation")
	private Label erstelldatumDateBox = new Label(aktuellesDatum().toLocaleString());
	//private DateBox modifikationsdatumDateBox = new DateBox();


	private Button erstelleNotizButton = new Button("Notiz Anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");
	private Label reqLabel2 = new Label("* Pflichtfeld");
	private Label reqLabel3 = new Label("* Pflichtfeld");
	//private Label reqLabel4 = new Label("");
	private Label warnLabel = new Label("");
	
  
	 /**
	   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
	   * eine "Einschubmethode", die von einer Methode der Basisklasse
	   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
	   */
  protected void run() {   
	  
// Ankündigung, was nun geschehen wird.
    this.append("Erstellen einer Notiz.");
    
//Erstelle verPanel.
  verPanel.add(notizFlexTable);
  verPanel.add(titelTextBox);
  verPanel.add(subtitelTextBox);
  verPanel.add(inhaltTextArea);
  verPanel.add(faelligkeitDateBox);
  verPanel.add(erstelldatumDateBox);
  //verPanel.add(modifikationsdatumDateBox);

  
  
  
    reqLabel1.setStyleName("red_label");
	reqLabel2.setStyleName("red_label");
	reqLabel3.setStyleName("red_label");
	warnLabel.setStyleName("red_label");
	
	notizFlexTable.addStyleName("FlexTable");
	notizFlexTable.setCellPadding(6);
	notizFlexTable.getColumnFormatter().addStyleName(0,
			"TableHeader");
	/**
	 * Erste Spalte der Tabelle festlegen.
	 */
	notizFlexTable.setText(0, 0, "Titel");
	notizFlexTable.setText(1, 0, "Subtitel");
	notizFlexTable.setText(2, 0, "Inhalt");
	notizFlexTable.setText(3, 0, "Fälligkeit");
	notizFlexTable.setText(4, 0, "Erstelldatum");
	//notizFlexTable.setText(4, 0, "Modifikationsdatum");

	/**
	 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
	 * die Tabelle eingefuegt.
	 */
	notizFlexTable.setWidget(0, 2, titelTextBox);
	notizFlexTable.setWidget(0, 3, reqLabel1);

	notizFlexTable.setWidget(1, 2, subtitelTextBox);
	notizFlexTable.setWidget(1, 3, reqLabel2);
	
	notizFlexTable.setWidget(2, 2, inhaltTextArea);
	notizFlexTable.setWidget(2, 3, reqLabel3);
	
	notizFlexTable.setWidget(3, 2, faelligkeitDateBox);
	
	notizFlexTable.setWidget(4, 2, erstelldatumDateBox);
	//notizFlexTable.setWidget(3, 3, reqLabel4);
	
	//notizFlexTable.setWidget(4, 2, modifikationsdatumDateBox);
	//notizFlexTable.setWidget(4, 3, reqLabel3);

  // button panel erstellen.
  
  buttonPanel.add(erstelleNotizButton);
  buttonPanel.add(abbrechenButton);

  // button panel mit der HTML host page verbinden.
  //verPanel panel mit der HTML host page verbinden.
  RootPanel.get("Details").add(verPanel);
  RootPanel.get("Details").add(buttonPanel);
  
  erstelleNotizButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			pruefeEingabe();
		}
	});

abbrechenButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			buttonPanel.clear();
			verPanel.clear();
			clear();
			}
	});


}

// Methode zum Prüfen der Vollständigkeit der Eingabemaske für eine neue Notiz

public void pruefeEingabe(){
	  
	  if (titelTextBox.getText().length() == 0) {
		  warnLabel.setText("Bitte geben Sie einen Titel an!"); 
	  } else if(subtitelTextBox.getText().length() == 0) {
		  warnLabel .setText("Bitte geben Sie einen Subtitel an!");
	  } else if(inhaltTextArea.getText().length() == 0) {
		  warnLabel .setText("Bitte geben Sie einen Inhalt an!");
	  }	else {
		  notizAnlegen();
		  
	  }
	  
}

// Methode zum Anlegen einer neuen Notiz und dessen Speicherung in der DB
public void notizAnlegen(){
//	 ClientsideSettings.getNotizSystemAdministration().erstelleNotiz(String titel, String subtitel, String inhalt, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum), callback);
	  
}


// Methode zum Bestimmen der aktuellen DateTime



private static Date aktuellesDatum() {
		return zeroTime(new Date()); 
   }

private static Date zeroTime(final Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd hh:mm:ss aa").parse(
				DateTimeFormat.getFormat("yyyyMMdd hh:mm:ss aa").format(date));
	}
	

}



