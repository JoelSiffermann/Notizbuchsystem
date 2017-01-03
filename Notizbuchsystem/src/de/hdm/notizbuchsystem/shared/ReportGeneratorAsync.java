package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;

import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.report.*;

public interface ReportGeneratorAsync {
	
	void setNutzer(Nutzer nutzer, AsyncCallback<Void> callback);
	
	void init(AsyncCallback<Void> callback);

	void erstelleNotizNachTitelundDatumReport(Date edatum, Date mdatum, Date fdatum, String titel, AsyncCallback<NotizNachTitelUndDatumReport> callback);
	
	void erstelleNotizNachNutzerUndBerechtigungReport(Nutzer nutzer, Freigabe freigabe, AsyncCallback<NotizNachNutzerUndBerechtigungReport> callback);

}
