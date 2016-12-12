package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.notizbuchsystem.client.ClientsideSettings;

import de.hdm.notizbuchsystem.shared.bo.Nutzer;

/**
 * diese Klasse dient dazu, ein Profil zu verwalten
 * @author Yannick
 *
 */
public class VerwalteNutzer extends VerticalPanel {

		/**
		 * Panels erzeugen.
		 */
		private VerticalPanel verPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();

		/**
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Profil bearbeiten:");
		private FlexTable editNutzerFlexTable = new FlexTable();
		private TextBox vornameTextBox = new TextBox();
		private TextBox nachnameTextBox = new TextBox();
		
		private Label emailLabel = new Label();
		private Button editNutzerButton = new Button("Profil speichern");
		private Button abbrechenButton = new Button("Abbrechen");
		
		private Label infoLabel = new Label();
		private Label warnungLabel = new Label();
		
		private Label pfadLabelNpA = new Label("Zurueck zu: Startseite");
		
		
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
		 * @param profilId Die Profil-ID des aktuellen Nutzers.  
		 * @param profiltyp Der Profiltyp (Nutzer). 
		 */
		public VerwalteNutzer(final int profilId, String profiltyp) {
			this.profilId = profilId; 
			this.profiltyp = profiltyp; 
			run(); 
		}
		
		/**
		 * Methode erstellen, die den Aufbau der Seite startet. 
		 */
		public void run() {
			this.add(verPanel);

			/**
			 * CSS anwenden und die Tabelle formatieren.
			 */
			ueberschriftLabel.addStyleName("Notizbuchsystem-label");
			editNutzerFlexTable.addStyleName("FlexTable");
			editNutzerFlexTable.setCellPadding(6);
			editNutzerFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");
			pfadLabelNpA.addStyleName("Notizbuchsystem-zurueckbutton");
			
			/**
			 * Erste Spalte der Tabelle festlegen.
			 */
			editNutzerFlexTable.setText(0, 0, "Vorname");
			editNutzerFlexTable.setText(1, 0, "Nachname");
			editNutzerFlexTable.setText(2, 0, "E-Mail");

			/**
			 * Zweite und Dritte Spalte der Tabelle festlegen.
			 * Die Widgets werden in die Tabelle eingefuegt und die Items fuer die ListBoxen werden gesetzt. 
			 */
			editNutzerFlexTable.setWidget(0, 1, vornameTextBox);
			editNutzerFlexTable.setWidget(1, 1, nachnameTextBox);
			editNutzerFlexTable.setWidget(2, 1, emailLabel);

			befuelleTabelle(); 
			
			/**
			 * ClickHandler fuer den Button zum Speichern des eigenen Nutzers erzeugen. 
			 * Sobald dieser Button betaetigt wird, werden die Eingaben sowohl auf 
			 * Vollstaendigkeit als auch auf Korrektheit ueberprueft. Sind Eingaben
			 * unvollstaendig oder inkorrekt, wird eine entsprechende Information 
			 * ueber diesen Zustand ausgegeben. Andernfalls wird das Nutzerprofil 
			 * gespeichert. Anschliessend wird die Seite zum Anzeigen des eigenen 
			 * Nutzers aufgerufen.
			 */
			editNutzerButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					pruefeEingabe(); 
				}
			}); 

			/**
			 * ClickHandler fuer den Button zum Abbrechen des Bearbeitens eines Nutzerprofils erzeugen. 
			 * Sobald dieser Button betaetigt wird, wird die Seite zum Anzeigen des eigenen Nutzerprofils
			 * aufgerufen.
			 */
			abbrechenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					ZeigeNutzer zeigeNutzer = new ZeigeNutzer(profilId, profiltyp); 
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(zeigeNutzer);
				}
			}); 
			
			/**
			 * ClickHandler fuer das Label zum Zurueckkehren zum Nutzerprofil erzeugen. 
			 * Sobald dieses Label betaetigt wird, wird die Seite zum Anzeigen des eigenen 
			 * Nutzerprofils aufgerufen.
			 */
			pfadLabelNpA.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					ZeigeNutzer showNp = new ZeigeNutzer(profilId, profiltyp);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showNp);
				}

			});

			/**
			 * Widgets den Panels hinzufuegen.
			 */
			verPanel.add(ueberschriftLabel);
			verPanel.add(editNutzerFlexTable);
			buttonPanel.add(editNutzerButton);
			buttonPanel.add(abbrechenButton);
			verPanel.add(buttonPanel);
			verPanel.add(infoLabel);
		}
		
		/**
		 * Methode erstellen, die das eigene Nutzerprofil ausliest und die Profildaten in die 
		 * Tabelle eintraegt. 
		 */
		public void befuelleTabelle() {
			ClientsideSettings.getNotizSystemAdministration().getNutzerById(profilId,
					new AsyncCallback<Nutzer>() {

						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Nutzer result) {

							vornameTextBox.setText(result.getVorname());

							nachnameTextBox.setText(result.getName());

							emailLabel.setText(result.getEmail());
						}
					});

			/**
			 * Widgets dem Panel hinzufuegen.
			 */
			verPanel.add(pfadLabelNpA);
			verPanel.add(ueberschriftLabel);
			verPanel.add(editNutzerFlexTable);
			verPanel.add(editNutzerButton);
			verPanel.add(infoLabel);
		}
		
		/**
		 * Methode erstellen, die die Eingaben auf Vollstaendigkeit und Korrektheit ueberprueft.
		 */
		public void pruefeEingabe() {
			boolean vornameWert = isBuchstabe(vornameTextBox.getText());
			boolean nachnameWert = isBuchstabe(nachnameTextBox.getText());
			

			if (vornameTextBox.getText().length() == 0) {
				warnungLabel.setText("Bitte geben Sie Ihren Vornamen an.");
				editNutzerFlexTable.setWidget(0, 3, warnungLabel);
			} else if (nachnameTextBox.getText().length() == 0) {
				warnungLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
				editNutzerFlexTable.setWidget(1, 3, warnungLabel);
			} else if (vornameWert == false) {
				warnungLabel.setText("Ihr Vorname darf keine Zahlen enthalten.");
				editNutzerFlexTable.setWidget(0, 3, warnungLabel);
			} else if (nachnameWert == false) {
				warnungLabel.setText("Ihr Nachname darf keine Zahlen enthalten.");
				editNutzerFlexTable.setWidget(1, 3, warnungLabel);
			} else {
				aktualisiereNutzer(); 
			}
		}
		
		/**
		 * Methode erstellen, die das eigene Nutzers aktualisiert. Dies f�hrt zum wiederholten 
		 * Schreiben des Nutzers in die Datenbank.
		 */
		public void aktualisiereNutzer() {
			ClientsideSettings.getNotizSystemAdministration().speicherNutzer(
					profilId, vornameTextBox.getText(),
					nachnameTextBox.getText(),
					new AsyncCallback<Void>() {
				

							public void onFailure(Throwable caught) {
							}

							public void onSuccess(Void result) {
							ZeigeNutzer zeigeNutzer = new ZeigeNutzer(profilId, profiltyp);
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(zeigeNutzer);
						}
				});
		}
		
		

		/**
		 * Methode erstellen, die ueberprueft, ob nur Buchstaben eingegeben wurden.
		 * @param name Der String, der ueberprueft wird. 
		 * @return Boolscher Wert, der angibt, ob es sich um Buchstaben handelt.
		 */
		public boolean isBuchstabe(String name) {
			return name.matches("^[a-zA-ZäöüÄÖÜß ]+$");
		}

		/**
		 * Methode erstellen, die ueberprueft, ob nur Zahlen eingegeben wurden. 
		 * @param name Der String, der ueberprueft wird.  
		 * @return Boolscher Wert, der angibt, ob es sich um Zahlen handelt. 
		 */
		public boolean isZahl(String name) {
			return name.matches("[0-9]+");
		}
		
	}

