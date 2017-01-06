package de.hdm.notizbuchsystem.shared.report;

import java.io.Serializable;
import java.util.*;

/**
 * Ein zusammengesetzter Report. Dieser Report kann aus einer Menge von 
 * Teil-Reports (vgl. Attribut <code>subReports</code>) bestehen.
 */
public abstract class zusammengesetzterReport 
	extends Report 
	implements Serializable {

	/*
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
	 * Die Menge der Teil-Reports.
	 */
	private Vector<Report> teilReport = new Vector<Report>();

	/**
	 * Hinzufügen eines Teil-Reports.
	 * @param r der hinzuzufügende Teil-Report.
	 */
	public void addTeilReport(Report r) {
		this.teilReport.addElement(r);
	}

	/**
	 * Entfernen eines Teil-Reports.
	 * @param r der zu entfernende Teil-Report.
	 */
	public void removeTeilReport(Report r) {
		this.teilReport.removeElement(r);
	}

	/**
	 * Auslesen der Anzahl von Teil-Reports.
	 * @return int Anzahl der Teil-Reports.
	 */
	public int getAnzahlTeilReport() {
		return this.teilReport.size();
	}

	/**
	 * Auslesen eines einzelnen Teil-Reports.
	 * @param i Position des Teilreports. Bei n Elementen läuft der Index i von 0
	 * bis n-1.
	 * 
	 * @return Position des Teil-Reports.
	 */	
	public Report getTeilReportBei(int i) {
		return this.teilReport.elementAt(i);
	}
}
