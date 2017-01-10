package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Basisklasse aller Reports. Reports sind als <code>Serializable</code>
 * deklariert, damit sie von dem Server an den Client gesendet werden können.
 * Der Zugriff auf Reports erfolgt also nach deren Bereitstellung lokal auf dem
 * Client.
 * </p>
 * <p>
 * Ein Report besitzt eine Reihe von Standardelementen. Sie werden mittels
 * Attributen modelliert und dort dokumentiert.
 * </p>
 * 
 * @see Report
 * @author Thies
 */
public abstract class Report implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Ein kleines Impressum, das eine Art Briefkopf darstellt. Jedes Unternehmen
   * einige Daten wie Firmenname, Adresse, Logo, etc. auf Geschäftsdokumenten
   * ab. Dies gilt auch für die hier realisierten Reports.
   */
  private Absatz impressum = null;

  /**
   * Kopfdaten des Berichts.
   */
  private Absatz kopfDaten = null;

  /**
   * Jeder Bericht kann einen individuellen Titel besitzen.
   */
  private String titel = "Report";

  /**
   * Datum der Erstellung des Berichts.
   */
  private Date created = new Date();

  /**
   * Auslesen des Impressums.
   * 
   * @return Text des Impressums
   */
  public Absatz getImpressum() {
    return this.impressum;
  }

  /**
   * Setzen des Impressums.
   * 
   * @param imprint Text des Impressums
   */
  public void setImpressum(Absatz impressum) {
    this.impressum = impressum;
  }

  /**
   * Auslesen der Kopfdaten.
   * 
   * @return Text der Kopfdaten.
   */
  public Absatz getKopfdaten() {
    return this.kopfDaten;
  }

  /**
   * Setzen der Kopfdaten.
   * 
   * @param headerData Text der Kopfdaten.
   */
  public void setKopfdaten(Absatz kopfDaten) {
    this.kopfDaten = kopfDaten;
  }

  /**
   * Auslesen des Berichtstitels.
   * 
   * @return Titeltext
   */
  public String getTitel() {
    return this.titel;
  }

  /**
   * Setzen des Berichtstitels.
   * 
   * @param title Titeltext
   */
  public void setTitel(String titel) {
    this.titel = titel;
  }

  /**
   * Auslesen des Erstellungsdatums.
   * 
   * @return Datum der Erstellung des Berichts
   */
  public Date getCreated() {
    return this.created;
  }

  /**
   * Setzen des Erstellungsdatums. <b>Hinweis:</b> Der Aufruf dieser Methoden
   * ist nicht unbedingt erforderlich, da jeder Report bei seiner Erstellung
   * automatisch den aktuellen Zeitpunkt festhält.
   * 
   * @param created Zeitpunkt der Erstellung
   */
  public void setCreated(Date created) {
    this.created = created;
  }

}
