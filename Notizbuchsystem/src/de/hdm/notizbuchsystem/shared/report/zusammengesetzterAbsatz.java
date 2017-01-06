package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Diese Klasse stellt eine Menge einzelner Abs�tze (
 * <code>SimpleParagraph</code>-Objekte) dar. Diese werden als Unterabschnitte
 * in einem <code>Vector</code> abgelegt verwaltet.
 * 
 * @author Thies
 */
public class zusammengesetzterAbsatz extends Absatz implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Speicherort der Unterabschnitte.
   */
  private Vector<einfacherAbsatz> unterAbschnitt = new Vector<einfacherAbsatz>();

  /**
   * Einen Unterabschnitt hinzuf�gen.
   * 
   * @param a der hinzuzuf�gende Unterabschnitt.
   */
  public void addUnterAbschnitt(einfacherAbsatz a) {
    this.unterAbschnitt.addElement(a);
  }

  /**
   * Einen Unterabschnitt entfernen.
   * 
   * @param a der zu entfernende Unterabschnitt.
   */
  public void removeUnterAbschnitt(einfacherAbsatz a) {
    this.unterAbschnitt.removeElement(a);
  }

  /**
   * Auslesen s�mtlicher Unterabschnitte.
   * 
   * @return <code>Vector</code>, der s�mtliche Unterabschnitte enth�lt.
   */
  public Vector<einfacherAbsatz> getUnterAbschnitte() {
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
   * @param i der Index des gew�nschten Unterabschnitts (0 <= i <n), mit n =
   *          Anzahl der Unterabschnitte.
   * 
   * @return der gew�nschte Unterabschnitt.
   */
  public einfacherAbsatz getAbsatzBei(int i) {
    return this.unterAbschnitt.elementAt(i);
  }

  /**
   * Umwandeln eines <code>CompositeParagraph</code> in einen
   * <code>String</code>.
   */
  @Override
public String toString() {
    /*
     * Wir legen einen leeren Buffer an, in den wir sukzessive s�mtliche
     * String-Repr�sentationen der Unterabschnitte eintragen.
     */
    StringBuffer result = new StringBuffer();

    // Schleife �ber alle Unterabschnitte
    for (int i = 0; i < this.unterAbschnitt.size(); i++) {
      einfacherAbsatz a = this.unterAbschnitt.elementAt(i);

      /*
       * den jew. Unterabschnitt in einen String wandeln und an den Buffer h�ngen.
       */
      result.append(a.toString() + "\n");
    }

    /*
     * Schlie�lich wird der Buffer in einen String umgewandelt und
     * zur�ckgegeben.
     */
    return result.toString();
  }
}
