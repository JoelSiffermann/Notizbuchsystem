package de.hdm.notizbuchsystem.client;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.ReportGeneratorAsync;
import de.hdm.notizbuchsystem.shared.bo.Notizbuch;;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Notizbuchsystem implements EntryPoint, ClickHandler {
	
	public void onClick(ClickEvent event){
		 Window.alert("Sie sind eingeloggt");
		  }
	 
	 
		public void onModuleLoad() {
			// Make some text boxes. The password text box is identical to the text
		    // box, except that the input is visually masked by the browser.
		    PasswordTextBox ptb = new PasswordTextBox();
		    TextBox tb = new TextBox();
		    
		    final Button button = new Button("Einloggen");
		    final Button button1 = new Button("Profil verwalten");
		    final Button button2 = new Button("Notizbuch verwalten");
		    final Button button3 = new Button("Notizbuch erstellen");
		    final Button button4 = new Button("Notizbuch erstellen");
		    final Button button5 = new Button("Notizerstellen");
		    final Label label = new Label();
		    //VerticalPanel navPanel = new VerticalPanel();
		    RootPanel.get("Navigator1").add(button1);
		   
			button.addClickHandler(new Notizbuchsystem());
			
		   
		    RootPanel.get("Navigator2").add(button2);
		    RootPanel.get("Navigator3").add(button3);
		    RootPanel.get("Navigator4").add(button4);
		    RootPanel.get("Navigator5").add(button5);
		    
		    RootPanel.get("slot1").add(tb);
		    RootPanel.get("slot2").add(ptb);
		    RootPanel.get("slot3").add(button);
		    RootPanel.get().add(label);
		    
		   /** NotizSystemAdministrationAsync nbVerwaltung = ClientsideSettings.getNutzer();
		    *Notizbuch nb = new Notizbuch();
		    *nb.setEnthalteneNotiz(enthalteneNotiz);
		    *nb.setTitel("Notizbuch1");
		    *nb.setErstelldatum(15.06);
		    *nb.setModifikationsdatum(modifikationsdatum);
		    *nb.setEigentuemer(eigentuemer);
		    *nb.setErstellungsZeitpunkt(erstellungszeitpunkt);
		    *nb.setId(id);
		    
		    *ReportGeneratorAsync reportGenerator = ClientsideSettings
		    *       .getReportGenerator();
		    *  reportGenerator.setBank(nb, new SetBankCallback());?!?
		    *  
		    * 

		    */
		    
		    
		}}
		     
			
