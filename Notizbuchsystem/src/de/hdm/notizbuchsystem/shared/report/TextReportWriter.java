package de.hdm.notizbuchsystem.shared.report;

import java.util.Vector;

import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;
import de.hdm.notizbuchsystem.shared.report.ReportWriter;
import de.hdm.notizbuchsystem.shared.report.Zeile;

public class TextReportWriter extends ReportWriter {
		  /**
		   * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
		   * <code>process...</code>-Methoden) belegt. Format: Text
		   */
		  private String reportText = "";

		  /**
		   * Zur�cksetzen der Variable <code>reportText</code>.
		   */
		  public void resetReportText() {
		    this.reportText = "";
		  }

		  /**
		   * Header-Text produzieren.
		   * 
		   * @return Text
		   */
		  public String getHeader() {
		    // Wir ben�tigen f�r Demozwecke keinen Header.
		    return "";
		  }

		  /**
		   * Trailer-Text produzieren.
		   * 
		   * @return Text
		   */
		  public String getTrailer() {
		    // Wir verwenden eine einfache Trennlinie, um das Report-Ende zu markieren.
		    return "___________________________________________";
		  }

		  /**
		   * Prozessieren des �bergebenen Reports und Ablage im Zielformat. Ein Auslesen
		   * des Ergebnisses kann sp�ter mittels <code>getReportText()</code> erfolgen.
		   * 
		   * @param r der zu prozessierende Report
		   */
		  @Override
		public void process(NotizNachNutzerUndBerechtigungReport r) {

		    // Zun�chst l�schen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir w�hrend der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

		    /*
		     * Nun werden Schritt f�r Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in Text-Form �bersetzt.
		     */
		    result.append("*** " + r.getTitel() + " ***\n\n");
		    result.append(r.getKopfdaten() + "\n");
		    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
		    Vector<Zeile> zeilen = r.getZeilen();

		    for (Zeile zeile : zeilen) {
		      for (int k = 0; k < zeile.getNumSpalten(); k++) {
		        result.append(zeile.getSpalteBei(k) + "\t ; \t");
		      }

		      result.append("\n");
		    }

		    result.append("\n");
		    result.append(r.getImpressum() + "\n");

		    /*
		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		     * reportText-Variable zugewiesen. Dadurch wird es m�glich, anschlie�end das
		     * Ergebnis mittels getReportText() auszulesen.
		     */
		    this.reportText = result.toString();
		  }

		  
		  @Override
		public void process(NotizNachTitelUndDatumReport r) {

		    // Zun�chst l�schen wir das Ergebnis vorhergehender Prozessierungen.
		    this.resetReportText();

		    /*
		     * In diesen Buffer schreiben wir w�hrend der Prozessierung sukzessive
		     * unsere Ergebnisse.
		     */
		    StringBuffer result = new StringBuffer();

		    /*
		     * Nun werden Schritt f�r Schritt die einzelnen Bestandteile des Reports
		     * ausgelesen und in Text-Form �bersetzt.
		     */
		    result.append("*** " + r.getTitel() + " ***\n\n");
		    result.append(r.getKopfdaten() + "\n");
		    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
		    Vector<Zeile> zeilen = r.getZeilen();

		    for (Zeile zeile : zeilen) {
		      for (int k = 0; k < zeile.getNumSpalten(); k++) {
		        result.append(zeile.getSpalteBei(k) + "\t ; \t");
		      }

		      result.append("\n");
		    }

		    result.append("\n");
		    result.append(r.getImpressum() + "\n");

		    /*
		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
		     * reportText-Variable zugewiesen. Dadurch wird es m�glich, anschlie�end das
		     * Ergebnis mittels getReportText() auszulesen.
		     */
		    this.reportText = result.toString();
		  }
		  
		  
		  
//		  /**
//		   * Prozessieren des �bergebenen Reports und Ablage im Zielformat. Ein Auslesen
//		   * des Ergebnisses kann sp�ter mittels <code>getReportText()</code> erfolgen.
//		   * 
//		   * @param r der zu prozessierende Report
//		   */
//		  @Override
//		public void process(AllAccountsOfAllCustomersReport r) {
//
//		    // Zun�chst l�schen wir das Ergebnis vorhergehender Prozessierungen.
//		    this.resetReportText();
//
//		    /*
//		     * In diesen Buffer schreiben wir w�hrend der Prozessierung sukzessive
//		     * unsere Ergebnisse.
//		     */
//		    StringBuffer result = new StringBuffer();
//
//		    /*
//		     * Nun werden Schritt f�r Schritt die einzelnen Bestandteile des Reports
//		     * ausgelesen und in Text-Form �bersetzt.
//		     */
//		    result.append("*** " + r.getTitle() + " ***\n\n");
//
//		    if (r.getHeaderData() != null)
//		      result.append(r.getHeaderData() + "\n");
//
//		    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
//
//		    /*
//		     * Da AllAccountsOfAllCustomersReport ein CompositeReport ist, enth�lt r
//		     * eine Menge von Teil-Reports des Typs AllAccountsOfCustomerReport. F�r
//		     * jeden dieser Teil-Reports rufen wir processAllAccountsOfCustomerReport
//		     * auf. Das Ergebnis des jew. Aufrufs f�gen wir dem Buffer hinzu.
//		     */
//		    for (int i = 0; i < r.getNumSubReports(); i++) {
//		      /*
//		       * AllAccountsOfCustomerReport wird als Typ der SubReports vorausgesetzt.
//		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
//		       * so m�sste hier eine detailliertere Implementierung erfolgen.
//		       */
//		      AllAccountsOfCustomerReport subReport = (AllAccountsOfCustomerReport) r
//		          .getSubReportAt(i);
//
//		      this.process(subReport);
//
//		      // Ein Form Feed w�re hier statt der 5 Leerzeilen auch denkbar...
//		      result.append(this.reportText + "\n\n\n\n\n");
//
//		      /*
//		       * Nach jeder �bersetzung eines Teilreports und anschlie�endem Auslesen
//		       * sollte die Ergebnisvariable zur�ckgesetzt werden.
//		       */
//		      this.resetReportText();
//		    }
//
//		    /*
//		     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
//		     * reportText-Variable zugewiesen. Dadurch wird es m�glich, anschlie�end das
//		     * Ergebnis mittels getReportText() auszulesen.
//		     */
//		    this.reportText = result.toString();
//		  }

		  /**
		   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
		   * 
		   * @return ein String bestehend aus einfachem Text
		   */
		  public String getReportText() {
		    return this.getHeader() + this.reportText + this.getTrailer();
		  }
		
}
