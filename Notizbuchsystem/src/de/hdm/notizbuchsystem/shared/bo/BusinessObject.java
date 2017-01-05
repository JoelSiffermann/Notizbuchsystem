package de.hdm.notizbuchsystem.shared.bo;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public abstract class BusinessObject implements IsSerializable,Serializable {

	private static final long serialVersionUID = 1601371118492937261L;

	private int id = 0;

	private Date erstellungszeitpunkt;

	public BusinessObject() {
		this.erstellungszeitpunkt = new Date();
	}

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

	public void setErstellungsZeitpunkt(Date erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;

	}

	public Date getErstellungsZeitpunkt() {
		return this.erstellungszeitpunkt;
	}

}
