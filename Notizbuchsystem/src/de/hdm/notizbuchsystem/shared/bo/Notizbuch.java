package de.hdm.notizbuchsystem.shared.bo;

import java.util.Vector;

public class Notizbuch extends Eintragung{

	private static final long serialVersionUID = 1L;

	private Vector<Notiz> enthalteneNotiz;
	
	public void setEnthalteneNotiz(Vector<Notiz> notiz){
		
		this.enthalteneNotiz = notiz;
		
	}
	
	public Vector<Notiz> getEnthalteneNotiz(){
		
		return this.enthalteneNotiz;
		
	}
	
	
}
