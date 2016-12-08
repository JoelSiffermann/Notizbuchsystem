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


import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.bo.*;



/**
 * Diese Klasse dient dazu, ein Notizbuch anzulegen.
 */

public class ErstelleNotizbuch extends VerticalPanel{
	
	/**
	 * Aktuelle EmailAdresse aus Google-Account holen
	 */
	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	//Damit auf die Async Methoden zugegriffen werden kÃ¶nnen
		
	private NotizSystemAdministrationAsync admin = ClientsideSettings
				.getNotizSystemAdministration();

		/**
		 * Panels erzeugen.
		 */
		private VerticalPanel verticalPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();

		/**
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Notizbuch anlegen:");
		private FlexTable erstelleNotizbuchFlexTable = new FlexTable();
		
		private TextBox titelTextBox = new TextBox();
		private DateBox erstelldatumDateBox = new DateBox();
		private DateBox modidatumDateBox = new DateBox();
		//private TextBox inhaltTextBox = new TextBox();
		private TextBox eigentuemerTextBox = new TextBox();
		//private TextBox nutzerTextBox = new TextBox();

		
		private Button erstelleNotizbuchButton = new Button("Notizbuch anlegen");
		private Button abbrechenButton = new Button("Abbrechen");
		private Label reqLabel1 = new Label("* Pflichtfeld");
		private Label reqLabel2 = new Label("* Pflichtfeld");
		private Label reqLabel3 = new Label("* Pflichtfeld");
		//private Label reqLabel4 = new Label("* Pflichtfeld");
		private Label reqLabel5 = new Label("* Pflichtfeld");
		//private Label reqLabel6 = new Label("* Pflichtfeld");
		private Label warnungLabel = new Label();
		/**
		 * Variable fuer den Profiltyp erstellen.
		 */
		private String profiltyp;

		/**
		 * Konstruktor erstellen.
		 * 
		 * @param profiltyp
		 *            Der Profiltyp (Notizbuch).
		 */
		public ErstelleNotizbuch(String profiltyp) {
			this.profiltyp = profiltyp;
			run();
		}

		/**
		 * Methode erstellen, die den Aufbau der Seite startet.
		 */
		public void run() {
			this.add(verticalPanel);

			/**
			 * CSS anwenden und Tabelle formatieren.
			 */
			ueberschriftLabel.addStyleName("partnerboerse-label");
			reqLabel1.setStyleName("red_label");
			reqLabel2.setStyleName("red_label");
			reqLabel3.setStyleName("red_label");
			//reqLabel4.setStyleName("red_label");
			reqLabel5.setStyleName("red_label");
			//reqLabel6.setStyleName("red_label");
			warnungLabel.setStyleName("red_label");
			erstelleNotizbuchFlexTable.addStyleName("FlexTable");
			erstelleNotizbuchFlexTable.setCellPadding(6);
			erstelleNotizbuchFlexTable.getColumnFormatter().addStyleName(0,
					"TableHeader");

			/**
			 * Erste Spalte der Tabelle festlegen.
			 */
			erstelleNotizbuchFlexTable.setText(0, 0, "Titel");
			erstelleNotizbuchFlexTable.setText(1, 0, "Erstelldatum");
			erstelleNotizbuchFlexTable.setText(2, 0, "Modifikationsdatum");
			//erstelleNotizbuchFlexTable.setText(3, 0, "Inhalt");
			erstelleNotizbuchFlexTable.setText(4, 0, "Eigentümer");
			//erstelleNotizbuchFlexTable.setText(5, 0, "Nutzer");
			
	

			/**
			 * Zweite und dritte Spalte der Tabelle festlegen. Die Widgets werden in
			 * die Tabelle eingefuegt und die Items fuer die ListBoxen werden
			 * gesetzt.
			 */
			erstelleNotizbuchFlexTable.setWidget(0, 2, titelTextBox);
			erstelleNotizbuchFlexTable.setWidget(0, 3, reqLabel1);

			erstelleNotizbuchFlexTable.setWidget(1, 2, erstelldatumDateBox);
			erstelleNotizbuchFlexTable.setWidget(1, 3, reqLabel2);
			
			erstelleNotizbuchFlexTable.setWidget(2, 2, modidatumDateBox);
			erstelleNotizbuchFlexTable.setWidget(2, 3, reqLabel2);
			
			//erstelleNotizbuchFlexTable.setWidget(3, 2, inhaltTextBox);
			//erstelleNotizbuchFlexTable.setWidget(3, 3, reqLabel1);
			
			erstelleNotizbuchFlexTable.setWidget(4, 2, eigentuemerTextBox);
			erstelleNotizbuchFlexTable.setWidget(4, 3, reqLabel1);
			
			//erstelleNotizbuchFlexTable.setWidget(5, 2, nutzerTextBox);
			//erstelleNotizbuchFlexTable.setWidget(5, 3, reqLabel1);

			
// Automatisches Erstelldatum und Modidatum muss noch programmiert werden
			
			
	

			/**
			 * ClickHandler fuer den Button zum Anlegen eines Notizbuches
			 * erzeugen. Sobald dieser Button betaetigt wird, werden die Eingaben
			 * sowohl auf Vollstaendigkeit als auch auf Korrektheit geprueft. Sind
			 * die Eingaben unvollstaendig oder inkorrekt, wird eine entsprechende
			 * Fehlermeldung ueber diesen Zustand ausgegeben. Andernfalls wird das
			 * Notizbuch angelegt. Anschliessend wird die Seite zum Anlegen einer
			 * Notiz aufgerufen.
			 */
			erstelleNotizbuchButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					pruefeEingabe();
				}
			});

			/**
			 * Widgets dem Panel hinzufuegen.
			 */
			buttonPanel.add(erstelleNotizbuchButton);
			buttonPanel.add(abbrechenButton);
			
			verticalPanel.add(ueberschriftLabel);
			verticalPanel.add(erstelleNotizbuchFlexTable);
			verticalPanel.add(buttonPanel);
			
			
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
			boolean titelWert = isBuchstabe(titelTextBox.getText());
			boolean inhaltWert = isBuchstabe(inhaltTextBox.getText());
			boolean eigentuemerWert = isBuchstabe(eigentuemerTextBox.getText());
			//boolean nutzerWert = isBuchstabe(nutzerTextBox.getText());

			if (titelTextBox.getText().length() == 0) {
				warnungLabel.setText("Bitte geben Sie einen Titel an.");
				erstelleNotizbuchFlexTable.setWidget(0, 4, warnungLabel);
			} else 
				
				
			if (erstelldatumDateBox.getValue() == null) {
				warnungLabel.setText("Bitte geben Sie das Erstellungs-Datum an.");
				erstelleNotizbuchFlexTable.setWidget(1, 4, warnungLabel);
			} else 
				
			if (modidatumDateBox.getValue() == null) {
				warnungLabel.setText("Bitte geben Sie das Modifikations-Datum an.");
				erstelleNotizbuchFlexTable.setWidget(2, 4, warnungLabel);
			} else 


//Statt einen Inhalt in das Notizbuch einzufügen, ein Button Notiz hinzufügen anlegen und dem Notizbuch beifügen.
//			if (inhaltTextBox.getText().length() == 0) {
//				warnungLabel.setText("Bitte geben Sie einen Inhalt an.");
//				erstelleNotizbuchFlexTable.setWidget(3, 4, warnungLabel);
//			} else 
					
			if (eigentuemerTextBox.getText().length() == 0) {
					warnungLabel.setText("Bitte geben Sie einen Eigentümer an.");
					erstelleNotizbuchFlexTable.setWidget(4, 4, warnungLabel);
			} else
			 
				 {
				notizbuchAnlegen();
			}
		}

		/**
		 * Methode erstellen, die ein neues Notizbuch anlegt. Dies fÃ¼rt zum
		 * Speichern des Notizbuches in der Datenbank.
		 */
		public void notizbuchAnlegen() {
			admin.erstelleNotizbuch(
					titelTextBox.getText(),
					getErstelldatum(),
					getModifikationsdatum(),
				//inhaltTextBox.getText(),
					eigentuemerTextBox.getText(),
				//	nutzerTextBox.getText(),
					
					erstelleNotizbuchExecute(
							titelTextBox.getText(),
							getErstelldatum(),
							getModifikationsdatum(),
							inhaltTextBox.getText(),
							eigentuemerTextBox.getText()));
							//nutzerTextBox.getText()));

		}

		private AsyncCallback<Notizbuch> erstelleNotizbuchExecute(
				String titel, Date erstelldatumDate,
				Date modifikationsdatumDate, String inhalt, String eigentuemer) {
			AsyncCallback<Notizbuch> asynCallback = new AsyncCallback<Notizbuch>() {
				@Override
				public void onFailure(Throwable caught) {

				}
				@Override
				public void onSuccess(Notizbuch result) {
					
				
					ErstelleNotiz createInfo = new ErstelleNotiz(result.getNotizId(),
							profiltyp);
					RootPanel.get("Details").clear();
					
					RootPanel.get("Details").add(createInfo); 
					
				}
			};
			return asynCallback;
		};}