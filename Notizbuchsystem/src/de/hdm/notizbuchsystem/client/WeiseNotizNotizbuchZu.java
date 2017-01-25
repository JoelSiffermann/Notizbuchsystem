package de.hdm.notizbuchsystem.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Notizbuch;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;

public class WeiseNotizNotizbuchZu extends Showcase {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Notiz zu Notizbuch zuweisen:";
	}

	private Button zuweisenbutton = new Button("Zum ausgewaehlten Notizbuch hinzufuegen");
	private VerticalPanel verPanel = new VerticalPanel();
	
	
	private int id;
	 SuggestBox suggestbox = new SuggestBox(getNotizbuchliste());
	
	
	public WeiseNotizNotizbuchZu(int id){
		this.id = id;
		run();
	}
	
	@Override
	protected void run() {
		 verPanel.add(suggestbox);
		 verPanel.add(zuweisenbutton);
		RootPanel.get("Details").add(verPanel);

	}
	
	private MultiWordSuggestOracle getNotizbuchliste(){
	MultiWordSuggestOracle Notizbuchliste = new MultiWordSuggestOracle();
//	ClientsideSettings.getNotizSystemAdministration().getNotizbuecherByNutzer(email,
//			new AsyncCallback<Nutzer>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					// TODO Auto-generated method stub
//					
//				}
//
//				@Override
//				public void onSuccess(Nutzer result) {
//					// TODO Auto-generated method stub
//					
//		for(int i=0; i< result.le)		
//		
//	}
	
	Notizbuchliste.add("asda@example.com");
	return Notizbuchliste;
	}
}
