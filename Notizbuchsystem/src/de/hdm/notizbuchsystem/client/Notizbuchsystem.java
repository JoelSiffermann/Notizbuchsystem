package de.hdm.notizbuchsystem.client;


import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;




import de.hdm.notizbuchsystem.shared.NotizSystemAdministrationAsync;
import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.client.ClientsideSettings;
import de.hdm.notizbuchsystem.client.ErstelleNutzer;
import de.hdm.notizbuchsystem.client.LoginInfo;
import de.hdm.notizbuchsystem.shared.LoginServiceAsync;



/**
 * Entry-Point-Klasse des Projekts <b>NotizbuchSystem</b>.
 */
public class Notizbuchsystem implements EntryPoint {
	private Button nutzerButton = new Button("Mein Profil");
	private Button notizButton = new Button("Verwalte Notizen");
	private Button notizbuchButton = new Button("Verwalte Notizbuecher");
	private Button logoutButton = new Button("Logout");

	/**
	 * Neues Nutzerprofil erzeugen
	 */
	Nutzer nutzer = new Nutzer();

	/**
	 * Deklaraion der Labels fuer die Startseite(n)
	 */
	private Label begruessenN = new Label(
			"Herzlich Willkommen bei Notizi. ");
	private Label begruessenN2 = new Label(
			"Klicke dich nun durch die Webseite und verwalte deine Notizen");
	
	/**
	 * Deklaration fuer den Login und den Logout
	 */
	private static Nutzer np = null;
	private static LoginInfo loginInfo = null;
	
	private static String editorHtmlName = "Notizbuchsystem.html";

	private NotizSystemAdministrationAsync admin = ClientsideSettings
			.getNotizSystemAdministration();
	private LoginServiceAsync loginService = ClientsideSettings
			.getLoginService();
	
	public void onModuleLoad() {
		setStyles();
		
		VerticalPanel navPanel = new VerticalPanel();

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
	    	        	
	    
			Showcase showcase = new ZeigeNutzer(Notizbuchsystem.getLoginInfo().getEmailAddress());
	     
	          RootPanel.get("Details").clear();
	          RootPanel.get("Details").add(showcase);
	        }
	      });
	
	      
	      logoutButton.addClickHandler (new ClickHandler(){
	    	  public void onClick(ClickEvent event){
	    		  Window.Location.replace(Notizbuchsystem.getLoginInfo().getLogoutUrl());
	    	  }
	      });
	      
	  	RootPanel.get("Navigator").add(navPanel);
	  	RootPanel.get("Details").add(begruessenN);
	  	RootPanel.get("Details").add(begruessenN2);
	
	  	
		

		/**
		 * Login-Methode aufrufen und anschließend auf die Hostpage leiten.
		 */
		loginService.login(GWT.getHostPageBaseURL() + editorHtmlName,
				loginExecute());
	}
		
	
	/**
	 * AsyncCallback für die Login-Methode. Bei erhalt der LoginInfos wird die Methode
	 * pruefeObMutzerNeu() aufgerufen.
	 */
	
	private AsyncCallback<LoginInfo> loginExecute() {
		AsyncCallback<LoginInfo> asynCallback = new AsyncCallback<LoginInfo>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(LoginInfo result) {

				if (result.isLoggedIn()) {
					loginInfo = result;
					admin.pruefeObNutzerNeu(result.getEmailAddress(),
							pruefeObNutzerNeuExecute(result
									.getEmailAddress()));


				} else {
					
					Window.Location.replace(result.getLoginUrl());
				}
			}
		};

		return asynCallback;
	}
	

	

	
	/**
	 * AsyncCallback für die Methode pruefeObNutzerNeu(). Falls der Wert false ist wird die Methode
	 * getNutzerByEmail() aufgerufen, sonst wird der Nutzer auf ErstelleNutzer() weitergeleitet.
	 * 
	 * @return
	 */
	private AsyncCallback<Boolean> pruefeObNutzerNeuExecute(String email) {
		AsyncCallback<Boolean> asynCallback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(Boolean result) {

				if (!result) {
			
					admin.getNutzerByEmail(loginInfo.getEmailAddress(),
							getNutzerByEmailExecute(loginInfo.getEmailAddress()));
					
					RootPanel.get("Details").add(begruessenN);
					RootPanel.get("Details").add(begruessenN2);
					Window.alert("Test");
					
				} else {
					
					Showcase showcase = new ErstelleNutzer();
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(showcase);
					
				}

			}
		};

		return asynCallback;
	}

	/**
	 * Gibt den aktuell-eingeloggten Nutzer zurueck
	 * 
	 * @return Nutzer
	 */
	public static Nutzer getNp() {
		return np;
	}

	/**
	 * Gibt die LoginInfos des aktuell-eingeloggten Nutzers zurueck
	 * 
	 * @return loginInfo LoginInfo
	 */
	public static LoginInfo getLoginInfo() {
		return loginInfo;
	}
	
	

	
	/**
	 * AsyncCallback für die Methode getNutzerByEmail(). Wenn ein Nutzer zurueckgeliefert wird,
	 * wird die Methode getMenu() aufgerufen und der zurueckgelieferte Nutzer in die Variable np 
	 * gespeichert.
	 * @return
	 */
	private AsyncCallback<Nutzer> getNutzerByEmailExecute(
			String email) {
		AsyncCallback<Nutzer> asynCallback = new AsyncCallback<Nutzer>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Nutzer result) {
				
				np = result;
				

			}
		};
		return asynCallback;
		
	
	}
	/**
	 * Methode legt die CSS-Styles fÃ¼r verschiedene Labels fest.
	 */
	private void setStyles() {
		begruessenN.setStyleName("welcomelabel");
		begruessenN2.setStyleName("welcomelabel2");

	}
}

