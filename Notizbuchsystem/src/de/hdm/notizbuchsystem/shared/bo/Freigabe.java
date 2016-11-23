package de.hdm.notizbuchsystem.shared.bo;

public abstract class Freigabe extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private boolean leseberechtigung;
	
	private boolean aendersungsberechtigung;
	
	private boolean loeschberechtigung;
	
	private String freigegebeneEmail;

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
	
//	ist die Variable email nicht besser als die Variable freigegebeneEmail?
	
	public void setFreigegebeneEmail(String freigegebeneEmail){
		this.freigegebeneEmail = freigegebeneEmail;
	}
	
	public String getFreigegebeneEmail(){
		return this.freigegebeneEmail;
	}
}
