package de.hdm.notizbuchsystem.shared.bo;

public class NotizFreigabe extends Freigabe{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798496734712191963L;

	private int notizFreigabeId = 0;
	private Freigabe freigabe;
	private int notizId = 0;
	private int nutzerId;
	public Notiz notiz;
	public Nutzer nutzer;
	
	public void setNotizId(int notizId){
		this.notizId = notizId;
	}
	
	public int getNotizId(){
		return this.notizId;
	}

	public int getNotizFreigabeId() {
		return notizFreigabeId;
	}

	public void setNotizFreigabeId(int notizFreigabeId) {
		this.notizFreigabeId = notizFreigabeId;
	}

	public Freigabe getFreigabe() {
		return freigabe;
	}

	public void setFreigabe(Freigabe freigabe) {
		this.freigabe = freigabe;
	}

	public int getNutzerId() {
		return nutzerId;
	}

	public void setUserId(int nutzerId) {
		this.nutzerId = nutzerId;
	}
}
