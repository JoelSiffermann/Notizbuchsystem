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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.client.VerwalteNutzer;
import de.hdm.notizbuchsystem.client.Notizbuchsystem;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;

/**
 * Diese Klasse dient dazu, das Nutzerprofil anzuzeigen
 * @author Yannick
 *
 */
public class ZeigeNutzer extends VerticalPanel {

		/**
		 * Panels erzeugen.
		 */
		private VerticalPanel nutzerprofilPanel = new VerticalPanel();
		private VerticalPanel infoPanel = new VerticalPanel();
		private HorizontalPanel horPanel = new HorizontalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();

		/**
		 * Widgets erzeugen.
		 */
		private Label ueberschriftLabel = new Label("Mein Profil:");
		private FlexTable showEigenesNpFlexTable = new FlexTable();
		private Label infoLabel = new Label();
		private Button loeschenButton = new Button("Profil loeschen");
		private Button bearbeitenButton = new Button("Profil bearbeiten");

		/**
		 * Variable fuer die ProfilId erstellen.
		 */
		private int profilId;

		/**
		 * Variable fuer den Profiltyp erstellen.
		 */
		private String profiltyp;

		/**
		 * Konstruktor erstellen.
		 * 
		 * @param profilId
		 *            Die Profil-ID des Nutzers, das angezeigt werden soll.
		 * @param profiltyp
		 *            Der Profiltyp (Nutzer).
		 */
		public ZeigeNutzer(int profilId, String profiltyp) {
			this.profilId = profilId;
			this.profiltyp = profiltyp;
			run();
		}

		/**
		 * Methode erstellen, die den Aufbau der Seite startet.
		 */
		public void run() {
			this.add(horPanel);
			horPanel.add(nutzerprofilPanel);
			horPanel.add(infoPanel);

			/**
			 * CSS anwenden und die Tabelle formatieren.
			 */
			ueberschriftLabel.addStyleName("Notizbuchsystem-label");
			showEigenesNpFlexTable.addStyleName("FlexTable");
			showEigenesNpFlexTable.setCellPadding(6);
			showEigenesNpFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");

			/**
			 * Erste Spalte der Tabelle festlegen.
			 */
			showEigenesNpFlexTable.setText(0, 0, "Vorname");
			showEigenesNpFlexTable.setText(1, 0, "Nachname");
			showEigenesNpFlexTable.setText(2, 0, "EMail");

			befuelleTabelle();

			/**
			 * ClickHandler fuer den Button zum Bearbeiten des Nutzers
			 * erzeugen. Sobald der Button betaetigt wird, wird die Seite zum
			 * Bearbeiten des Nutzers aufgerufen.
			 */
			bearbeitenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					VerwalteNutzer editNutzerprofil = new VerwalteNutzer(profilId, profiltyp);
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(editNutzerprofil);
				}
			});

			/**
			 * ClickHandler fuer den Button zum Loeschen des Nutzers erzeugen.
			 * Sobald der Button betaetigt wird, erscheint eine Bildschirmmeldung,
			 * die hinterfragt, ob das Nutzerprofil tatsaechlich geloescht werden
			 * soll. Wird diese mit "Ok" bestaetigt, wird das Nutzerprofil aus der
			 * Datenbank entfernt. Zudem wird der Nutzer ausgeloggt und auf die
			 * Login-Seite weitergeleitet.
			 */
			loeschenButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					loescheNutzer();
				}
			});


			/**
			 * Widgets den Panels hinzufuegen.
			 */
			nutzerprofilPanel.add(ueberschriftLabel);
			nutzerprofilPanel.add(showEigenesNpFlexTable);
			buttonPanel.add(bearbeitenButton);
			buttonPanel.add(loeschenButton);
			nutzerprofilPanel.add(buttonPanel);
			nutzerprofilPanel.add(infoLabel);
			

		}

		/**
		 * Methode erstellen, die das eigene Nutzerprofil anhand der Profil-ID
		 * ausliest und die Profildaten in die Tabelle einfuegt.
		 */
		public void befuelleTabelle() {
			ClientsideSettings.getNotizSystemAdministration().getNutzerById(profilId,
					new AsyncCallback<Nutzer>() {

						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Nutzer result) {
							showEigenesNpFlexTable.setText(0, 1, result.getVorname());
							showEigenesNpFlexTable.setText(1, 1, result.getName());
							showEigenesNpFlexTable.setText(2, 1, result.getEmail());
						}
					});
		}

		/**
		 * Methode erstellen, die das eigene Nutzerprofil loescht.
		 */
		public void loescheNutzer() {
			if (Window.confirm("Moechten Sie Ihr Profil wirklich loeschen?")) {

				ClientsideSettings.getNotizSystemAdministration().loescheNutzer(profilId, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Void result) {

						Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl());

					}
				});
			}
		}

	}

