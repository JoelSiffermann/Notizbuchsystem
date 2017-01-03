package de.hdm.notizbuchsystem.client;


	import com.google.gwt.core.client.GWT;
	

	import de.hdm.notizbuchsystem.shared.LoginService;
	import de.hdm.notizbuchsystem.shared.LoginServiceAsync;
	import de.hdm.notizbuchsystem.shared.NotizSystemAdministration;
	import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
	import de.hdm.notizbuchsystem.shared.CommonSettings;
	import de.hdm.notizbuchsystem.shared.ReportGenerator;
	import de.hdm.notizbuchsystem.shared.ReportGeneratorAsync; 


	public class ClientsideSettings extends CommonSettings {


		/**
		 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
		 * Dienst namens NotizSystemAdministration.
		 */
		private static NotizSystemAdministrationAsync notizSystemAdministration = null;

		/**
		 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
		 * Dienst namens ReportGenerator.
		 */
		private static ReportGeneratorAsync reportGenerator = null;

		/**
		 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
		 * Dienst namens LoginService.
		 */
		private static LoginServiceAsync loginService = null;
		

		/**
		 * Anlegen und Auslesen der applikationsweit eindeutigen
		 * NotizSystemAdministration. Diese Methode erstellt die
		 * NotizSystemAdministration, sofern sie noch nicht existiert. Bei
		 * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
		 * Objekt zurueckgegeben.
		 * 
		 * @return Instanz des Typs NotizSystemAdministrationAsync
		 */
		public static NotizSystemAdministrationAsync getNotizSystemAdministration() {

			if (notizSystemAdministration == null) {
				notizSystemAdministration = GWT.create(NotizSystemAdministration.class);
			}
			return notizSystemAdministration;
		}

		/**
		 * Anlegen und Auslesen des applikationsweit eindeutigen ReportGenerators.
		 * Diese Methode erstellt den ReportGenerator, sofern dieser noch nicht
		 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
		 * zuvor angelegte Objekt zurueckgegeben.
		 * 
		 * @return Instanz des Typs ReportGeneratorAsync
		 */
		public static ReportGeneratorAsync getReportGenerator() {

			if (reportGenerator == null) {
				reportGenerator = GWT.create(ReportGenerator.class);
			}
			return reportGenerator;

		}
		
		/**
		 * Anlegen und Auslesen des applikationsweit eindeutigen LoginService.
		 * Diese Methode erstellt den LoginService, sofern dieser noch nicht
		 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
		 * zuvor angelegte Objekt zurueckgegeben.
		 * 
		 * @return Instanz des Typs LoginServiceAsync
		 */
		public static LoginServiceAsync getLoginService() {

			if (loginService == null) {
				loginService = GWT.create(LoginService.class);
			}
			return loginService;

		}

	}
