package de.hdm.notizbuchsystem.server.report;

import de.hdm.notizbuchsystem.server.NotizbuchAdministrationImpl;
import de.hdm.notizbuchsystem.shared.ReportGenerator;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministration;
import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;
import de.hdm.notizbuchsystem.shared.report.Spalte;
import de.hdm.notizbuchsystem.shared.report.ZusammengesetzterAbsatz;
import de.hdm.notizbuchsystem.shared.report.Report;
import de.hdm.notizbuchsystem.shared.report.Zeile;
import de.hdm.notizbuchsystem.shared.report.EinfacherAbsatz;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	
	  private NotizSystemAdministration administration = null;

	  /**
	   * <p>
	   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	   * Konstruktors ist durch die Client-seitige Instantiierung durch
	   * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	   * m�glich.
	   * </p>
	   * <p>
	   * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	   * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	   * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	   * </p>
	   */
	  public ReportGeneratorImpl() throws IllegalArgumentException {
	  }

	  /**
	   * Initialsierungsmethode. Siehe dazu Anmerkungen zum No-Argument-Konstruktor.
	   * 
	   * @see #ReportGeneratorImpl()
	   */
	  @Override
	public void init() throws IllegalArgumentException {
	    
	    NotizbuchAdministrationImpl n = new NotizbuchAdministrationImpl();
	    n.init();
	    this.administration = n;
	  }

//	  public Nutzer getNutzer(Nutzer u) {
//		  return this.administration.getNutzerById(u.getId());
//	  }

	  /**
	   * Hinzuf�gen des Report-Impressums. Diese Methode ist aus den
	   * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	   * diese T�tigkeiten redundant auszuf�hren h�tte. Stattdessen rufen die
	   * <code>create...</code>-Methoden diese Methode auf.
	   * 
	   * @param r der um das Impressum zu erweiternde Report.
	   */
	  //TODO
	  protected void addImpressum(Report r) {
	    
	    //Nutzer nutzer = this.administration.);

	    
	    ZusammengesetzterAbsatz impressum = new ZusammengesetzterAbsatz();

	    impressum.addUnterAbschnitt(new EinfacherAbsatz("IT-Projekt WS16/17 Gruppe 1"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Jan Habersetzer Mat.Nr.:"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Joel Siffermann Mat.Nr.:"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Marcel Krebs Mat.Nr.:"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Teuta Gashi Mat.Nr.:"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Vi Quan Tran Mat.Nr.:"));
	    impressum.addUnterAbschnitt(new EinfacherAbsatz("Yannick Miller Mat.Nr.:"));

	    
	    r.setImpressum(impressum);

	  }

	  @Override
	public NotizNachTitelUndDatumReport erstelleNotizNachTitelUndDatumReport(
	      Notiz n, Eintragung e, Faelligkeit f, Nutzer u) throws IllegalArgumentException {

		  
		  NotizNachTitelUndDatumReport result = new NotizNachTitelUndDatumReport();

	    
	    result.setTitel("Notizen bezueglich ihrer Titel und des Datums");

	    // Imressum hinzuf�gen
	    this.addImpressum(result);

	    /*
	     * Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());

	    /*
	     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
	     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
	     * die Verwendung von CompositeParagraph.
	     */
	    ZusammengesetzterAbsatz header = new ZusammengesetzterAbsatz();

	    
	    header.addUnterAbschnitt(new EinfacherAbsatz(u.getName() + u.getVorname()));

	    
	    header.addUnterAbschnitt(new EinfacherAbsatz(u.getEmailAddress()));

	    
	    result.setKopfdaten(header);

	    /*
	     * Ab hier erfolgt ein zeilenweises Hinzuf�gen von Konto-Informationen.
	     */
	    
	    /*
	     * Zun�chst legen wir eine Kopfzeile f�r die Konto-Tabelle an.
	     */
	    Zeile headline = new Zeile();

	    /*
	     * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
	     * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
	     * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
	     * �berschriften ab.
	     */
	    headline.addSpalte(new Spalte("Titel"));
	    headline.addSpalte(new Spalte("Erstelldatum"));
	    headline.addSpalte(new Spalte("Modifikationsdatum"));
	    headline.addSpalte(new Spalte("F�lligkeitsdatum"));
	    

	    // Hinzuf�gen der Kopfzeile
	    result.addZeile(headline);

	    /*
	     * Nun werden s�mtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und
	     * Kontostand sukzessive in die Tabelle eingetragen.
	     */
	    Vector<Notiz> notizen = this.administration.getNotizenByNutzer(u);

	    for (Notiz n : notizen) {
	      // Eine leere Zeile anlegen.
	      Zeile notizZeile = new Zeile();

	      // Erste Spalte: Titel hinzuf�gen
	      notizZeile.addSpalte(new Spalte(String.valueOf(n.getTitel())));

	      // Zweite Spalte: Kontostand hinzuf�gen
	      notizZeile.addSpalte(new Spalte(String.valueOf(n.getErstelldatum())));
	      
	      notizZeile.addSpalte(new Spalte(String.valueOf(n.getModifikationsdatum())));
	      
	      notizZeile.addSpalte(new Spalte(String.valueOf(this.administration.getF)));

	      // und schlie�lich die Zeile dem Report hinzuf�gen.
	      result.addRow(accountRow);
	    }

	    /*
	     * Zum Schluss m�ssen wir noch den fertigen Report zur�ckgeben.
	     */
	    return result;
	  }

	  /**
	   * Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
	   * 
	   * @return der fertige Report
	   */
	  @Override
	public AllAccountsOfAllCustomersReport createAllAccountsOfAllCustomersReport()
	      throws IllegalArgumentException {

	    if (this.getBankVerwaltung() == null)
	      return null;

	    /*
	     * Zun�chst legen wir uns einen leeren Report an.
	     */
	    AllAccountsOfAllCustomersReport result = new AllAccountsOfAllCustomersReport();

	    // Jeder Report hat einen Titel (Bezeichnung / �berschrift).
	    result.setTitle("Alle Konten aller Kunden");

	    // Imressum hinzuf�gen
	    this.addImprint(result);

	    /*
	     * Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());

	    /*
	     * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
	     * AllAccountsOfCustomerReport-Objekten besteht, ben�tigen wir keine
	     * Kopfdaten f�r diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
	     */

	    /*
	     * Nun m�ssen s�mtliche Kunden-Objekte ausgelesen werden. Anschlie�end wir
	     * f�r jedes Kundenobjekt c ein Aufruf von
	     * createAllAccountsOfCustomerReport(c) durchgef�hrt und somit jeweils ein
	     * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
	     * sukzessive der result-Variable hinzugef�gt. Sie ist vom Typ
	     * AllAccountsOfAllCustomersReport, welches eine Subklasse von
	     * CompositeReport ist.
	     */
	    Vector<Customer> allCustomers = this.administration.getAllCustomers();

	    for (Customer c : allCustomers) {
	      /*
	       * Anlegen des jew. Teil-Reports und Hinzuf�gen zum Gesamt-Report.
	       */
	      result.addSubReport(this.createAllAccountsOfCustomerReport(c));
	    }

	    /*
	     * Zu guter Letzt m�ssen wir noch den fertigen Report zur�ckgeben.
	     */
	    return result;
	  }

	
	
	
}
