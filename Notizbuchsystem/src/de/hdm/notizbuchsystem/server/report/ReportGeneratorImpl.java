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
import java.util.Map;
import java.util.Set;
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
	   * möglich.
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
	   * Hinzufügen des Report-Impressums. Diese Methode ist aus den
	   * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden
	   * diese Tätigkeiten redundant auszuführen hätte. Stattdessen rufen die
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
			Date edatum, Date mdatum, Date fdatum, String titel) throws IllegalArgumentException {

		  
		  
		  NotizNachTitelUndDatumReport result = new NotizNachTitelUndDatumReport();

	    
	    result.setTitel("Notizen bezueglich ihrer Titel und des Datums");

	    // Imressum hinzufügen
	    this.addImpressum(result);

	    /*
	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
	     */
	    result.setCreated(new Date());


	    Zeile headline = new Zeile();

	    
	    headline.addSpalte(new Spalte("Titel"));
	    headline.addSpalte(new Spalte("Subtitel"));
	    headline.addSpalte(new Spalte("Eigentümer"));
	    headline.addSpalte(new Spalte("Inhalt"));
	    headline.addSpalte(new Spalte("Erstelldatum"));
	    headline.addSpalte(new Spalte("Modifikationsdatum"));
	    headline.addSpalte(new Spalte("Fälligkeitsdatum"));
	    

	    // Hinzufügen der Kopfzeile
	    result.addZeile(headline);

	    Map<Vector<Notiz>,Vector<Faelligkeit>> resultMap = this.administration.getNotizByKriterium(titel, edatum, mdatum, fdatum);
	    Set<Vector<Notiz>> output = resultMap.keySet();
	    
	    for (Vector<Notiz> notizen : output) {
	    	
	    	Vector<Faelligkeit> faelligkeiten = new Vector<Faelligkeit>();
	    	faelligkeiten = resultMap.get(notizen);
	    	
	    	for(int f = 0; f < faelligkeiten.size() ; f++){
	    		
	    		Zeile z = new Zeile();
	    		z.addSpalte(new Spalte(notizen.get(f).getTitel()));
	    		z.addSpalte(new Spalte(notizen.get(f).getSubtitel()));
	    		z.addSpalte(new Spalte(notizen.get(f).getEigentuemer()));
	    		z.addSpalte(new Spalte(notizen.get(f).getInhalt()));
	    		z.addSpalte(new Spalte(String.valueOf(notizen.get(f).getErstelldatum())));
	    		z.addSpalte(new Spalte(String.valueOf(notizen.get(f).getModifikationsdatum())));
	    		z.addSpalte(new Spalte(String.valueOf(faelligkeiten.get(f).getDatum())));
	    		result.addZeile(z);
	    	}
	    }

	    /*
	     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
	     */
	    return result;
	  }
	  
	  @Override
	  public NotizNachNutzerUndBerechtigungReport erstelleNotizNachNutzerUndBerechtigungReport(Nutzer n, Freigabe f)
	  throws IllegalArgumentException {
		  
		  NotizNachNutzerUndBerechtigungReport result = new NotizNachNutzerUndBerechtigungReport();

		    
		    result.setTitel("Notizen bezueglich der freigegebenen Nutzer und deren Berechtigungen");

		    // Imressum hinzufügen
		    this.addImpressum(result);

		    /*
		     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		     */
		    result.setCreated(new Date());


		    Zeile headline = new Zeile();

		    
		    headline.addSpalte(new Spalte("Titel"));
		    headline.addSpalte(new Spalte("Subtitel"));
		    headline.addSpalte(new Spalte("Eigentümer"));
		    headline.addSpalte(new Spalte("Inhalt"));
		    headline.addSpalte(new Spalte("Erstelldatum"));
		    headline.addSpalte(new Spalte("Modifikationsdatum"));
		    headline.addSpalte(new Spalte("FreigegebenerNutzer"));
		    headline.addSpalte(new Spalte("Leseberechtigung"));
		    headline.addSpalte(new Spalte("Aenderungsberechtigung"));
		    headline.addSpalte(new Spalte("Loeschberechtigung"));
		    

		    // Hinzufügen der Kopfzeile
		    result.addZeile(headline);

		    Map<Vector<Notiz>,Vector<Freigabe>> resultMap = this.administration.getNotizByNutzerUndFreigabe(n, f);
		    Set<Vector<Notiz>> output = resultMap.keySet();
		    
		    for (Vector<Notiz> notizen : output) {
		    	
		    	Vector<Freigabe> freigabe = new Vector<Freigabe>();
		    	freigabe = resultMap.get(notizen);
		    	
		    	for(int fr = 0; fr < freigabe.size() ; fr++){
		    		
		    		Zeile z = new Zeile();
		    		z.addSpalte(new Spalte(notizen.get(fr).getTitel()));
		    		z.addSpalte(new Spalte(notizen.get(fr).getSubtitel()));
		    		z.addSpalte(new Spalte(notizen.get(fr).getEigentuemer()));
		    		z.addSpalte(new Spalte(notizen.get(fr).getInhalt()));
		    		z.addSpalte(new Spalte(String.valueOf(notizen.get(fr).getErstelldatum())));
		    		z.addSpalte(new Spalte(String.valueOf(notizen.get(fr).getModifikationsdatum())));
		    		
		    		result.addZeile(z);
		    	}
		    }

		    /*
		     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		     */
		    return result;
	  }

	@Override
	public void setNutzer(Nutzer nutzer) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}



//	  /**
//	   * Erstellen von <code>AllAccountsOfAllCustomersReport</code>-Objekten.
//	   * 
//	   * @return der fertige Report
//	   */
//	  @Override
//	public AllAccountsOfAllCustomersReport createAllAccountsOfAllCustomersReport()
//	      throws IllegalArgumentException {
//
//	    if (this.getBankVerwaltung() == null)
//	      return null;
//
//	    /*
//	     * Zunächst legen wir uns einen leeren Report an.
//	     */
//	    AllAccountsOfAllCustomersReport result = new AllAccountsOfAllCustomersReport();
//
//	    // Jeder Report hat einen Titel (Bezeichnung / überschrift).
//	    result.setTitle("Alle Konten aller Kunden");
//
//	    // Imressum hinzufügen
//	    this.addImprint(result);
//
//	    /*
//	     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
//	     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
//	     */
//	    result.setCreated(new Date());
//
//	    /*
//	     * Da AllAccountsOfAllCustomersReport-Objekte aus einer Sammlung von
//	     * AllAccountsOfCustomerReport-Objekten besteht, benötigen wir keine
//	     * Kopfdaten für diesen Report-Typ. Wir geben einfach keine Kopfdaten an...
//	     */
//
//	    /*
//	     * Nun müssen sämtliche Kunden-Objekte ausgelesen werden. Anschließend wir
//	     * für jedes Kundenobjekt c ein Aufruf von
//	     * createAllAccountsOfCustomerReport(c) durchgeführt und somit jeweils ein
//	     * AllAccountsOfCustomerReport-Objekt erzeugt. Diese Objekte werden
//	     * sukzessive der result-Variable hinzugefügt. Sie ist vom Typ
//	     * AllAccountsOfAllCustomersReport, welches eine Subklasse von
//	     * CompositeReport ist.
//	     */
//	    Vector<Customer> allCustomers = this.administration.getAllCustomers();
//
//	    for (Customer c : allCustomers) {
//	      /*
//	       * Anlegen des jew. Teil-Reports und Hinzufügen zum Gesamt-Report.
//	       */
//	      result.addSubReport(this.createAllAccountsOfCustomerReport(c));
//	    }
//
//	    /*
//	     * Zu guter Letzt müssen wir noch den fertigen Report zurückgeben.
//	     */
//	    return result;
//	  }

	
	
	
}
