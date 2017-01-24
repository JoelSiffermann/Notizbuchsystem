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
import com.google.gwt.user.client.ui.Widget;

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
		private Label emailLabel = new Label();
	
	    
	  final Button speichernButton = new Button("Nutzer Speichern");
	  final Button abbrechenButton = new Button("Abbrechen");
	  private Label reqLabel1 = new Label("* Pflichtfeld");
		private Label reqLabel2 = new Label("* Pflichtfeld");
		
		private Label warnLabel = new Label();
		
	  
		/**
		 * Variable fuer die Email erzeugen. 
		 */


		private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();


		/**
		 * Konstruktor erstellen.
		 * @param nutzerId Die Nutzer-ID des aktuellen Nutzers.  
		 * @param profiltyp Der Profiltyp (Nutzer). 
		 */

		public BearbeiteNutzer(String email) {

			this.email = email;
			run(); 
		}
		
	@Override
	protected void run() {
		
		  
		//Erstelle verPanel.
		  verPanel.add(nutzerFlexTable);
		  verPanel.add(vornameTextBox);
		  verPanel.add(nameTextBox);
		  verPanel.add(emailLabel);
		  

		  /**
			 * CSS anwenden und die Tabelle formatieren.
			 */
		  
		  
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
			nutzerFlexTable.setWidget(0, 1, vornameTextBox);
			nutzerFlexTable.setWidget(0, 2, reqLabel1);

			nutzerFlexTable.setWidget(1, 1, nameTextBox);
			nutzerFlexTable.setWidget(1, 2, reqLabel2);
			
			nutzerFlexTable.setWidget(2, 1, emailLabel);
			
			befuelleTabelle();
			

		buttonPanel.add(speichernButton);
		buttonPanel.add(abbrechenButton);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		
		/**
		 * ClickHandler fuer den Button zum Speichern des eigenen Nutzers erzeugen. 
		 * Sobald dieser Button betaetigt wird, werden die Eingaben sowohl auf 
		 * Vollstaendigkeit als auch auf Korrektheit ueberprueft. Sind Eingaben
		 * unvollstaendig oder inkorrekt, wird eine entsprechende Information 
		 * ueber diesen Zustand ausgegeben. Andernfalls wird der Nutzer 
		 * gespeichert. Anschliessend wird die Seite zum Anzeigen des eigenen 
		 * Nutzers aufgerufen.
		 */
		speichernButton.addClickHandler(new ClickHandler() {
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
				
				      
			          Showcase showcase = new ZeigeNutzer(email);
			     
			          RootPanel.get("Details").clear();
			          RootPanel.get("Details").add(showcase);
			        }
			      });
	}
			
			
	

			/**
			 * Methode erstellen, die den eigene Nutzer ausliest und die Profildaten in die 
			 * Tabelle eintraegt. 
			 */
			public void befuelleTabelle(){
				
				ClientsideSettings.getNotizSystemAdministration().getNutzerByEmail(email,
						new AsyncCallback<Nutzer>() {
					public void onFailure(Throwable caught) {
					}
					
					public void onSuccess(Nutzer result) {

						vornameTextBox.setText(result.getVorname());

						nameTextBox.setText(result.getName());
					
						emailLabel.setText(result.getEmail());
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
			warnLabel.setText("Bitte geben Sie Ihren Vornamen an.");
			nutzerFlexTable.setWidget(0, 3, warnLabel);
		} else if (nameTextBox.getText().length() == 0) {
			warnLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
			nutzerFlexTable.setWidget(1, 3, warnLabel);
		} else if (vornameWert == false) {
			warnLabel.setText("Ihr Vorname darf nur Buchstaben enthalten.");
			nutzerFlexTable.setWidget(0, 3, warnLabel);
		} else if (nameWert == false) {
			warnLabel.setText("Ihr Nachname darf nur Buchstaben enthalten.");
			nutzerFlexTable.setWidget(1, 3, warnLabel);
		
		} else {
			aktualisiereNutzer();
		}

	}


	/**
	 * Methode erstellen, die den eigene Nutzer aktualisiert. Dies f�hrt zum wiederholten 
	 * Schreiben des Nutzers in die Datenbank.
	 */
	public void aktualisiereNutzer() {
		ClientsideSettings.getNotizSystemAdministration().bearbeiteNutzer(
				email, nameTextBox.getText(), vornameTextBox.getText(),
				new AsyncCallback<Nutzer>() {
			

						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Nutzer nutzer) {
						Showcase showcase = new ZeigeNutzer(email);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showcase);
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

	

