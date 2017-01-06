package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;

import de.hdm.notizbuchsystem.shared.report.Zeile;
import de.hdm.notizbuchsystem.shared.report.einfacherAbsatz;

/**
 * Spalte eines <code>Row</code>-Objekts. <code>Column</code>-Objekte
 * implementieren das <code>Serializable</code>-Interface und können daher als
 * Kopie z.B. vom Server an den Client übertragen werden.
 * 
 * @see Zeile
 * @author Thies
 */
public class Spalte implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Der Wert eines Spaltenobjekts entspricht dem Zelleneintrag einer Tabelle.
   * In dieser Realisierung handelt es sich um einen einfachen textuellen Wert.
   */
  private String wert = "";

  /**
   * <p>
   * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
   * müssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
   * explizit angegeben, so existiert in Java-Klassen der Default-Konstruktor,
   * der dem No-Argument-Konstruktor entspricht.
   * </p>
   * <p>
   * Besitzt eine Klasse mind. einen explizit implementierten Konstruktor, so
   * gelten nur diese explizit implementierten Konstruktoren. Der
   * Default-Konstruktor gilt dann nicht. Wenn wir in einer solchen Situation
   * aber dennoch einen No-Argument-Konstruktor benötigen, müssen wir diesen wie
   * in diesem Beispiel explizit implementieren.
   * </p>
   * 
   * @see #Column(String)
   * @see SimpleParagraph#SimpleParagraph()
   */
  public Spalte() {
  }

  /**
   * Konstruktor, der die Angabe eines Werts (Spalteneintrag) erzwingt.
   * 
   * @param s der Wert, der durch das Column-Objekt dargestellt werden soll.
   * @see #Column()
   */
  public Spalte(String s) {
    this.wert = s;
  }

  /**
   * Auslesen des Spaltenwerts.
   * 
   * @return der Eintrag als String
   */
  public String getWert() {
    return wert;
  }

  /**
   * Überschreiben des aktuellen Spaltenwerts.
   * 
   * @param value neuer Spaltenwert
   */
  public void setWert(String wert) {
    this.wert = wert;
  }

  /**
   * Umwandeln des <code>Column</code>-Objekts in einen String.
   * 
   * @see java.lang.Object
   */
  @Override
public String toString() {
    return this.wert;
  }
}
