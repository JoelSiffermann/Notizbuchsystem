package de.hdm.notizbuchsystem.shared.report;

import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;
import de.hdm.notizbuchsystem.shared.report.NotizenBzglTitelReport;
import de.hdm.notizbuchsystem.shared.report.NotizenBzglDatumReport;
import de.hdm.notizbuchsystem.shared.report.NotizTeilhabeReport;
import de.hdm.notizbuchsystem.shared.report.NutzerBerechtigungReport;

/**
 * <p>
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu überführen.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat überführten Information wird den Subklassen überlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
 * Prozessierung der Quellinformation zuständig sind.
 * </p>
 * 
 * @author Thies
 */
public abstract class ReportWriter {

 
  public abstract void process(NotizNachNutzerUndBerechtigungReport r);

  public abstract void process(NotizNachTitelUndDatumReport r);

}
