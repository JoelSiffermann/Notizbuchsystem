package de.hdm.notizbuchsystem.client;
import java.util.logging.Logger;

	import com.google.gwt.core.client.GWT;
	import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.notizbuchsystem.shared.CommonSettings;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministration;
	import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
	import de.hdm.notizbuchsystem.shared.ReportGenerator;
	import de.hdm.notizbuchsystem.shared.ReportGeneratorAsync; 


	public class ClientsideSettings extends CommonSettings {

		  /**
		   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
		   * namens <code>BankAdministration</code>.
		   */

		  private static NotizSystemAdministrationAsync Nutzer = null;

		  /**
		   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
		   * namens <code>ReportGenerator</code>.
		   */
		  private static ReportGeneratorAsync reportGenerator = null;

		  /**
		   * Name des Client-seitigen Loggers.
		   */
		  private static final String LOGGER_NAME = "Notiz Web Client";
		  
		  /**
		   * Instanz des Client-seitigen Loggers.
		   */
		  private static final Logger log = Logger.getLogger(LOGGER_NAME);

		  /**
		   * <p>
		   * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
		   * </p>
		   * 
		   * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
		   * 
		   * <pre>
		   * Logger logger = ClientSideSettings.getLogger();
		   * </pre>
		   * 
		   * und dann Nachrichten schreiben etwa mittels
		   * 
		   * <pre>
		   * logger.severe(&quot;Sie sind nicht berechtigt, ...&quot;);
		   * </pre>
		   * 
		   * oder
		   * 
		   * <pre>
		   * logger.info(&quot;Lege neuen Kunden an.&quot;);
		   * </pre>
		   * 
		   * <p>
		   * Bitte auf <em>angemessene Log Levels</em> achten! Severe und info sind nur
		   * Beispiele.
		   * </p>
		   * 
		   * <h2>HINWEIS:</h2>
		   * <p>
		   * Beachten Sie, dass Sie den auszugebenden Log nun nicht mehr durch
		   * bedarfsweise Einfügen und Auskommentieren etwa von
		   * <code>System.out.println(...);</code> steuern. Sie belassen künftig
		   * sämtliches Logging im Code und können ohne abermaliges Kompilieren den Log
		   * Level "von außen" durch die Datei <code>logging.properties</code> steuern.
		   * Sie finden diese Datei in Ihrem <code>war/WEB-INF</code>-Ordner. Der dort
		   * standardmäßig vorgegebene Log Level ist <code>WARN</code>. Dies würde
		   * bedeuten, dass Sie keine <code>INFO</code>-Meldungen wohl aber
		   * <code>WARN</code>- und <code>SEVERE</code>-Meldungen erhielten. Wenn Sie
		   * also auch Log des Levels <code>INFO</code> wollten, müssten Sie in dieser
		   * Datei <code>.level = INFO</code> setzen.
		   * </p>
		   * 
		   * Weitere Infos siehe Dokumentation zu Java Logging.
		   * 
		   * @return die Logger-Instanz für die Server-Seite
		   */
		  public static Logger getLogger() {
		    return log;
		  }

		  /**
		   * <p>
		   * Anlegen und Auslesen der applikationsweit eindeutigen BankAdministration. Diese
		   * Methode erstellt die BankAdministration, sofern sie noch nicht existiert. Bei
		   * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
		   * Objekt zurückgegeben.
		   * </p>
		   * 
		   * <p>
		   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
		   * <code>BankAdministrationAsync bankVerwaltung = ClientSideSettings.getBankVerwaltung()</code>
		   * .
		   * </p>
		   * 
		   * @return eindeutige Instanz des Typs <code>BankAdministrationAsync</code>
		   *
		   */
		  public static NotizSystemAdministrationAsync getNutzer() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann...
		    if (Nutzer == null) {
		      // Zunächst instantiieren wir BankAdministration
		      Nutzer = GWT.create(NotizSystemAdministration.class);
		    }

		    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
		    return Nutzer;
		  }

		  /**
		   * <p>
		   * Anlegen und Auslesen des applikationsweit eindeutigen ReportGenerators.
		   * Diese Methode erstellt den ReportGenerator, sofern dieser noch nicht
		   * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
		   * zuvor angelegte Objekt zurückgegeben.
		   * </p>
		   * 
		   * <p>
		   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
		   * <code>ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator()</code>
		   * .
		   * </p>
		   * 
		   * @return eindeutige Instanz des Typs <code>BankAdministrationAsync</code>
		   
		   */
		  public static ReportGeneratorAsync getReportGenerator() {
		    // Gab es bislang noch keine ReportGenerator-Instanz, dann...
		    if (reportGenerator == null) {
		      // Zunächst instantiieren wir ReportGenerator
		      reportGenerator = GWT.create(ReportGenerator.class);

		      final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {
		        public void onFailure(Throwable caught) {
		          ClientsideSettings.getLogger().severe(
		              "Der ReportGenerator konnte nicht initialisiert werden!");
		        }

		        public void onSuccess(Void result) {
		          ClientsideSettings.getLogger().info(
		              "Der ReportGenerator wurde initialisiert.");
		        }
		      };

		      reportGenerator.init(initReportGeneratorCallback);
		    }

		    // So, nun brauchen wir den ReportGenerator nur noch zurückzugeben.
		    return reportGenerator;
		  }

		}


	

