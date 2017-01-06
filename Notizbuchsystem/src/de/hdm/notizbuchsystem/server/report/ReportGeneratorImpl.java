package de.hdm.notizbuchsystem.server.report;

import de.hdm.notizbuchsystem.server.NotizbuchAdministrationImpl;
import de.hdm.notizbuchsystem.shared.ReportGenerator;
import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.NotizNachNutzerUndBerechtigungReport;
import de.hdm.notizbuchsystem.shared.report.NotizNachTitelUndDatumReport;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	public ReportGeneratorImpl() throws IllegalArgumentException{
		
	}
	
	public void init() throws IllegalArgumentException{
		
//		NotizbuchAdministrationImpl notizbuchAdministrationImpl = new NotizbuchAdministrationImpl();
		
//		notizbuchAdministrationImpl.init();
		
	}
	
	public void setNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
	}
	
	public NotizNachTitelUndDatumReport erstelleNotizNachTitelUndDatumReport(Date edatum, Date mdatum, Date fdatum, String titel) throws IllegalArgumentException{
		return null;
		
	}
	
	public NotizNachNutzerUndBerechtigungReport erstelleNotizNachNutzerUndBerechtigungReport(Nutzer nutzer, Freigabe freigabe) throws IllegalArgumentException{
		return null;
		
	}

	@Override
	public NotizNachTitelUndDatumReport erstelleNotizNachTitelundDatumReport(
			Date edatum, Date mdatum, Date fdatum, String titel)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
