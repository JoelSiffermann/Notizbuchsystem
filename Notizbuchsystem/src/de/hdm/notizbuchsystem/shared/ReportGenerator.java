package de.hdm.notizbuchsystem.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.*;

@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator {
	
	public void setNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public void init() throws IllegalArgumentException;

	
	public NotizNachNutzerUndBerechtigungReport erstelleNotizNachNutzerUndBerechtigungReport(Nutzer nutzer, Freigabe freigabe) throws IllegalArgumentException;

	public NotizNachTitelUndDatumReport erstelleNotizNachTitelUndDatumReport(
			Date edatum, Date mdatum, Date fdatum, String titel)
			throws IllegalArgumentException;
}
