package de.hdm.notizbuchsystem.shared.bo;


import java.util.Date;


public class Faelligkeit extends BusinessObject {

	private static final long serialVersionUID = 4558164114659368865L;

	private Date datum;
	
	private int faelligkeitId;

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getFaelligkeitId() {
		return faelligkeitId;
	}

	public void setFaelligkeitId(int faelligkeitId) {
		this.faelligkeitId = faelligkeitId;
	}

	


}
