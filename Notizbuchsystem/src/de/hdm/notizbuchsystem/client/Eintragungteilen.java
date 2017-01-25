package de.hdm.notizbuchsystem.client;

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Freigabe;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;

public class Eintragungteilen extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	private String email = Notizbuchsystem.getLoginInfo().getEmailAddress();
	
	
	int id;
	
	public Eintragungteilen(int id){
		this.id = id;
		run();
	}
	
	
	VerticalPanel verPanel = new VerticalPanel();
	
	CheckBox leseb =	new CheckBox("Leseberechtigung");
	CheckBox ab	=		new CheckBox("Schreibberechtigung");
	CheckBox loeb = 	new CheckBox("Loeschberechtigung");


	 
	
	 final SuggestBox suggestbox = new SuggestBox(getNutzer());
	 

	 
	 
	 	
	final Button teilen = new Button("Jetzt teilen!");
	

	
	protected void run() {
	
		verPanel.add(leseb);
		verPanel.add(ab);
		verPanel.add(loeb);
		verPanel.add(suggestbox);
		verPanel.add(teilen);
		
		RootPanel.get("Details").add(verPanel);
		
		
		
		teilen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ClientsideSettings.getNotizSystemAdministration().erstelleNotizFreigabe(id, leseb.getValue(),
						ab.getValue(), loeb.getValue(), email, suggestbox.getText(), 
						new AsyncCallback<Freigabe>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Freigabe result) {
								Window.alert(loeb.getValue().toString());
							}});
							}});

	}


	private MultiWordSuggestOracle getNutzer(){
		final MultiWordSuggestOracle Nutzer = new MultiWordSuggestOracle();
		
ClientsideSettings.getNotizSystemAdministration().getStringforSuggestBox(new AsyncCallback<Collection<String>>(){

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Collection<String> result) {
		Nutzer.addAll(result);
		}
});
	return Nutzer;
	}
}
