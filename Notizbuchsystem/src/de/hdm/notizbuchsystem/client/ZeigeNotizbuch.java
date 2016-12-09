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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.*;


/**
 * Diese Klasse dient dazu, ein Notizbuch anzuzeigen.
 */

public class ZeigeNotizbuch extends VerticalPanel {

	/**
	 * Panels erzeugen.
	 */
	private VerticalPanel notizbuchPanel = new VerticalPanel();
	private VerticalPanel notizPanel = new VerticalPanel();
	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Widgets erzeugen.
	 */
	private Label ueberschriftLabel = new Label("Mein Notizbuch:");
	private FlexTable zeigeNotizbuchFlexTable = new FlexTable();
	private Label notizLabel = new Label();
	private Button loeschenButton = new Button("Notizbuch löschen");
	private Button bearbeitenButton = new Button("Notizbuch bearbeiten");
	private Button notizhinzufuegenButton = new Button("Notiz hinzufügen");

	/**
	 * Variable fuer die notizbuchId erstellen.
	 */
	private int notizbuchId;

	/**
	 * Variable fuer den notizbuchtyp erstellen.
	 */
	private String notizbuchtyp;

	/**
	 * Konstruktor erstellen.
	 * 
	 * @param notizbuchId
	 *            Die Notizbuch-ID des Notizbuches, das angezeigt werden soll.
	 * @param profiltyp
	 *            Der Notizbuchtyp (Notizbuch).
	 */
	public ZeigeNotizbuch(int notizbuchId, String notizbuchtyp) {
		this.notizbuchId = notizbuchId;
		this.notizbuchtyp = notizbuchtyp;
		run();
	}

	/**
	 * Methode erstellen, die den Aufbau der Seite startet.
	 */
	public void run() {
		this.add(horizontalPanel);
		horizontalPanel.add(notizbuchPanel);
		horizontalPanel.add(notizPanel);

		/**
		 * CSS anwenden und die Tabelle formatieren.
		 */
		ueberschriftLabel.addStyleName("notizbuch-label");
		zeigeNotizbuchFlexTable.addStyleName("FlexTable");
		zeigeNotizbuchFlexTable.setCellPadding(6);
		zeigeNotizbuchFlexTable.getColumnFormatter().addStyleName(0, "TableHeader");

		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		zeigeNotizbuchFlexTable.setText(0, 0, "Titel");
		zeigeNotizbuchFlexTable.setText(1, 0, "Erstellungs Datum");
		zeigeNotizbuchFlexTable.setText(2, 0, "Modifikations Datum");
		zeigeNotizbuchFlexTable.setText(3, 0, "Eigentümer");
	
	
		befuelleTabelle();

		/**
		 * ClickHandler fuer den Button zum Bearbeiten des Notizbuches
		 * erzeugen. Sobald der Button betaetigt wird, wird die Seite zum
		 * Bearbeiten des Notizbuches aufgerufen.
		 */
		bearbeitenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				BearbeiteNotizbuch bearbeiteNotizbuch = new BearbeiteNotizbuch(notizbuchId, notizbuchtyp);
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(BearbeiteNotizbuch);
			}
		});

		/**
		 * ClickHandler fuer den Button zum Loeschen des Notizbuches erzeugen.
		 * Sobald der Button betaetigt wird, erscheint eine Bildschrimmeldung,
		 * die hinterfragt, ob das Notizbuch tatsaechlich geloescht werden
		 * soll. Wird diese mit "Ok" bestaetigt, wird das Notizbuch aus der
		 * Datenbank entfernt. 
		 */
		loeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loescheNotizbuch();
			}
		});

		

		/**
		 * Widgets den Panels hinzufuegen.
		 */
		notizbuchPanel.add(ueberschriftLabel);
		notizbuchPanel.add(zeigeNotizbuchFlexTable);
		buttonPanel.add(bearbeitenButton);
		buttonPanel.add(loeschenButton);
		notizbuchPanel.add(buttonPanel);
		notizbuchPanel.add(notizLabel);
		notizPanel.add(zeigeNotiz);

	}

	/**
	 * Methode erstellen, die das Notizbuch anhand der Notizbuch-ID
	 * ausliest und die daten in die Tabelle einfuegt.
	 */
	public void befuelleTabelle() {
		ClientsideSettings.getNotizSystemAdministration().getNotizbuchById(notizbuchId,
				new AsyncCallback<Notizbuch>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Notizbuch result) {
						zeigeNotizbuchFlexTable.setText(0, 1, result.getTitel());
						Date erstellDatum = result.getErstelldatum();
						String erstellDatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(erstellDatum);
						zeigeNotizbuchFlexTable.setText(3, 1, erstellDatumString);
						Date modiDatum = result.getModifikationsdatum();
						String modiDatumString = DateTimeFormat.getFormat("dd.MM.yyyy").format(modiDatum);
						zeigeNotizbuchFlexTable.setText(4, 1, modiDatumString);
						zeigeNotizbuchFlexTable.setText(5, 1, result.getEigentuemer());
					
					}
				});
	}

	/**
	 * Methode erstellen, die das Notizbuch loescht.
	 */
	public void loescheNotizbuch() {
		if (Window.confirm("Möchten Sie das Notizbuch wirklich löschen?")) {

			ClientsideSettings.getNotizSystemAdministration().loescheNotizbuch(notizbuchId, new AsyncCallback<Void>() {

				public void onFailure(Throwable caught) {
				}

				public void onSuccess(Void result) {

//Muss noch codiert werden.

				}
			});
		}
	}

}
