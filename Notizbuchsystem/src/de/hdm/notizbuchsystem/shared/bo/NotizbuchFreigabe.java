package de.hdm.notizbuchsystem.shared.bo;

public class NotizbuchFreigabe extends Freigabe{

	private static final long serialVersionUID = -8606786542957547644L;
	
	private int notizbuchId;
	
	public void setNotizbuchId(int notizbuchId){
		this.notizbuchId = notizbuchId;
	}
	
	public int getNotizbuchId(){
		return this.notizbuchId;
	}

}
