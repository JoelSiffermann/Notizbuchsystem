package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;


/**
 * In diesem Showcase wird ein neues Konto für den Kunden mit der Kundennummer 1
 * erstellt.
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

  /**
   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
   * eine "Einschubmethode", die von einer Methode der Basisklasse
   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
   */
  
  private VerticalPanel verPanel = new VerticalPanel();
  private HorizontalPanel buttonPanel = new HorizontalPanel();
  
  
	private FlexTable notizFlexTable = new FlexTable();
	private TextBox titelTextBox = new TextBox();
	private TextBox subtitelTextBox = new TextBox();
	private TextArea inhaltTextArea = new TextArea();
	private DateBox erstelldatumDateBox = new DateBox();
	private DateBox modifikationsdatumDateBox = new DateBox();


	private Button erstelleNotizButton = new Button("Notiz Anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");
	private Label reqLabel2 = new Label("* Pflichtfeld");
	private Label reqLabel3 = new Label("* Pflichtfeld");
	
  
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
  verPanel.add(erstelldatumDateBox);
  verPanel.add(modifikationsdatumDateBox);

  
  
  
    reqLabel1.setStyleName("red_label");
	reqLabel2.setStyleName("red_label");
	reqLabel3.setStyleName("red_label");
	
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
	notizFlexTable.setText(3, 0, "Erstelldatum");
	notizFlexTable.setText(4, 0, "Modifikationsdatum");

	/**
	 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
	 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
	 * gesetzt.
	 */
	notizFlexTable.setWidget(0, 2, titelTextBox);
	notizFlexTable.setWidget(0, 3, reqLabel1);

	notizFlexTable.setWidget(1, 2, subtitelTextBox);
	notizFlexTable.setWidget(1, 3, reqLabel2);
	
	notizFlexTable.setWidget(2, 2, inhaltTextArea);
	notizFlexTable.setWidget(2, 3, reqLabel3);
	
	notizFlexTable.setWidget(3, 2, erstelldatumDateBox);
	notizFlexTable.setWidget(3, 3, reqLabel3);
	
	notizFlexTable.setWidget(4, 2, modifikationsdatumDateBox);
	notizFlexTable.setWidget(4, 3, reqLabel3);

  // button panel erstellen.
  
  buttonPanel.add(erstelleNotizButton);
  buttonPanel.add(abbrechenButton);

  // button panel mit der HTML host page verbinden.
  //verPanel panel mit der HTML host page verbinden.
  RootPanel.get("Details").add(verPanel);
  RootPanel.get("Details").add(buttonPanel);
  


 
  }
  
  
  

  }

  

