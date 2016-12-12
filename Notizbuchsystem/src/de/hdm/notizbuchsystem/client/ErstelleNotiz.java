package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

// Klasse dient zum Erstellen einer neuen Notiz

public class ErstelleNotiz extends VerticalPanel{
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	private Label neueNotizErstellen = new Label("Neue Notiz anlegen");
	private TextBox Titel = new TextBox();
	private TextBox	SubTitel = new TextBox();
	private TextArea Inhalt = new TextArea();
	private DateBox Fälligkeit = new DateBox();
	
	private Button speichern = new Button("Speichern");
	private Button speichernUndNBZuweisen = new Button ("Speicher und Notizbuch zuweisen");
	

	
	
}
