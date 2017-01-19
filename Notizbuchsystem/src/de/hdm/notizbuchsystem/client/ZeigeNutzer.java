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

import de.hdm.notizbuchsystem.shared.bo.Nutzer;

public class ZeigeNutzer extends Showcase {

	/**
	   * Jeder Showcase besitzt eine einleitende Überschrift, die durch diese
	   * Methode zu erstellen ist.
	   * 
	   * @see Showcase#getHeadlineText()
	   */
	  @Override
	  protected String getHeadlineText() {
	    return "Mein Profil:";
	  }
	  
	  private VerticalPanel verPanel = new VerticalPanel();
	  private HorizontalPanel buttonPanel = new HorizontalPanel();
	  
	  private FlexTable nutzerFlexTable = new FlexTable();
	  final Button bearbeiteNutzerButton = new Button("Mein Profil Bearbeiten");
	  final Button meinProfilloeschenButton = new Button("Mein Profil Loeschen");
	  private Label pfadLabelNA = new Label("Zurueck zu: Startseite");

	  /**
		 * Variable fuer die NutzerId erstellen.
		 */
		private int nutzerId;
		
		/**
		 * Variable fuer die ProfilId erstellen.
		 */
		private String profiltyp;

		
		/**
		 * Konstruktor erstellen.
		 * 
		 * @param nutzerId
		 *            Die Nutzer-ID des Nutzer, das angezeigt werden soll.
		 * @param profiltyp 
		 */
		public ZeigeNutzer(int nutzerId, String profiltyp) {
			this.nutzerId = nutzerId;
			this.profiltyp = profiltyp;
			
			run();
		}
	@Override
	protected void run() {
		
		buttonPanel.add(bearbeiteNutzerButton);
		buttonPanel.add(meinProfilloeschenButton);
		buttonPanel.add(pfadLabelNA);
		verPanel.add(nutzerFlexTable);
		
		
		RootPanel.get("Details").add(verPanel);
		RootPanel.get("Details").add(buttonPanel);
		
		
		nutzerFlexTable.addStyleName("FlexTable");
		nutzerFlexTable.setCellPadding(6);
		nutzerFlexTable.getColumnFormatter().addStyleName(0,
				"TableHeader");
		pfadLabelNA.addStyleName("notizbuchsystem-zurueckbutton");
		
		pfadLabelNA.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("Details").clear();
			}

		});
		
		
		/**
		 * Erste Spalte der Tabelle festlegen.
		 */
		nutzerFlexTable.setText(0, 0, "Vorname:");
		nutzerFlexTable.setText(1, 0, "Name:");
		nutzerFlexTable.setText(2, 0, "Email:");
		
		befuelleTabelle();
		
		
		
		
		bearbeiteNutzerButton.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
		        Showcase showcase = new BearbeiteNutzer(nutzerId, profiltyp);

		        /*
		         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
		         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
		         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
		         * Elemente dieses DIV.
		         */
		        RootPanel.get("Details").clear();
		        RootPanel.get("Details").add(showcase);
		      }
		    });

/**
		 * ClickHandler fuer den Button zum Loeschen des Nutzers erzeugen.
		 * Sobald der Button betaetigt wird, erscheint eine Bildschrimmeldung,
		 * die hinterfragt, ob das Nutzerprofil tatsaechlich geloescht werden
		 * soll. Wird diese mit "Ok" bestaetigt, wird der Nutzer aus der
		 * Datenbank entfernt. Zudem wird der Nutzer ausgeloggt und auf die
		 * Login-Seite weitergeleitet.
		 */
		meinProfilloeschenButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loescheNutzer();
			}
		});}
	
	/**
	 * Methode erstellen, die den eigenen Nutzer anhand der Nutzer-ID
	 * ausliest und die Profildaten in die Tabelle einfuegt.
	 */
	public void befuelleTabelle() {
		ClientsideSettings.getNotizSystemAdministration().getNutzerById(nutzerId,
				new AsyncCallback<Nutzer>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Nutzer result) {
						nutzerFlexTable.setText(0, 1, result.getVorname());
						nutzerFlexTable.setText(1, 1, result.getName());
						nutzerFlexTable.setText(2, 1, result.getEmail());
					}
				});
	}
	
	

		/**
		 * Methode erstellen, die das eigene Nutzerprofil loescht.
		 */
		public void loescheNutzer() {
			if (Window.confirm("Moechten Sie Ihr Profil wirklich loeschen?")) {

				ClientsideSettings.getNotizSystemAdministration().loescheNutzer(nutzerId, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Void result) {

						Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl());

					}
				});
		
		

			}
	
		
	}}

