package de.hdm.notizbuchsystem.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;


import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.client.Notizbuchsystem;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;

public class ErstelleNutzer extends VerticalPanel {

		/**
		 * Aktuelle EmailAdresse aus Google-Account holen
		 */
		private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();

		//Damit auf die Async Methoden zugegriffen werden können
		private NotizSystemAdministrationAsync admin = ClientsideSettings
				.getNotizSystemAdministration();
		

		/**
		 * Panels erzeugen.
		 */
		private VerticalPanel verPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();

		/**
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Profil anlegen:");
		private FlexTable createNutzerprofilFlexTable = new FlexTable();
		private TextBox vornameTextBox = new TextBox();
		private TextBox nachnameTextBox = new TextBox();

		private Button createNutzerprofilButton = new Button("Profil anlegen");
		private Button abbrechenButton = new Button("Abbrechen");
		
		private Label warnungLabel = new Label();

		/**
		 * Variable fuer den Profiltyp erstellen.
		 */
		private String profiltyp;

		/**
		 * Konstruktor erstellen.
		 * 
		 * @param profiltyp
		 *            Der Profiltyp (Nutzerprofil).
		 */
		public ErstelleNutzer(String profiltyp) {
			this.profiltyp = profiltyp;
			run();
		}

		/**
		 * Methode erstellen, die den Aufbau der Seite startet.
		 */
		public void run() {
			this.add(verPanel);

			/**
			 * CSS anwenden und Tabelle formatieren.
			 * noch das Label f�r �berschrift eingeben!!!!!!!!!!!!!!!!!!!!!!!!!!
			 */
			ueberschriftLabel.addStyleName("");
			warnungLabel.setStyleName("red_label");
			createNutzerprofilFlexTable.addStyleName("FlexTable");
			createNutzerprofilFlexTable.setCellPadding(6);
			createNutzerprofilFlexTable.getColumnFormatter().addStyleName(0,
					"TableHeader");

			/**
			 * Erste Spalte der Tabelle festlegen.
			 */
			createNutzerprofilFlexTable.setText(0, 0, "Vorname");
			createNutzerprofilFlexTable.setText(1, 0, "Nachname");
			createNutzerprofilFlexTable.setText(2, 0, "E-Mail");
			

			/**
			 * ZweiteSpalte der Tabelle festlegen. Die Widgets werden in
			 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
			 * gesetzt.
			 */
			createNutzerprofilFlexTable.setWidget(0, 2, vornameTextBox);
			

			createNutzerprofilFlexTable.setWidget(1, 2, nachnameTextBox);
			
			createNutzerprofilFlexTable.setText(2, 2, email);

			/**
			 * ClickHandler fuer den Button zum Anlegen eines Nutzerprofils
			 * erzeugen. Sobald dieser Button betaetigt wird, werden die Eingaben
			 * sowohl auf Vollstaendigkeit als auch auf Korrektheit geprueft. Sind
			 * die Eingaben unvollstaendig oder inkorrekt, wird eine entsprechende
			 * Fehlermeldung ueber diesen Zustand ausgegeben. Andernfalls wird das
			 * Nutzerprofil angelegt. Anschliessend wird die Seite zum Anlegen der
			 * Infos aufgerufen.
			 */
			createNutzerprofilButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					pruefeEingabe();
				}
			});

			/**
			 * Widgets dem Panel hinzufuegen.
			 */
			buttonPanel.add(createNutzerprofilButton);
			buttonPanel.add(abbrechenButton);
			
			verPanel.add(ueberschriftLabel);
			verPanel.add(createNutzerprofilFlexTable);
			verPanel.add(buttonPanel);
			
			
			/**
			 * ClickHandler fuer den Button zum Abbrechen des Anlegevorgangs eines Nutzerprofils erzeugen.
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
			boolean nachnameWert = isBuchstabe(nachnameTextBox.getText());
			
			
			if (vornameTextBox.getText().length() == 0) {
				warnungLabel.setText("Bitte geben Sie Ihren Vornamen an.");
				createNutzerprofilFlexTable.setWidget(0, 4, warnungLabel);
			} else if (nachnameTextBox.getText().length() == 0) {
				warnungLabel.setText("Bitte geben Sie Ihren Nachnamen an.");
				createNutzerprofilFlexTable.setWidget(1, 4, warnungLabel);
			} else if (vornameWert == false) {
				warnungLabel.setText("Ihr Vorname darf nur Buchstaben enthalten.");
				createNutzerprofilFlexTable.setWidget(0, 4, warnungLabel);
			} else if (nachnameWert == false) {
				warnungLabel.setText("Ihr Nachname darf nur Buchstaben enthalten.");
				createNutzerprofilFlexTable.setWidget(1, 4, warnungLabel);
			} else {
				nutzerprofilAnlegen();
			}
		}

		/**
		 * Methode erstellen, die ein neues Nutzerprofil anlegt. Dies fürt zum
		 * Speichern des Nutzerprofils in der Datenbank.
		 */
		public void nutzerprofilAnlegen() {
			admin.erstelleNutzer(
					vornameTextBox.getText(),
					nachnameTextBox.getText(),
					email,
					erstelleNutzerExecute(vornameTextBox.getText(),
							nachnameTextBox.getText(),
							email));

		}

		private AsyncCallback<Nutzer> erstelleNutzerExecute(
				String vorname, String nachname,  String emailAddress) {
			AsyncCallback<Nutzer> asynCallback = new AsyncCallback<Nutzer>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(Nutzer result) {
					
				//NOCH UNSICHER WEGEN CREATE INFO!!!!!
					CreateInfo createInfo = new CreateInfo(result.getProfilId(),
							profiltyp);
					RootPanel.get("Details").clear();
					
					RootPanel.get("Details").add(createInfo); 
					
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

		
	}
