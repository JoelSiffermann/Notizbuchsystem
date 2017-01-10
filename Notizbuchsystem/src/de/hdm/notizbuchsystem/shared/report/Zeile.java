package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Zeile einer Tabelle eines <code>SimpleReport</code>-Objekts. <code>Row</code>
 * -Objekte implementieren das <code>Serializable</code>-Interface und können
 * daher als Kopie z.B. vom Server an den Client übertragen werden.
 * 
 * @see EinfacherReport
 * @see Spalte
 * @author Thies
 */
public class Zeile implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * Speicherplatz für die Spalten der Zeile.
   */
  private Vector<Spalte> spalten = new Vector<Spalte>();

  /**
   * Hinzufügen einer Spalte.
   * 
   * @param s das Spaltenobjekt
   */
  public void addSpalte(Spalte s) {
    this.spalten.addElement(s);
  }

  /**
   * Entfernen einer benannten Spalte
   * 
   * @param s das zu entfernende Spaltenobjekt
   */
  public void removeSpalte(Spalte s) {
    this.spalten.removeElement(s);
  }

  /**
   * Auslesen sämtlicher Spalten.
   * 
   * @return <code>Vector</code>-Objekts mit sämtlichen Spalten
   */
  public Vector<Spalte> getSpalten() {
    return this.spalten;
  }

  /**
   * Auslesen der Anzahl sämtlicher Spalten.
   * 
   * @return int Anzahl der Spalten
   */
  public int getNumSpalten() {
    return this.spalten.size();
  }

  /**
   * Auslesen eines einzelnen Spalten-Objekts.
   * 
   * @param i der Index der auszulesenden Spalte (0 <= i < n), mit n = Anzahl
   *          der Spalten.
   * @return das gewünschte Spaltenobjekt.
   */
  public Spalte getSpalteBei(int i) {
    return this.spalten.elementAt(i);
  }
}
