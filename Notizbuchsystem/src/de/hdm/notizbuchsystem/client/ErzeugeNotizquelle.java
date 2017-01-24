package de.hdm.notizbuchsystem.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ErzeugeNotizquelle implements EntryPoint, ValueChangeHandler<String>{
	private Label lbl = new Label();

	  public void onModuleLoad() {
		 
		// Erzeuge ein Hyperlink, dass die Applikations-History aendert.
	    // Create three hyperlinks that change the application's history.
		  
	    Hyperlink notizquelle = new Hyperlink();
	 
		// Falls die Applikation ohne eine History startet, dann wird zu einen neuen "start" status weitergeleitet.
	    // If the application starts with no history token, redirect to a new
	    // 'baz' state.
	    String initToken = History.getToken();
	    if (initToken.length() == 0) {
	      History.newItem("start");
	    }

	    // Füge widgets zu den root panel hinzu.
	    // Add widgets to the root panel.
	    VerticalPanel panel = new VerticalPanel();
	    panel.add(lbl);
	    panel.add(notizquelle);
	    RootPanel.get().add(panel);
	    
	    // Füge eine History listener hinzu.
	    // Add history listener
	    History.addValueChangeHandler(this);

	    // Starte den aktuellen History listener.
	    // Now that we've setup our listener, fire the initial history state.
	    History.fireCurrentHistoryState();
	  }

	  public void onValueChange(ValueChangeEvent<String> event) {
	    // This method is called whenever the application's history changes. Set
	    // the label to reflect the current history token.
	    lbl.setText("The current history token is: " + event.getValue());
	  }
}