package de.hdm.notizbuchsystem.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.notizbuchsystem.shared.bo.*;

@RemoteServiceRelativePath("NotizSystem")
public interface NotizSystemAdministration {
	
//	public void login() throws IllegalArgumentException;
//	
//	public void logout() throws IllegalArgumentException;
	
	public boolean pruefeObNutzerNeu(String userEmail) throws IllegalArgumentException;
	
	public void init() throws IllegalArgumentException;
	
	public void speichereNutzer(int profilId, String vorname, String name) throws IllegalArgumentException;
	
	public Notiz erstelleNotiz(String titel, String subtitel, String inhalt, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException;

	public Notizbuch erstelleNotizbuch(String titel, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException;
	
	public Nutzer erstelleNutzer(String name, String vorname, String email) throws IllegalArgumentException;
	
	public Notizquelle erstelleNotizquelle(String url) throws IllegalArgumentException;
	
	public Faelligkeit erstelleFaelligkeit(Date datum) throws IllegalArgumentException;
	
	public Freigabe erstelleNotizFreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung, String email) throws IllegalArgumentException;
	
	public Freigabe erstelleNotizbuchFreigabe(boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException;
	
	public void loescheNotiz(Notiz notiz, Eintragung eintragung) throws IllegalArgumentException;
	
	public void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung) throws IllegalArgumentException;
	
	public void loescheNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public void loescheNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException;
	
	public void loescheFaelligkeit(Faelligkeit faelligkeit) throws IllegalArgumentException;
	
	public void loescheFreigabe(Freigabe freigabe) throws IllegalArgumentException;
	
	public Notiz bearbeiteNotiz(Notiz notiz, Eintragung eintragung) throws IllegalArgumentException;
	
	public Notizbuch bearbeiteNotizbuch(Notizbuch notizbuch, Eintragung eintragung) throws IllegalArgumentException;
	
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException;
	
	public Faelligkeit bearbeiteFaelligkeit(Date faelligkeit) throws IllegalArgumentException;
	
	public Nutzer bearbeiteNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Freigabe bearbeiteNotizFreigabe(Freigabe notizfreigabe) throws IllegalArgumentException;

	public Freigabe bearbeiteNotizbuchFreigabe(Freigabe notizbuchfreigabe) throws IllegalArgumentException;

	public Notiz zuweisungNotiz(Notizbuch notizbuch, Vector<Notiz> notiz) throws IllegalArgumentException;
	
	public Notiz getNotizByFaelligkeit(Faelligkeit faelligkeit) throws IllegalArgumentException;
	
	public Notiz getNotizByNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Freigabe getBerechtigungByNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException;
	
	public Nutzer getNutzerByEmail(String email) throws IllegalArgumentException;
	
	public Vector<Nutzer> getNutzerByName(String name, String vorname) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByTitel(String titel) throws IllegalArgumentException;
	
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException;
	
	public Vector<Faelligkeit> getFaelligkeitByDatum(Date datum) throws IllegalArgumentException;
	
	public Vector<Freigabe> getFreigabeByNotiz(Notiz notiz) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByEDatum(Date erstelldatum) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByMDatum(Date modifikationsdatum) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByNotizbuch(String titel) throws IllegalArgumentException;

	public Faelligkeit bearbeiteFaelligkeit(Faelligkeit faelligkeit, Date datum)
			throws IllegalArgumentException;

	public Freigabe erstelleNotizfreigabe(boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung
			) throws IllegalArgumentException;

	
}


