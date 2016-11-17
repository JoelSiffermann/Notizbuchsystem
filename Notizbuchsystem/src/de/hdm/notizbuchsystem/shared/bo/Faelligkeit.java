package de.hdm.notizbuchsystem.shared.bo;

import java.util.Date;

public class Faelligkeit extends BusinessObject {

	private static final long serialVersionUID = 4558164114659368865L;

	private Date datum;

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getDatum() {
		return this.datum;
	}
}
