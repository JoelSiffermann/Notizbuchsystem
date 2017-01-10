package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Diese Klasse stellt eine Menge einzelner Absätze (
 * <code>SimpleParagraph</code>-Objekte) dar. Diese werden als Unterabschnitte
 * in einem <code>Vector</code> abgelegt verwaltet.
 * 
 * @author Thies
 */
public class ZusammengesetzterAbsatz extends Absatz implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Speicherort der Unterabschnitte.
   */
  private Vector<EinfacherAbsatz> unterAbschnitt = new Vector<EinfacherAbsatz>();

  /**
   * Einen Unterabschnitt hinzufügen.
   * 
   * @param a der hinzuzufügende Unterabschnitt.
   */
  public void addUnterAbschnitt(EinfacherAbsatz a) {
    this.unterAbschnitt.addElement(a);
  }

  /**
   * Einen Unterabschnitt entfernen.
   * 
   * @param a der zu entfernende Unterabschnitt.
   */
  public void removeUnterAbschnitt(EinfacherAbsatz a) {
    this.unterAbschnitt.removeElement(a);
  }

  /**
   * Auslesen sämtlicher Unterabschnitte.
   * 
   * @return <code>Vector</code>, der sämtliche Unterabschnitte enthält.
   */
  public Vector<EinfacherAbsatz> getUnterAbschnitte() {
    return this.unterAbschnitt;
  }

  /**
   * Auslesen der Anzahl der Unterabschnitte.
   * 
   * @return Anzahl der Unterabschnitte
   */
  public int getAnzahlAbschnitte() {
    return this.unterAbschnitt.size();
  }

  /**
   * Auslesen eines einzelnen Unterabschnitts.
   * 
   * @param i der Index des gewünschten Unterabschnitts (0 <= i <n), mit n =
   *          Anzahl der Unterabschnitte.
   * 
   * @return der gewünschte Unterabschnitt.
   */
  public EinfacherAbsatz getAbsatzBei(int i) {
    return this.unterAbschnitt.elementAt(i);
  }

  /**
   * Umwandeln eines <code>CompositeParagraph</code> in einen
   * <code>String</code>.
   */
  @Override
public String toString() {
    /*
     * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
     * String-Repräsentationen der Unterabschnitte eintragen.
     */
    StringBuffer result = new StringBuffer();

    // Schleife über alle Unterabschnitte
    for (int i = 0; i < this.unterAbschnitt.size(); i++) {
      EinfacherAbsatz a = this.unterAbschnitt.elementAt(i);

      /*
       * den jew. Unterabschnitt in einen String wandeln und an den Buffer hängen.
       */
      result.append(a.toString() + "\n");
    }

    /*
     * Schließlich wird der Buffer in einen String umgewandelt und
     * zurückgegeben.
     */
    return result.toString();
  }
}
