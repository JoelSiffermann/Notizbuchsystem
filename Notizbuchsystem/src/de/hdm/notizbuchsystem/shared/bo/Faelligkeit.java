package de.hdm.notizbuchsystem.shared.bo;


import java.util.Date;


public class Faelligkeit extends BusinessObject {

	private static final long serialVersionUID = 4558164114659368865L;

	private Date datum;
	
	private int notiz;
	
	private int faelligkeitId;


	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	public Date getDatum() {
		return this.datum;
	}

	public int getFaelligkeitId() {
		return faelligkeitId;
	}

	public void setFaelligkeitId(int faelligkeitId) {
		this.faelligkeitId = faelligkeitId;
	}

	public int getNotiz() {
		return notiz;
	}

	public void setNotiz(int notiz) {
		this.notiz = notiz;
	}

	


}
