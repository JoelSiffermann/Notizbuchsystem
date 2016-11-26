package de.hdm.notizbuchsystem.server;

import java.util.Date;

import java.util.Vector;

import de.hdm.notizbuchsystem.server.db.*;

import de.hdm.notizbuchsystem.shared.bo.*;

import de.hdm.notizbuchsystem.shared.*;

public class NotizbuchAdministrationImpl {
	
	private NutzerMapper nutzerMapper;
	
	private NotizMapper notizMapper;
	
	private NotizbuchMapper notizbuchMapper;
	
	private FaelligkeitMapper faelligkeitMapper;
	
	private NotizquelleMapper notizquelleMapper;
	
	private FreigabeMapper freigabeMapper;
	
//	private EintragungMapper eintragungMapper;
	
	public void login() throws IllegalArgumentException {
		
	}
	
	public void logout() throws IllegalArgumentException {
		
	}
	
	public void init() throws IllegalArgumentException {
		
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		
		this.notizMapper = NotizMapper.notizMapper();
		
		this.notizbuchMapper = NotizbuchMapper.notizbuchMapper();
		
		this.faelligkeitMapper = FaelligkeitMapper.faelligkeitMapper();
		
		this.notizquelleMapper = NotizquelleMapper.notizquelleMapper();
		
		this.freigabeMapper = FreigabeMapper.freigabeMapper();
		
//		this.eintragungMapper = EintragungMapper.eintragungMapper();
				
	}
	
	public Notiz erstelleNotiz(String titel, String subtitel, String inhalt, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException{
		
		Notiz notiz = new Notiz();
		
		notiz.setTitel(titel);
		
		notiz.setSubtitel(subtitel);
		
		notiz.setInhalt(inhalt);
		
		notiz.setEigentuemer(eigentuemer);
		
		notiz.setErstelldatum(erstelldatum);
		
		notiz.setModifikationsdatum(modifikationsdatum);
	
		return this.notizMapper.erstellen(notiz);
						 
	}
	
	public Notizbuch erstelleNotizbuch(String titel, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException {
		
		Notizbuch notizbuch = new Notizbuch();
		
		notizbuch.setTitel(titel);
		
		notizbuch.setEigentuemer(eigentuemer);
		
		notizbuch.setErstelldatum(erstelldatum);
		
		notizbuch.setModifikationsdatum(modifikationsdatum);
	
		return this.notizbuchMapper.erstellen(notizbuch);
		
	}
	
	public Nutzer erstelleNutzer(String name, String vorname, String email) throws IllegalArgumentException{
		
		Nutzer nutzer = new Nutzer();
		
		nutzer.setName(name);
		
		nutzer.setVorname(vorname);
		
		nutzer.setEmail(email);
		
		nutzer.setId(1);
		
		return this.nutzerMapper.erstellen(nutzer);
		
	}

	public Notizquelle erstelleNotizquelle(String url) throws IllegalArgumentException{
		
		Notizquelle notizquelle = new Notizquelle();
		
		notizquelle.setUrl(url);
		
		return this.notizquelleMapper.erstellen(notizquelle);
		
	}
	
//	Im Klassendiagramm steht Faelligkeit faelligkeit, sollen wir es lieber bei Date datum lassen?
	
	public Faelligkeit erstelleFaelligkeit(Date datum) throws IllegalArgumentException{
		
		Faelligkeit faelligkeit = new Faelligkeit();
		
		faelligkeit.setDatum(datum);
		
		return this.faelligkeitMapper.erstellen(faelligkeit);
		
	}
	
	public Freigabe erstelleNotizfreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung, String email) throws IllegalArgumentException{
		
// 	Ist new Notizfreigabe() richtig?		
		
		Freigabe freigabeNotiz = new NotizFreigabe();
		
		freigabeNotiz.setLeseberechtigung(leseberechtigung);
		
		freigabeNotiz.setAenderungsberechtigung(aenderungsberechtigung);
		
		freigabeNotiz.setLoeschberechtigung(loeschberechtigung);
		
		freigabeNotiz.setFreigegebeneEmail(email);
		
		return this.freigabeMapper.erstellen(freigabeNotiz);
		
	}
	
	public Freigabe erstelleNotizbuchFreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung, String email) throws IllegalArgumentException{
		
		Freigabe freigabeNotizbuch = new NotizbuchFreigabe();
		
		freigabeNotizbuch.setLeseberechtigung(leseberechtigung);
		
		freigabeNotizbuch.setAenderungsberechtigung(aenderungsberechtigung);
		
		freigabeNotizbuch.setLoeschberechtigung(loeschberechtigung);
		
		freigabeNotizbuch.setFreigegebeneEmail(email);
		
		return this.freigabeMapper.erstellen(freigabeNotizbuch);
		
	}
	
//	fehlt in der Klasse NotizMapper nicht noch im Methoden-Parameter Eintragung eintragung? oder fehlt es in der Klasse Notiz/Eintragung irgendwelche Methoden?
	
	public Notiz bearbeiteNotiz(Notiz notiz) throws IllegalArgumentException{
		
		return this.notizMapper.bearbeiten(notiz);
		
	}
	
//	fehlt in der Klasse NotizbuchMapper nicht noch im Methoden-Parameter Eintragung eintragung? der fehlt es in der Klasse Notizbuch/Eintragung irgendwelche Methoden?
	
	public Notizbuch bearbeiteNotizbuch(Notizbuch notizbuch) throws IllegalArgumentException{
		
		return this.notizbuchMapper.bearbeiten(notizbuch);
				
	}
	
	public Nutzer bearbeiteNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
		return this.nutzerMapper.bearbeiten(nutzer);
		
	}
		
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException{
		
		return this.notizquelleMapper.bearbeiten(notizquelle);
		
	}
	
	public Faelligkeit bearbeiteFaelligkeit(Faelligkeit faelligkeit, Date datum) throws IllegalArgumentException {
		
		faelligkeit.setDatum(datum);
				
		return this.faelligkeitMapper.bearbeiten(faelligkeit);
		
	}
	
	public Freigabe bearbeiteNotizFreigabe(Freigabe notizFreigabe) throws IllegalArgumentException{
		
		return this.freigabeMapper.bearbeiten(notizFreigabe);
		
	}
	
	public Freigabe bearbeiteNotizbuchFreigabe(Freigabe notizbuchFreigabe) throws IllegalArgumentException{
		
		return this.freigabeMapper.bearbeiten(notizbuchFreigabe);
	}
	
	public void loescheNotiz(Notiz notiz) throws IllegalArgumentException {
		
		this.notizMapper.loeschen(notiz);
		
	}
	
	public void loescheNotizbuch(Notizbuch notizbuch) throws IllegalArgumentException{
		
		this.notizbuchMapper.loeschen(notizbuch);

	}
	
	public void loescheNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
		this.nutzerMapper.loeschen(nutzer);
		
	}
	
	public void loescheNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException{
		
		this.notizquelleMapper.loeschen(notizquelle);

		
	}
	
	public void loescheFaelligkeit(Faelligkeit faelligkeit) throws IllegalArgumentException{
		
		this.faelligkeitMapper.loeschen(faelligkeit);
		
	}
	
	public void loescheFreigabe(Freigabe freigabe) throws IllegalArgumentException{
		
		this.freigabeMapper.loeschen(freigabe);
		
	}
	
	public Notiz zuweisungNotiz(Notizbuch notizbuch) throws IllegalArgumentException{
		
//	was ist die zuweisen-Methode?
		return this.notizMapper.zuweisen(notizbuch);
	}
	
	public Nutzer getNutzerByEMail(String email) throws IllegalArgumentException{
		
		return this.nutzerMapper.getNutzerByEmail(email);
		
	}
	
	public Vector<Nutzer> getNutzerByName(String name, String vorname) throws IllegalArgumentException{
		
		return this.nutzerMapper.getNutzerByName(name, vorname);
		
	}
	
	public Vector<Notiz> getNotizByTitel(String titel) throws IllegalArgumentException{
		
		return this.notizbuchMapper.getNotizByTitel(titel);
		
	}
	
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException{
		
		return this.notizbuchMapper.getNotizbuchByTitel(titel);
		
	}
	
	public Vector<Faelligkeit> getFaelligkeitByDatum(Date datum) throws IllegalArgumentException{
		
		return this.faelligkeitMapper.getFaelligkeitByDatum(datum);
		
	}
	
	public Vector<Freigabe> getFreigabeByNotiz(Notiz notiz) throws IllegalArgumentException{
		
		return this.freigabeMapper.getFreigabeByNotiz(notiz);
		
	}
	
	public Vector<Notiz> getNotizByErstelldatum(Date erstelldatum) throws IllegalArgumentException{
		
		return this.notizMapper.getNotizByErstelldatum(erstelldatum);
		
	}
	
	public Vector<Notiz> getNotizByModifikationsdatum(Date modifikationsdatum) throws IllegalArgumentException{
		
		return this.notizMapper.getNotizByModifikationsdatum(modifikationsdatum);
		
	}
		
	public Vector<Notiz> getNotizByNotizbuch(String titel) throws IllegalArgumentException{
		
		return this.notizMapper.getNotizByNotizbuch(titel);
	}
	
}
