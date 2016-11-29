package de.hdm.notizbuchsystem.shared.bo;

public abstract class Freigabe extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private boolean leseberechtigung;
	
	private boolean aendersungsberechtigung;
	
	private boolean loeschberechtigung;
	
	//Hier brauchen wir die Eintragung, da die Beziehung zu Freigabe und Nutzer in einer Zwischentabelle ausgedr�ckt wird
	private int freigegebeneEintragung;

	public Freigabe(){
		
	}
	
	public void setLeseberechtigung(boolean leseberechtigung){
		this.leseberechtigung = leseberechtigung;
	}
	
	public boolean getLeseberechtigung(){
		return this.leseberechtigung;
	}
	
	public void setAenderungsberechtigung(boolean aenderungsberechtigung){
		this.aendersungsberechtigung = aenderungsberechtigung;
	}
	
	public boolean getAenderungsberechtigung(){
		return this.aendersungsberechtigung;
	}
	
	public void setLoeschberechtigung(boolean loeschberechtigung){
		this.loeschberechtigung = loeschberechtigung;
	}
	
	public boolean getLoeschberechtigung(){
		return this.loeschberechtigung;
	}
	
//	ga�ndert zu freigegebeneEintragung!
	
	public void setFreigegebeneEintragung(int freigegebeneEintragung){
		this.freigegebeneEintragung = freigegebeneEintragung;
	}
	
	public int getFreigegebeneEintragung(){
		return this.freigegebeneEintragung;
	}
}
