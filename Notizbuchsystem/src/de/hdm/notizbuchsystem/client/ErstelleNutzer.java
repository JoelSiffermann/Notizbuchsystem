package de.hdm.notizbuchsystem.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.client.Notizbuchsystem;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;

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
	 * Aktuelle EmailAdresse aus Google-Account holen
	 */
	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();

	//Damit auf die Async Methoden zugegriffen werden können
	private NotizSystemAdministrationAsync admin = ClientsideSettings
			.getNotizSystemAdministration();
	
  /**
   * Jeder Showcase besitzt eine einleitende �berschrift, die durch diese
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
	



	private Button erstelleNutzerButton = new Button("Nutzer Anlegen");
	private Button abbrechenButton = new Button("Abbrechen");
	private Label reqLabel1 = new Label("* Pflichtfeld");
	private Label reqLabel2 = new Label("* Pflichtfeld");
	private Label warnungLabel = new Label();
	
  

 
	/**
	 * Variable fuer den NutzerId erstellen.
	 */
	//private int nutzerId;

	private String profiltyp;

	/**
	 * Konstruktor erstellen.
	 * 
	 * @param nutzer
	 *            Der Nutzer.
	 */
	public ErstelleNutzer(String profiltyp) {
		this.profiltyp = profiltyp;
		run();
	}
	
 







/**
   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
   * eine "Einschubmethode", die von einer Methode der Basisklasse
   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
   */
  @Override
  protected void run() {
    // Ank�ndigung, was nun geschehen wird.
    this.append("Erstellen eines Nutzers.");
  
  
//Erstelle verPanel.
  verPanel.add(nutzerFlexTable);
  verPanel.add(vornameTextBox);
  verPanel.add(nameTextBox);
 
  

  
  
  
    reqLabel1.setStyleName("red_label");
	reqLabel2.setStyleName("red_label");
	
	
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
	 * die Tabelle eingefuegt 
	 */
	nutzerFlexTable.setWidget(0, 2, vornameTextBox);
	nutzerFlexTable.setWidget(0, 3, reqLabel1);

	nutzerFlexTable.setWidget(1, 2, nameTextBox);
	nutzerFlexTable.setWidget(1, 3, reqLabel2);
	
	nutzerFlexTable.setText(2, 2, email);
	
	
	

// button panel erstellen.

buttonPanel.add(erstelleNutzerButton);
buttonPanel.add(abbrechenButton);

// button panel mit der HTML host page verbinden.
//verPanel panel mit der HTML host page verbinden.
RootPanel.get("Details").add(verPanel);
RootPanel.get("Details").add(buttonPanel);


/**
 * ClickHandler fuer den Button zum Anlegen eines Nutzers
 * erzeugen. Sobald dieser Button betaetigt wird, werden die Eingaben
 * sowohl auf Vollstaendigkeit als auch auf Korrektheit geprueft. Sind
 * die Eingaben unvollstaendig oder inkorrekt, wird eine entsprechende
 * Fehlermeldung ueber diesen Zustand ausgegeben. Andernfalls wird der
 * Nutzer angelegt. 
 */
erstelleNutzerButton.addClickHandler(new ClickHandler() {
	public void onClick(ClickEvent event) {
		pruefeEingabe();
	}
});


/**
 * ClickHandler fuer den Button zum Abbrechen des Anlegevorgangs eines Nutzers erzeugen.
 * Sobald dieser Button getaetigt wird, wird der Nutzer zurueck auf die Login-Seite geleitet.
 * Alle bisher im Formular eingetragenen Daten werden verworfen.
 */
	abbrechenButton.addClickHandler(new ClickHandler() {
	public void onClick(ClickEvent event) {
		
		Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl());
	}
	
	});
	}
	

	/**
	 * Methode erstellen, die die Eingabe des Nutzers auf Vollstaendigkeit und
	 * Korrektheit ueberprueft.
	 */
	
  
   public void pruefeEingabe() {
		boolean vornameWert = isBuchstabe(vornameTextBox.getText());
		boolean nameWert = isBuchstabe(nameTextBox.getText());
		

		if (vornameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Vornamen an.");
			nutzerFlexTable.setWidget(0, 4, warnungLabel);
		} else if (nameTextBox.getText().length() == 0) {
			warnungLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
			nutzerFlexTable.setWidget(1, 4, warnungLabel);
		} else if (vornameWert == false) {
			warnungLabel.setText("Ihr Vorname darf nur Buchstaben enthalten.");
			nutzerFlexTable.setWidget(0, 4, warnungLabel);
		} else if (nameWert == false) {
			warnungLabel.setText("Ihr Nachname darf nur Buchstaben enthalten.");
			nutzerFlexTable.setWidget(1, 4, warnungLabel);
		
		} else {
			nutzerAnlegen();
		}

	}
	/**
	 * Methode erstellen, die einen neuen Nutzer anlegt. Dies f�hrt zum
	 * Speichern des Nutzers in der Datenbank.
	 */
	public void nutzerAnlegen() {
		admin.erstelleNutzer(
				vornameTextBox.getText(),
				nameTextBox.getText(),
				email,
				erstelleNutzerExecute(vornameTextBox.getText(),
						nameTextBox.getText(),
						email));
	}

	private AsyncCallback<Nutzer> erstelleNutzerExecute(
			String vorname, String name, String emailAddress) {
		AsyncCallback<Nutzer> asynCallback = new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Nutzer result) {
				Showcase showcase = new ZeigeNutzer(result.getNutzerId(), result.getEmailAddress());
				RootPanel.get("Details").clear();
			
				RootPanel.get("Details").add(showcase); 
		
			}
		};
		return asynCallback;
	}











	/**
	 * Methode erstellen, die ueberprueft, ob nur Buchstaben eingegeben wurden.
	 * 
	 * @param name
	 *            Der String, der ueberprueft wird.
	 * @return Boolscher Wert, der angibt, ob es sich um Buchstaben handelt.
	 */
	public boolean isBuchstabe(String name) {
		return name.matches("^[a-zA-ZäöüÄÖÜß ]+$");
	}

	/**
	 * Methode erstellen, die ueberprueft, ob nur Zahlen eingegeben wurden.
	 * 
	 * @param name
	 *            Der String, der ueberprueft wird.
	 * @return Boolscher Wert, der angibt, ob es sich um Zahlen handelt.
	 */
	public boolean isZahl(String name) {
		return name.matches("[0-9]+");
	}}

	
	
	
	
	
	
	
	
	
	