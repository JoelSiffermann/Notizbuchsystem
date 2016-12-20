package de.hdm.notizbuchsystem.client;

import com.google.gwt.core.client.*;
import com.google.gwt.event.dom.client.*;

import com.google.gwt.user.client.ui.*;



/**
 * Entry-Point-Klasse des Projekts <b>BankProjekt</b>.
 */
public class Notizbuchsystem implements EntryPoint {

  /**
   * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
   * zusichert, benötigen wir eine Methode
   * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
   * <code>main()</code>-Methode normaler Java-Applikationen.
   */
  @Override
public void onModuleLoad() {
   

    /*
     * Wir bereiten nun die Erstellung eines bescheidenen Navigators vor, der
     * einige Schaltflächen (Buttons) für die Ausführung von Unterprogrammen
     * enthalten soll.
     * 
     * Die jeweils ausgeführten Unterprogramme sind Demonstratoren
     * exemplarischer Anwendungsfälle des Systems. Auf eine professionelle
     * Gestaltung der Benutzungsschnittstelle wurde bewusst verzichtet, um den
     * Blick nicht von den wesentlichen Funktionen abzulenken. Eine
     * exemplarische GUI-Realisierung findet sich separat.
     * 
     * Die Demonstratoren werden nachfolgend als Showcase bezeichnet. Aus diesem
     * Grund existiert auch eine Basisklasse für sämtliche Showcase-Klassen
     * namens Showcase.
     */

    /*
     * Der Navigator ist als einspaltige Aneinanderreihung von Buttons
     * realisiert. Daher bietet sich ein VerticalPanel als Container an.
     */
    VerticalPanel navPanel = new VerticalPanel();

    /*
     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
     */
    RootPanel.get("Navigator").add(navPanel);

    /*
     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
     */

    /*
     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
     */
    
    final Button nutzerButton = new Button("Verwalte Nutzer");

    /*
     * Unter welchem Namen können wir den Button durch die CSS-Datei des
     * Projekts formatieren?
     */
    nutzerButton.setStylePrimaryName("test-menubutton");

    /*
     * Hinzufügen des Buttons zum VerticalPanel.
     */
    navPanel.add(nutzerButton);

    /*
     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
     * wird.
     */
    nutzerButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
        Showcase showcase = new VerwalteNutzer();

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
    /*
     * Ab hier folgen weitere Button-Definitionen, die nach exakt der gleichen
     * Methode erfolgen wie beim ersten Button.
     * 
     * Da das Muster dazu sich mehrfach wiederholt, könnte man hier schon von
     * einem unerwünschte Code Clone sprechen. Um dies stilistisch zu optimieren
     * wäre z.B. die Verwendung des Factory oder Builder Pattern denkbar. 
     * Hierauf wurde jedoch bewusst verzichtet, um den Komplexitätsgrad dieses
     * Demonstrators nicht unnötig zu erhöhen. 
     */
    
    final Button notizbuchButton = new Button("Verwalte Notizbuch");

    /*
     * Unter welchem Namen können wir den Button durch die CSS-Datei des
     * Projekts formatieren?
     */
    notizbuchButton.setStylePrimaryName("test-menubutton");

    /*
     * Hinzufügen des Buttons zum VerticalPanel.
     */
    navPanel.add(notizbuchButton);

    /*
     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
     * wird.
     */
    notizbuchButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
        Showcase showcase = new VerwalteNotizbuch();

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

    /*
     * Ab hier folgen weitere Button-Definitionen, die nach exakt der gleichen
     * Methode erfolgen wie beim ersten Button.
     * 
     * Da das Muster dazu sich mehrfach wiederholt, könnte man hier schon von
     * einem unerwünschte Code Clone sprechen. Um dies stilistisch zu optimieren
     * wäre z.B. die Verwendung des Factory oder Builder Pattern denkbar. 
     * Hierauf wurde jedoch bewusst verzichtet, um den Komplexitätsgrad dieses
     * Demonstrators nicht unnötig zu erhöhen. 
     */
    final Button notizButton = new Button("Verwalte Notiz");

    /*
     * Unter welchem Namen können wir den Button durch die CSS-Datei des
     * Projekts formatieren?
     */
    notizButton.setStylePrimaryName("test-menubutton");

    /*
     * Hinzufügen des Buttons zum VerticalPanel.
     */
    navPanel.add(notizButton);

    /*
     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
     * wird.
     */
    notizButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
        Showcase showcase = new VerwalteNotiz();

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

    final Button logoutButton = new Button("Logout");

    /*
     * Unter welchem Namen können wir den Button durch die CSS-Datei des
     * Projekts formatieren?
     */
    logoutButton.setStylePrimaryName("test-menubutton");

    /*
     * Hinzufügen des Buttons zum VerticalPanel.
     */
    navPanel.add(logoutButton);

    /*
     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
     * wird.
     */
   // logoutButton.addClickHandler(new ClickHandler() {
    //  @Override
	//public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
     //   Showcase showcase = new ErstelleNotizbuch();

        /*
         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
         * Elemente dieses DIV.
         */
      //  RootPanel.get("Details").clear();
      //  RootPanel.get("Details").add(showcase);
    //  }
   // });


  }
}

