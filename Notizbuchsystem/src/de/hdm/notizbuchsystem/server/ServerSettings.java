package de.hdm.notizbuchsystem.server;

import java.util.logging.Logger;

import de.hdm.notizbuchsystem.shared.CommonSettings;

public class ServerSettings extends CommonSettings {
	
	private static final String Logger_Name = "NotizbuchSystem Server";

	private static final Logger log = Logger.getLogger(Logger_Name);
	
	public ServerSettings(){
		
	}
	
	public static Logger getLogger(){
		return log;
		
	}
	
	

}
