package de.hdm.notizbuchsystem.shared.bo;

public class NotizbuchFreigabe extends Freigabe{

	private static final long serialVersionUID = -8606786542957547644L;
	
	private int notizbuchId;
	private int nutzerId;
	private Freigabe freigabe;
	private int notizbuchfreigabeId;
	private Nutzer nutzer;
	private Notizbuch notizbuch;
	
	
	
	public void setNotizbuchId(int notizbuchId){
		this.notizbuchId = notizbuchId;
	}
	
	public int getNotizbuchId(){
		return this.notizbuchId;
	}

	public int getNutzerId() {
		return nutzerId;
	}

	public void setNutzerId(int nutzerId) {
		this.nutzerId = nutzerId;
	}

	public Freigabe getFreigabe() {
		return freigabe;
	}

	public void setFreigabe(Freigabe freigabe) {
		this.freigabe = freigabe;
	}

	public int getNotizbuchfreigabeId() {
		return notizbuchfreigabeId;
	}

	public void setNotizbuchfreigabeId(int notizbuchfreigabeId) {
		this.notizbuchfreigabeId = notizbuchfreigabeId;
	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public Notizbuch getNotizbuch() {
		return notizbuch;
	}

	public void setNotizbuch(Notizbuch notizbuch) {
		this.notizbuch = notizbuch;
	}

}
