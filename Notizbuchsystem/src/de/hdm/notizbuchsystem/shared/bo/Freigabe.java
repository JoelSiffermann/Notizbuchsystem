package de.hdm.notizbuchsystem.shared.bo;

public class Freigabe extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	private int freigabeId;

	private boolean leseberechtigung;
	
	private boolean aendersungsberechtigung;
	
	private boolean loeschberechtigung;
	
	//Hier brauchen wir die Eintragung, da die Beziehung zu Freigabe und Nutzer in einer Zwischentabelle ausgedrückt wird
	private int freigegebeneEintragung;
	
	private String freigebenderNutzer;
	
	private String freigegebenerNutzer;

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
	
//	gaändert zu freigegebeneEintragung!
	
	public void setFreigegebeneEintragung(int freigegebeneEintragung){
		this.freigegebeneEintragung = freigegebeneEintragung;
	}
	
	public int getFreigegebeneEintragung(){
		return this.freigegebeneEintragung;
	}

	public int getFreigabeId() {
		return freigabeId;
	}

	public void setFreigabeId(int freigabeId) {
		this.freigabeId = freigabeId;
	}

	public String getFreigebenderNutzer() {
		return freigebenderNutzer;
	}

	public void setFreigebenderNutzer(String freigebenderNutzer) {
		this.freigebenderNutzer = freigebenderNutzer;
	}

	public String getFreigegebenerNutzer() {
		return freigegebenerNutzer;
	}

	public void setFreigegebenerNutzer(String freigegebenerNutzer) {
		this.freigegebenerNutzer = freigegebenerNutzer;
	}
}
