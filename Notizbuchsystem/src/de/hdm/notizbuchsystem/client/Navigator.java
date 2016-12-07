package de.hdm.notizbuchsystem.client;

import java.util.List;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.shared.bo.Notiz;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;

/**
 * In dieser Klasse wird die Menubar erstellt.
 * 
 * @author Teuta
 */
public class Navigator extends HorizontalPanel {

	/**
	 * Neues Nutzer-Objekt anlegen mit Login-Infos.
	 */
	private Nutzer nutzer = Notizbuchsystem.getNutzer();

	private String logoutUrl = Notizbuchsystem.getLoginInfo().getLogoutUrl();

	/**
	 * Vertikales Panel erzeugen
	 */
	VerticalPanel verticalPanel1 = new VerticalPanel();

	/**
	 * Konstruktor erzeugen
	 * 
	 * @param nu Nutzer
	 */
	public Navigator(Nutzer nu) {
		run();
	}

	/**
	 * Methode erstellen, die den Aufbau der Seite startet.
	 */
	public void run() {
		this.add(verticalPanel1);

		/**
		 * Ab hier wird die Menuebar erstellt. Dabei werden abhaengig von der
		 * Thematik einzelne vertikale aufklappbare Menues zur horizontalen
		 * Menuehauptleiste "menu" hinzugefuegt.
		 */
		MenuBar menu = new MenuBar();
		menu.setAutoOpen(true);

		/**
		 * 
		 * Festlegen der Laenge der Menubar und Einbinden von CSS.
		 */
		menu.setWidth("100%");
		menu.setHeight("36px");

		menu.setAnimationEnabled(true);
		menu.setStyleName("MenuBar");

		/**
		 * Menueleiste für den Nutzer, in denen die Funktionen bezueglich des
		 * eigenen Profils zur Verfuegung gestellt werden.
		 */
		MenuBar nutzerMenu = new MenuBar(true);
		nutzerMenu.setAnimationEnabled(true);

		MenuItem nutzerAnzeigen = nutzerMenu.addItem("Nutzer verwalten",
				new Command() {
					@Override
					public void execute() {

						int profilId = nutzer.getNutzerId();
						String profiltyp = "Nu";

						ZeigeNutzer zeigeNutzer = new ZeigeNutzer(profilId,
								profiltyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(zeigeNutzer);
					}
				});

		nutzerAnzeigen.setStyleName("MenuItem");

		nutzerMenu.addSeparator();

		/**
		 * Menue für Notiz
		 */
		MenuBar notizMenu = new MenuBar(true);
		notizMenu.setAnimationEnabled(true);

		MenuItem notizAnlegen = notizMenu.addItem("Notiz anlegen",
				new Command() {

					@Override
					public void execute() {

						String profiltyp = "NA";

						ErstelleNotiz erstelleNotiz = new ErstelleNotiz(
								profiltyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(erstelleNotiz);

					}

				});

		notizAnlegen.setStyleName("MenuItem");

		/**
		 * Den einzelnen Menus werden verschiedene Items hinzugefuegt, denen
		 * jeweils ein Command uebergeben wird. Wird ein bestimmtes Item
		 * angeklickt, so wird der jeweilige Command ausgefuehrt.
		 */

		MenuItem notizAnzeigen = notizMenu.addItem("Meine Notizen anzeigen",
				new Command() {

					@Override
					public void execute() {
						String listtyp = "M";
						ZeigeNotiz zeigeNotiz = new ZeigeNotiz(listtyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(zeigeNotiz);
					}
				});

		notizAnzeigen.setStyleName("MenuItem");

		notizMenu.addSeparator();

		/**
		 * Menue für Notizbuch
		 * 
		 */

		MenuBar notizbuchMenu = new MenuBar(true);
		notizbuchMenu.setAnimationEnabled(true);

		MenuItem notizbuchAnlegen = notizbuchMenu.addItem("Notizbuch anlegen",
				new Command() {

					@Override
					public void execute() {

						String profiltyp = "NBA";

						ErstelleNotizbuch erstelleNotizbuch = new ErstelleNotizbuch(
								profiltyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(erstelleNotizbuch);

					}

				});

		notizbuchAnlegen.setStyleName("MenuItem");

		/**
		 * Den einzelnen Menus werden verschiedene Items hinzugefuegt, denen
		 * jeweils ein Command uebergeben wird. Wird ein bestimmtes Item
		 * angeklickt, so wird der jeweilige Command ausgefuehrt.
		 */

		MenuItem notizbuchAnzeigen = notizbuchMenu.addItem(
				"Meine Notizb�cher anzeigen", new Command() {

					@Override
					public void execute() {
						String listtyp = "B";
						ZeigeNotizbuch zeigeNotizbuch = new ZeigeNotizbuch(
								listtyp);
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(zeigeNotizbuch);
					}
				});

		notizbuchAnzeigen.setStyleName("MenuItem");

		notizbuchMenu.addSeparator();

		/**
		 * Menue für Logout
		 */

		MenuBar ausloggenMenu = new MenuBar(true);
		ausloggenMenu.setAnimationEnabled(true);

		ausloggenMenu.addItem("Ausloggen", new Command() {
			@Override
			public void execute() {
				Window.Location.replace(logoutUrl);

			}
		});

		/**
		 * Hinzufuegen der vertikalen Menueleisten nutzerMenu, notizMenu,
		 * notizbuchMenu und ausloggenMenu zur horizontalen Hauptleiste "menu"
		 * und Benennung der Menueleisten in der Menuebar per String-Uebergabe.
		 */

		menu.addItem(new MenuItem("Mein Profil", nutzerMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Notizen", notizMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Notizb�cher", notizbuchMenu));
		menu.addSeparator();
		menu.addItem(new MenuItem("Ausloggen", ausloggenMenu));

		/**
		 * Hinzufügen der Menübar zum RootPanel
		 */
		RootPanel.get("Header").add(menu);

	}
}
