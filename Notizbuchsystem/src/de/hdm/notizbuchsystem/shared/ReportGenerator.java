package de.hdm.notizbuchsystem.shared;

import java.util.Date;

import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.*;

public interface ReportGenerator {
	
	public void setNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public void init() throws IllegalArgumentException;

	public NotizNachTitelUndDatumReport erstelleNotizNachTitelundDatumReport(Date edatum, Date mdatum, Date fdatum, String titel) throws IllegalArgumentException;
	
	public NotizNachNutzerUndBerechtigungReport erstelleNotizNachNutzerUndBerechtigungReport(Nutzer nutzer, Freigabe freigabe) throws IllegalArgumentException;
}
