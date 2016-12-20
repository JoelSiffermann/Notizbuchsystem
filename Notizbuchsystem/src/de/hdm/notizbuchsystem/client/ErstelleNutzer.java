package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * In diesem Showcase wird ein neuer Nutzer 
 * erstellt.
 * 
 * @author teuta
 * @version 1.0
 * 
 */
public class ErstelleNutzer extends Showcase {

  /**
   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
   * Methode zu erstellen ist.
   * 
   * @see Showcase#getHeadlineText()
   */
  @Override
  protected String getHeadlineText() {
    return "Nutzer Erstellen:";
  }
  
  private VerticalPanel verPanel = new VerticalPanel();
  private HorizontalPanel buttonPanel = new HorizontalPanel();
  
  
	private FlexTable nutzerFlexTable = new FlexTable();
	private TextBox vornameTextBox = new TextBox();
	private TextBox nameTextBox = new TextBox();
	private TextBox emailTextBox = new TextBox();



	private Button erstelleNutzerButton = new Button("Nutzer Anlegen");
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
    this.append("Erstellen eines Nutzers.");
  
  
//Erstelle verPanel.
  verPanel.add(nutzerFlexTable);
  verPanel.add(vornameTextBox);
  verPanel.add(nameTextBox);
  verPanel.add(emailTextBox);
  

  
  
  
    reqLabel1.setStyleName("red_label");
	reqLabel2.setStyleName("red_label");
	reqLabel3.setStyleName("red_label");
	
	nutzerFlexTable.addStyleName("FlexTable");
	nutzerFlexTable.setCellPadding(6);
	nutzerFlexTable.getColumnFormatter().addStyleName(0,
			"TableHeader");
	
	/**
	 * Erste Spalte der Tabelle festlegen.
	 */
	nutzerFlexTable.setText(0, 0, "Vorname");
	nutzerFlexTable.setText(1, 0, "Name");
	nutzerFlexTable.setText(2, 0, "Email");


	/**
	 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
	 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
	 * gesetzt.
	 */
	nutzerFlexTable.setWidget(0, 2, vornameTextBox);
	nutzerFlexTable.setWidget(0, 3, reqLabel1);

	nutzerFlexTable.setWidget(1, 2, nameTextBox);
	nutzerFlexTable.setWidget(1, 3, reqLabel2);
	
	nutzerFlexTable.setWidget(2, 2, emailTextBox);
	nutzerFlexTable.setWidget(2, 3, reqLabel3);
	
	

// button panel erstellen.

buttonPanel.add(erstelleNutzerButton);
buttonPanel.add(abbrechenButton);

// button panel mit der HTML host page verbinden.
//verPanel panel mit der HTML host page verbinden.
RootPanel.get("Details").add(verPanel);
RootPanel.get("Details").add(buttonPanel);




}


  

  }