package de.hdm.notizbuchsystem.shared.bo;

import java.sql.Timestamp;
import java.util.Date;

public abstract class Eintragung extends BusinessObject {

	private static final long serialVersionUID = 1073264159607447080L;

	private String titel;

	private Timestamp erstelldatum;

	private Timestamp modifikationsdatum;

	private String eigentuemer;
	
	private int eintragungId;

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setErstelldatum(Timestamp erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	public Date getErstelldatum() {
		return this.erstelldatum;
	}

	public void setEigentuemer(String eigentuemer) {
		this.eigentuemer = eigentuemer;
	}

	public String getEigentuemer() {
		return this.eigentuemer;
	}

	public void setModifikationsdatum(Timestamp modifikationsdatum) {
		this.modifikationsdatum = modifikationsdatum;
	}

	public Date getModifikationsdatum() {
		return this.modifikationsdatum;
	}

	public int getEintragungId() {
		return eintragungId;
	}

	public void setEintragungId(int eintragungId) {
		this.eintragungId = eintragungId;
	}
}
