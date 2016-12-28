package de.hdm.notizbuchsystem.server;

import java.util.logging.Logger;

import de.hdm.notizbuchsystem.shared.CommonSettings;

public class ServersideSettings extends CommonSettings {
	
	private static final String Logger_Name = "NotizbuchSystem Server";

	private static final Logger log = Logger.getLogger(Logger_Name);
	
	public ServersideSettings(){
		
	}
	
	public static Logger getLogger(){
		return log;
		
	}
	
	

}
