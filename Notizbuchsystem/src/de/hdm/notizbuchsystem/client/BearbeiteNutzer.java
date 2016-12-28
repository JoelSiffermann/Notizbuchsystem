package de.hdm.notizbuchsystem.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.client.ZeigeNutzer;
import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;


public class BearbeiteNutzer extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Nutzer Bearbeiten:";
	}

	private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  private FlexTable nutzerFlexTable = new FlexTable();
		private TextBox vornameTextBox = new TextBox();
		private TextBox nameTextBox = new TextBox();
		private TextBox emailTextBox = new TextBox();
	
	    
	  final Button speichernButton = new Button("Nutzer Speichern");
	  final Button abbrechenButton = new Button("Abbrechen");
	  private Label reqLabel1 = new Label("* Pflichtfeld");
		private Label reqLabel2 = new Label("* Pflichtfeld");
		private Label reqLabel3 = new Label("* Pflichtfeld");
	  
		/**
		 * Variable fuer die Profil-ID erzeugen. 
		 */
		private int profilId; 
		
		/**
		 * Variable fuer den Profiltyp erzeugen. 
		 */
		private String profiltyp; 

		/**
		 * Konstruktor erstellen.
		 * @param profilId Die Profil-ID des aktuellen Nutzerprofils.  
		 * @param profiltyp Der Profiltyp (Nutzerprofil). 
		 */
		public BearbeiteNutzer(final int profilId, String profiltyp) {
			this.profilId = profilId; 
			this.profiltyp = profiltyp; 
			run(); 
		}
		
	@Override
	protected void run() {
		
		  
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
			
			

		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);

/**
 * ClickHandler fuer den Button zum Abbrechen des Anlegevorgangs eines Nutzers erzeugen.
 * Sobald dieser Button getaetigt wird, wird der Nutzer zurueck auf die Login-Seite geleitet.
 * Alle bisher im Formular eingetragenen Daten werden verworfen.
 */
	abbrechenButton.addClickHandler(new ClickHandler() {
	public void onClick(ClickEvent event) {
		
		Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl()); //WAS PASSIERT BEI ABBRUCH
	}
	
	});
	}
	
	
	/**
	 * Methode erstellen, die den eigenen Nutzer ausliest und die Profildaten in die 
	 * Tabelle eintraegt. 
	 */
	public void befuelleTabelle() {
		ClientsideSettings.getNotizSystemAdministration().getNutzerById(profilId,
				new AsyncCallback<Nutzer>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Nutzer result) {

						vornameTextBox.setText(result.getVorname());

						nameTextBox.setText(result.getName());

						emailTextBox.setText(result.getEmail());
					}
		});
	}
				



	/**
	 * Methode erstellen, die die Eingabe des Nutzers auf Vollstaendigkeit und
	 * Korrektheit ueberprueft.
	 */
	
  /**
   * ______________________notwendig?____________________________
   * public void pruefeEingabe() {
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
*/

	/**
	 * Methode erstellen, die den eigene Nutzer aktualisiert. Dies f�hrt zum wiederholten 
	 * Schreiben des Nutzers in die Datenbank.
	 */
	public void aktualisiereNutzerprofil() {
		ClientsideSettings.getNotizSystemAdministration().speichereNutzer(
				profilId, vornameTextBox.getText(),
				nameTextBox.getText(),emailTextBox.getText(),
				new AsyncCallback<Void>() {
			

						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Void result) {
						ZeigeNutzer showNutzer = new ZeigeNutzer(profilId, profiltyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showNutzer);
					}
			});
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

	

