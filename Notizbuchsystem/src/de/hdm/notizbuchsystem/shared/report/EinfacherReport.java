package de.hdm.notizbuchsystem.shared.report;

import java.util.Vector;

import de.hdm.notizbuchsystem.shared.report.Spalte;
import de.hdm.notizbuchsystem.shared.report.Report;
import de.hdm.notizbuchsystem.shared.report.Zeile;

/**
 * <p>
 * Ein einfacher Report, der neben den Informationen der Superklasse <code>
 * Report</code> eine Tabelle mit "Positionsdaten" aufweist. Die Tabelle greift
 * auf zwei Hilfsklassen namens <code>Row</code> und <code>Column</code> zurück.
 * </p>
 * <p>
 * Die Positionsdaten sind vergleichbar mit der Liste der Bestellpositionen
 * eines Bestellscheins. Dort werden in eine Tabelle zeilenweise Eintragung z.B.
 * bzgl. Artikelnummer, Artikelbezeichnung, Menge, Preis vorgenommen.
 * </p>
 * 
 * @see Zeile
 * @see Spalte
 * @author Thies
 */
public abstract class EinfacherReport extends Report {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
   * <code>Vector</code> abgelegt.
   */
  private Vector<Zeile> tabelle = new Vector<Zeile>();

  /**
   * Hinzufügen einer Zeile.
   * 
   * @param z die hinzuzufügende Zeile
   */
  public void addZeile(Zeile z) {
    this.tabelle.addElement(z);
  }

  /**
   * Entfernen einer Zeile.
   * 
   * @param z die zu entfernende Zeile.
   */
  public void removeZeile(Zeile z) {
    this.tabelle.removeElement(z);
  }

  /**
   * Auslesen sämtlicher Positionsdaten.
   * 
   * @return die Tabelle der Positionsdaten
   */
  public Vector<Zeile> getZeilen() {
    return this.tabelle;
  }
}
