package de.hdm.notizbuchsystem.shared.bo;

import java.util.Date;

public abstract class Eintragung extends BusinessObject {

	private static final long serialVersionUID = 1073264159607447080L;

	private String titel;

	private Date erstelldatum;

	private Date modifikationsdatum;

	private Nutzer eigentuemer;

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setErstelldatum(Date erstelldatum) {
		this.erstelldatum = erstelldatum;
	}

	public Date getErstelldatum() {
		return this.erstelldatum;
	}

	public void setEigentuemer(Nutzer eigentuemer) {
		this.eigentuemer = eigentuemer;
	}

	public Nutzer getEigentuemer() {
		return this.eigentuemer;
	}

	public void setModifikationsdatum(Date modifikationsdatum) {
		this.modifikationsdatum = modifikationsdatum;
	}

	public Date getModifikationsdatum() {
		return this.modifikationsdatum;
	}
}
