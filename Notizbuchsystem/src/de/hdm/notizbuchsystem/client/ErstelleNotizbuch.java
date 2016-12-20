package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * In diesem Showcase wird ein neues Konto für den Kunden mit der Kundennummer 1
 * erstellt.
 * 
 * @author thies
 * @version 1.0
 * 
 */
public class ErstelleNotizbuch extends Showcase {

  /**
   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
   * Methode zu erstellen ist.
   * 
   * @see Showcase#getHeadlineText()
   */
  @Override
  protected String getHeadlineText() {
    return "Notizbuch Erstellen:";
  }
  
  private VerticalPanel verPanel = new VerticalPanel();
  private HorizontalPanel buttonPanel = new HorizontalPanel();
  
  
	private FlexTable notizbuchFlexTable = new FlexTable();
	private TextBox titelTextBox = new TextBox();
	private TextBox subtitelTextBox = new TextBox();
	private DateBox erstelldatumDateBox = new DateBox();
	private DateBox modifikationsdatumDateBox = new DateBox();


	private Button erstelleNotizbuchButton = new Button("Notizbuch Anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");
	private Label reqLabel2 = new Label("* Pflichtfeld");
	private Label reqLabel3 = new Label("* Pflichtfeld");
	
  

  /**
   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
   * eine "Einschubmethode", die von einer Methode der Basisklasse
   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
   */
  @Override
  protected void run() {
    // Ankündigung, was nun geschehen wird.
    this.append("Erstellen eines Notizbuches.");
    
  //Erstelle verPanel.
    verPanel.add(notizbuchFlexTable);
    verPanel.add(titelTextBox);
    verPanel.add(subtitelTextBox);
    verPanel.add(erstelldatumDateBox);
    verPanel.add(modifikationsdatumDateBox);
  
    
    
    
      reqLabel1.setStyleName("red_label");
  	reqLabel2.setStyleName("red_label");
  	reqLabel3.setStyleName("red_label");
  	
  	notizbuchFlexTable.addStyleName("FlexTable");
  	notizbuchFlexTable.setCellPadding(6);
  	notizbuchFlexTable.getColumnFormatter().addStyleName(0,
  			"TableHeader");
  	
  	/**
	 * Erste Spalte der Tabelle festlegen.
	 */
	notizbuchFlexTable.setText(0, 0, "Titel");
	notizbuchFlexTable.setText(1, 0, "Subtitel");
	notizbuchFlexTable.setText(2, 0, "Erstelldatum");
	notizbuchFlexTable.setText(3, 0, "Modifikationsdatum");

	/**
	 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
	 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
	 * gesetzt.
	 */
	notizbuchFlexTable.setWidget(0, 2, titelTextBox);
	notizbuchFlexTable.setWidget(0, 3, reqLabel1);

	notizbuchFlexTable.setWidget(1, 2, subtitelTextBox);
	notizbuchFlexTable.setWidget(1, 3, reqLabel2);
	
	notizbuchFlexTable.setWidget(2, 2, erstelldatumDateBox);
	notizbuchFlexTable.setWidget(2, 3, reqLabel3);
	
	notizbuchFlexTable.setWidget(3, 2, modifikationsdatumDateBox);
	notizbuchFlexTable.setWidget(3, 3, reqLabel3);

  // button panel erstellen.
  
  buttonPanel.add(erstelleNotizbuchButton);
  buttonPanel.add(abbrechenButton);

  // button panel mit der HTML host page verbinden.
  //verPanel panel mit der HTML host page verbinden.
  RootPanel.get("Details").add(verPanel);
  RootPanel.get("Details").add(buttonPanel);
  


 
  }
  
  
  
    
    
  }

  

  
  

