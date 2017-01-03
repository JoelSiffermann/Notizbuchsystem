package de.hdm.notizbuchsystem.server;

import java.util.Date;
import java.util.Vector;

import de.hdm.notizbuchsystem.server.db.*;
import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.*;

public class NotizbuchAdministrationImpl implements NotizSystemAdministration{
	
	private NutzerMapper nutzerMapper;
	
	private NotizMapper notizMapper;
	
	private NotizbuchMapper notizbuchMapper;
	
	private FaelligkeitMapper faelligkeitMapper;
	
	private NotizquelleMapper notizquelleMapper;
	
	private FreigabeMapper freigabeMapper;
		
	public void login() throws IllegalArgumentException {
		
	}
	
	
	public void logout() throws IllegalArgumentException {
		
	}
	
	@Override
	public void init() throws IllegalArgumentException {
		
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		
		this.notizMapper = NotizMapper.notizMapper();
		
		this.notizbuchMapper = NotizbuchMapper.notizbuchMapper();
		
		this.faelligkeitMapper = FaelligkeitMapper.faelligkeitMapper();
		
		this.notizquelleMapper = NotizquelleMapper.notizquelleMapper();
		
		this.freigabeMapper = FreigabeMapper.freigabeMapper();
		
	}
	
	@Override
	public boolean pruefeObNutzerNeu(String Email) throws IllegalArgumentException {

		if (nutzerMapper.getNutzerByEmail(Email) == null) {
			return true;
		}
		return false;
		
	}
	
	@Override
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
	
	@Override
	public Notizbuch erstelleNotizbuch(String titel, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException {
		
		Notizbuch notizbuch = new Notizbuch();
		
		notizbuch.setTitel(titel);
		
		notizbuch.setEigentuemer(eigentuemer);
		
		notizbuch.setErstelldatum(erstelldatum);
		
		notizbuch.setModifikationsdatum(modifikationsdatum);
	
		return this.notizbuchMapper.erstellen(notizbuch);
		
	}
	
	@Override
	public Nutzer erstelleNutzer(String name, String vorname, String email) throws IllegalArgumentException{
		
		Nutzer nutzer = new Nutzer();
		
		nutzer.setName(name);
		
		nutzer.setVorname(vorname);
		
		nutzer.setEmail(email);
		
		nutzer.setId(1);
		
		return this.nutzerMapper.erstellen(nutzer);
		
	}

	@Override
	public Notizquelle erstelleNotizquelle(String url) throws IllegalArgumentException{
		
		Notizquelle notizquelle = new Notizquelle();
		
		notizquelle.setUrl(url);
		
		return this.notizquelleMapper.erstellen(notizquelle);
		
	}
	
	@Override
	public Faelligkeit erstelleFaelligkeit(Date datum) throws IllegalArgumentException{
		
		Faelligkeit faelligkeit = new Faelligkeit();
		
		faelligkeit.setDatum(datum);
		
		return this.faelligkeitMapper.erstellen(faelligkeit);
		
	}
	
	@Override
	public Freigabe erstelleNotizfreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException{
	
		
		Freigabe freigabeNotiz = new NotizFreigabe();
		
		freigabeNotiz.setLeseberechtigung(leseberechtigung);
		
		freigabeNotiz.setAenderungsberechtigung(aenderungsberechtigung);
		
		freigabeNotiz.setLoeschberechtigung(loeschberechtigung);
		
		
		return this.freigabeMapper.erstellen(freigabeNotiz);
		
	}
	
	@Override
	public Freigabe erstelleNotizbuchFreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException{
		
		Freigabe freigabeNotizbuch = new NotizbuchFreigabe();
		
		freigabeNotizbuch.setLeseberechtigung(leseberechtigung);
		
		freigabeNotizbuch.setAenderungsberechtigung(aenderungsberechtigung);
		
		freigabeNotizbuch.setLoeschberechtigung(loeschberechtigung);
		
		
		return this.freigabeMapper.erstellen(freigabeNotizbuch);
		
	}
	
	@Override
	public Notiz bearbeiteNotiz(Notiz notiz, Eintragung eintragung) throws IllegalArgumentException{
		
		return this.notizMapper.bearbeiten(notiz);
		
	}
	
	@Override
	public Notizbuch bearbeiteNotizbuch(Notizbuch notizbuch, Eintragung eintragung) throws IllegalArgumentException{
		
		return this.notizbuchMapper.bearbeiten(notizbuch);
				
	}
	
	@Override
	public Nutzer bearbeiteNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
		return this.nutzerMapper.bearbeiten(nutzer);
		
	}
	
	@Override
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException{
		
		return this.notizquelleMapper.bearbeiten(notizquelle);
		
	}
	
	@Override
	public Faelligkeit bearbeiteFaelligkeit(Faelligkeit faelligkeit, Date datum) throws IllegalArgumentException {
		
		faelligkeit.setDatum(datum);
				
		return this.faelligkeitMapper.bearbeiten(faelligkeit);
		
	}
	
	@Override
	public Freigabe bearbeiteNotizFreigabe(Freigabe notizFreigabe) throws IllegalArgumentException{
		
		return this.freigabeMapper.bearbeiten(notizFreigabe);
		
	}
	
	@Override
	public Freigabe bearbeiteNotizbuchFreigabe(Freigabe notizbuchFreigabe) throws IllegalArgumentException{
		
		return this.freigabeMapper.bearbeiten(notizbuchFreigabe);
	}
	
	@Override
	public void loescheNotiz(Notiz notiz, Eintragung eintragung) throws IllegalArgumentException {
		
		this.notizMapper.loeschen(notiz);
		
	}
	
	@Override
	public void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung) throws IllegalArgumentException{
		
		this.notizbuchMapper.loeschen(notizbuch);

	}
	
	@Override
	public void loescheNutzer(Nutzer nutzer) throws IllegalArgumentException{
		
		this.nutzerMapper.loeschen(nutzer);
		
	}
	
	@Override
	public void loescheNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException{
		
		this.notizquelleMapper.loeschen(notizquelle);

		
	}
	
	@Override
	public void loescheFaelligkeit(Faelligkeit faelligkeit) throws IllegalArgumentException{
		
		this.faelligkeitMapper.loeschen(faelligkeit);
		
	}
	
	@Override
	public void loescheFreigabe(Freigabe freigabe) throws IllegalArgumentException{
		
		this.freigabeMapper.loeschen(freigabe);
		
	}
	
	@Override
	public Notiz zuweisungNotiz(Notizbuch notizbuch, Vector<Notiz> notiz) throws IllegalArgumentException{
		
		notizbuch.setEnthalteneNotiz(notiz);
		
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


	@Override
	public void speichereNutzer(int profilId, String vorname, String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Freigabe erstelleNotizFreigabe(boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			String email) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Faelligkeit bearbeiteFaelligkeit(Date faelligkeit)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Notiz getNotizByFaelligkeit(Faelligkeit faelligkeit)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Notiz getNotizByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Freigabe getBerechtigungByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Nutzer getNutzerByEmail(String email)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Vector<Notiz> getNotizByEDatum(Date erstelldatum)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Vector<Notiz> getNotizByMDatum(Date modifikationsdatum)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
