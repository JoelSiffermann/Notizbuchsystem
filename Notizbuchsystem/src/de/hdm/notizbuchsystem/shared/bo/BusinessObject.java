package de.hdm.notizbuchsystem.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;




public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id = 0;

	protected Timestamp erstellungszeitpunkt = new Timestamp(System.currentTimeMillis());

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusinessObject other = (BusinessObject) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {

		return this.getClass().getName() + " #" + this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	public Timestamp getErstellungszeitpunkt(){
		return this.erstellungszeitpunkt;
	}

	public void setErstellungsZeitpunkt(Timestamp erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;

	}



}
