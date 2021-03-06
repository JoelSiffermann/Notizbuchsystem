package de.hdm.notizbuchsystem.shared.bo;

import java.util.Date;

public class Notiz extends Eintragung{

	private static final long serialVersionUID = 1L;
	
	private int notizId = 0;

	private String subtitel;
	
	private String inhalt;
	
	private Date faelligkeit;
	
//	private Notiz notiz;
	
//	Sollte die Notiz nicht auch noch die Methode Titel haben?	

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
	
	public void setNotizId(int notizId){
		this.notizId = notizId;
	}
	
	public int getNotizId(){
		return notizId;
	}

	public Date getFaelligkeit() {
		return faelligkeit;
	}

	public void setFaelligkeit(Date faelligkeit) {
		this.faelligkeit = faelligkeit;
	}
	
	
}
