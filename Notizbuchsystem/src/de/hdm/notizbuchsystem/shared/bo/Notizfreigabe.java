package de.hdm.notizbuchsystem.shared.bo;

public class Notizfreigabe extends Freigabe{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2798496734712191963L;

	private int notizId;
	
	public void setNotizId(int notizId){
		this.notizId = notizId;
	}
	
	public int getNotizId(){
		return this.notizId;
	}
}
