package de.hdm.notizbuchsystem.shared.bo;

public class Notiz extends Eintragung{

	private static final long serialVersionUID = 6845416990934452844L;

	private String subtitel;
	
	private String inhalt;
	
	public void setSubtitel(String subtitel){
		this.subtitel = subtitel;
	}
	
	public String getSubtitel(){
		return this.subtitel;
	}
	
	public void setInhalt(String inhalt){
		this.inhalt = inhalt;
	}
	
	public String getInhalt(){
		return this.inhalt;
	}
}
