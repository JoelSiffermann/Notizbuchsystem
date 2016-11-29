package de.hdm.notizbuchsystem.server.report;

import de.hdm.notizbuchsystem.server.NotizbuchAdministrationImpl;
import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;

import java.util.Date;

public class ReportGeneratorImpl {

	public ReportGeneratorImpl() throws IllegalArgumentException{
		
	}
	
	public void init() throws IllegalArgumentException{
		
//		NotizbuchAdministrationImpl notizbuchAdministrationImpl = new NotizbuchAdministrationImpl();
		
//		notizbuchAdministrationImpl.init();
		
	}
	
	public void setNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
	}
	
	public NotizNachTitelUndDatumReport erstelleNotizNachTitelUndDatumReport(Date edatum, Date mdatum, Date fdatum, String titel) throws IllegalArgumentException{
		
	}
	
	public NotizNachNutzerUndBerechtigungReport erstelleNotizNachNutzerUndBerechtigungReport(Nutzer nutzer, Freigabe freigabe) throws IllegalArgumentException{
		
	}
	
	
}
