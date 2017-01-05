package de.hdm.notizbuchsystem.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.notizbuchsystem.shared.bo.Nutzer;

public class Navigator extends VerticalPanel {

	private VerticalPanel navPanel = new VerticalPanel();
	private Button nutzerButton = new Button("Verwalte Nutzer");
	private Button notizButton = new Button("Verwalte Notizen");
	private Button notizbuchButton = new Button("Verwalte Notizbuecher");
	private Button logoutButton = new Button("Logout");
	
	private Nutzer nutzer = Notizbuchsystem.getNp();
	private String logoutUrl = Notizbuchsystem.getLoginInfo().getLogoutUrl();
	
	
	
	public Navigator(){
		run();
	}
	
	public void run() {
	
	
    nutzerButton.setStylePrimaryName("test-menubutton");
    notizbuchButton.setStylePrimaryName("test-menubutton");
    notizButton.setStylePrimaryName("test-menubutton");
    logoutButton.setStylePrimaryName("test-menubutton");
        
    navPanel.add(nutzerButton);
    navPanel.add(notizbuchButton);
    navPanel.add(notizButton);
    navPanel.add(logoutButton);
    
    notizbuchButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
    
        Showcase showcase = new VerwalteNotizbuch();
       
        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

   
    notizButton.addClickHandler(new ClickHandler() {
      @Override
	public void onClick(ClickEvent event) {
        /*
         * Showcase instantiieren.
         */
        Showcase showcase = new VerwalteNotiz();

        RootPanel.get("Details").clear();
        RootPanel.get("Details").add(showcase);
      }
    });

   
      nutzerButton.addClickHandler(new ClickHandler() {
        @Override
  	public void onClick(ClickEvent event) {
      
          Showcase showcase = new VerwalteNutzer();
     
          RootPanel.get("Details").clear();
          RootPanel.get("Details").add(showcase);
        }
      });
}}
