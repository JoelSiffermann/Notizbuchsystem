package de.hdm.notizbuchsystem.shared.report;

import java.util.Vector;

import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;
import de.hdm.notizbuchsystem.shared.report.ZusammengesetzterAbsatz;
import de.hdm.notizbuchsystem.shared.report.Absatz;
import de.hdm.notizbuchsystem.shared.report.ReportWriter;
import de.hdm.notizbuchsystem.shared.report.Zeile;
import de.hdm.notizbuchsystem.shared.report.EinfacherAbsatz;

/**
 * Ein <code>ReportWriter</code>, der Reports mittels HTML formatiert. Das im
 * Zielformat vorliegende Ergebnis wird in der Variable <code>reportText</code>
 * abgelegt und kann nach Aufruf der entsprechenden Prozessierungsmethode mit
 * <code>getReportText()</code> ausgelesen werden.
 * 
 * @author Thies
 */
public class HTMLReport extends ReportWriter {

  /**
   * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
   * <code>process...</code>-Methoden) belegt. Format: HTML-Text
   */
  private String reportText = "";

  /**
   * Zurücksetzen der Variable <code>reportText</code>.
   */
  public void resetReportText() {
    this.reportText = "";
  }

  /**
   * Umwandeln eines <code>Paragraph</code>-Objekts in HTML.
   * 
   * @param p der Paragraph
   * @return HTML-Text
   */
  public String absatz2HTML(Absatz a) {
    if (a instanceof ZusammengesetzterAbsatz) {
      return this.absatz2HTML((ZusammengesetzterAbsatz) a);
    }
    else {
      return this.absatz2HTML((EinfacherAbsatz) a);
    }
  }

  /**
   * Umwandeln eines <code>CompositeParagraph</code>-Objekts in HTML.
   * 
   * @param p der CompositeParagraph
   * @return HTML-Text
   */
  public String absatz2HTML(ZusammengesetzterAbsatz a) {
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < a.getAnzahlAbschnitte(); i++) {
      result.append("<p>" + a.getAbsatzBei(i) + "</p>");
    }

    return result.toString();
  }

  /**
   * Umwandeln eines <code>SimpleParagraph</code>-Objekts in HTML.
   * 
   * @param p der SimpleParagraph
   * @return HTML-Text
   */
  public String absatz2HTML(EinfacherAbsatz a) {
    return "<p>" + a.toString() + "</p>";
  }

  /**
   * HTML-Header-Text produzieren.
   * 
   * @return HTML-Text
   */
  public String getHeader() {
    StringBuffer result = new StringBuffer();

    result.append("<html><head><title></title></head><body>");
    return result.toString();
  }

  /**
   * HTML-Trailer-Text produzieren.
   * 
   * @return HTML-Text
   */
  public String getTrailer() {
    return "</body></html>";
  }

  /**
   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
   * 
   * @param r der zu prozessierende Report
   */
  @Override
public void process(NotizNachTitelUndDatumReport r) {
    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
    this.resetReportText();

    /*
     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
     * unsere Ergebnisse.
     */
    StringBuffer result = new StringBuffer();

    /*
     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
     * ausgelesen und in HTML-Form übersetzt.
     */
    result.append("<H1>" + r.getTitel() + "</H1>");
    result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
    result.append("<td valign=\"top\"><b>" + absatz2HTML(r.getKopfdaten())
        + "</b></td>");
    result.append("<td valign=\"top\">" + absatz2HTML(r.getImpressum())
        + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");

    Vector<Zeile> zeilen = r.getZeilen();
    result.append("<table style=\"width:400px\">");

    for (int i = 0; i < zeilen.size(); i++) {
      Zeile zeile = zeilen.elementAt(i);
      result.append("<tr>");
      for (int k = 0; k < zeile.getNumSpalten(); k++) {
        if (i == 0) {
          result.append("<td style=\"background:silver;font-weight:bold\">" + zeile.getSpalteBei(k)
              + "</td>");
        }
        else {
          if (i > 1) {
            result.append("<td style=\"border-top:1px solid silver\">"
                + zeile.getSpalteBei(k) + "</td>");
          }
          else {
            result.append("<td valign=\"top\">" + zeile.getSpalteBei(k) + "</td>");
          }
        }
      }
      result.append("</tr>");
    }

    result.append("</table>");

    /*
     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
     * Ergebnis mittels getReportText() auszulesen.
     */
    this.reportText = result.toString();
  }
  
  /**
   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
   * 
   * @param r der zu prozessierende Report
   */
  @Override
public void process(NotizNachNutzerUndBerechtigungReport r) {
    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
    this.resetReportText();

    /*
     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
     * unsere Ergebnisse.
     */
    StringBuffer result = new StringBuffer();

    /*
     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
     * ausgelesen und in HTML-Form übersetzt.
     */
    result.append("<H1>" + r.getTitel() + "</H1>");
    result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
    result.append("<td valign=\"top\"><b>" + absatz2HTML(r.getKopfdaten())
        + "</b></td>");
    result.append("<td valign=\"top\">" + absatz2HTML(r.getImpressum())
        + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");

    Vector<Zeile> zeilen = r.getZeilen();
    result.append("<table style=\"width:400px\">");

    for (int i = 0; i < zeilen.size(); i++) {
      Zeile zeile = zeilen.elementAt(i);
      result.append("<tr>");
      for (int k = 0; k < zeile.getNumSpalten(); k++) {
        if (i == 0) {
          result.append("<td style=\"background:silver;font-weight:bold\">" + zeile.getSpalteBei(k)
              + "</td>");
        }
        else {
          if (i > 1) {
            result.append("<td style=\"border-top:1px solid silver\">"
                + zeile.getSpalteBei(k) + "</td>");
          }
          else {
            result.append("<td valign=\"top\">" + zeile.getSpalteBei(k) + "</td>");
          }
        }
      }
      result.append("</tr>");
    }

    result.append("</table>");

    /*
     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
     * Ergebnis mittels getReportText() auszulesen.
     */
    this.reportText = result.toString();
  }
  
//
//  /**
//   * Prozessieren des übergebenen Reports und Ablage im Zielformat. Ein Auslesen
//   * des Ergebnisses kann später mittels <code>getReportText()</code> erfolgen.
//   * 
//   * @param r der zu prozessierende Report
//   */
//  @Override
//public void process(NotizNachNutzerUndBerechtigungReport r) {
//    // Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen.
//    this.resetReportText();
//
//    /*
//     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
//     * unsere Ergebnisse.
//     */
//    StringBuffer result = new StringBuffer();
//
//    /*
//     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
//     * ausgelesen und in HTML-Form übersetzt.
//     */
//    result.append("<H1>" + r.getTitel() + "</H1>");
//    result.append("<table><tr>");
//
//    if (r.getKopfdaten() != null) {
//      result.append("<td>" + absatz2HTML(r.getKopfdaten()) + "</td>");
//    }
//
//    result.append("<td>" + absatz2HTML(r.getImpressum()) + "</td>");
//    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
//        + "</td></tr></table>");
//
//    /*
//     * Da AllAccountsOfAllCustomersReport ein CompositeReport ist, enthält r
//     * eine Menge von Teil-Reports des Typs AllAccountsOfCustomerReport. Für
//     * jeden dieser Teil-Reports rufen wir processAllAccountsOfCustomerReport
//     * auf. Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
//     */
//    for (int i = 0; i < r.getAnzahlTeilReport(); i++) {
//      /*
//       * AllAccountsOfCustomerReport wird als Typ der SubReports vorausgesetzt.
//       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
//       * so müsste hier eine detailliertere Implementierung erfolgen.
//       */
//      AllAccountsOfCustomerReport subReport = (AllAccountsOfCustomerReport) r
//          .getSubReportAt(i);
//
//      this.process(subReport);
//
//      result.append(this.reportText + "\n");
//
//      /*
//       * Nach jeder Übersetzung eines Teilreports und anschließendem Auslesen
//       * sollte die Ergebnisvariable zurückgesetzt werden.
//       */
//      this.resetReportText();
//    }
//
//    /*
//     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
//     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschließend das
//     * Ergebnis mittels getReportText() auszulesen.
//     */
//    this.reportText = result.toString();
//  }

  /**
   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
   * 
   * @return ein String im HTML-Format
   */
  public String getReportText() {
    return this.getHeader() + this.reportText + this.getTrailer();
  }
  
}

