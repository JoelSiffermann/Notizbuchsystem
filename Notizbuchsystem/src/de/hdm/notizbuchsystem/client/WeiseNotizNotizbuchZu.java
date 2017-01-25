package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WeiseNotizNotizbuchZu extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Notiz zu Notizbuch zuweisen:";
	}

	private Button zuweisenbutton = new Button("Zum ausgewaehlten Notizbuch hinzufügen");
	private VerticalPanel verPanel = new VerticalPanel();
	
	
	private int id;
	
	public WeiseNotizNotizbuchZu(int id){
		this.id = id;
		run();
	}
	
	@Override
	protected void run() {
		// TODO Auto-generated method stub

	}

}
