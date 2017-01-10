package de.hdm.notizbuchsystem.shared.report;

import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;
import de.hdm.notizbuchsystem.shared.report.NotizenBzglTitelReport;
import de.hdm.notizbuchsystem.shared.report.NotizenBzglDatumReport;
import de.hdm.notizbuchsystem.shared.report.NotizTeilhabeReport;
import de.hdm.notizbuchsystem.shared.report.NutzerBerechtigungReport;

/**
 * <p>
 * Diese Klasse wird ben�tigt, um auf dem Client die ihm vom Server zur
 * Verf�gung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu �berf�hren.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat �berf�hrten Information wird den Subklassen �berlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die f�r die
 * Prozessierung der Quellinformation zust�ndig sind.
 * </p>
 * 
 * @author Thies
 */
public abstract class ReportWriter {

 
  public abstract void process(NotizNachNutzerUndBerechtigungReport r);

  public abstract void process(NotizNachTitelUndDatumReport r);

}
