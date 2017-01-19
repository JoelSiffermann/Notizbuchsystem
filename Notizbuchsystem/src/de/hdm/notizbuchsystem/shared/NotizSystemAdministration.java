package de.hdm.notizbuchsystem.shared;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;



import de.hdm.notizbuchsystem.shared.bo.*;

@RemoteServiceRelativePath("admin")
public interface NotizSystemAdministration extends RemoteService {
	
	/**
	 * @see de.hdm.notizbuchsystem.server.NotizSystemAdministrationImpl#init()
	 */
	public void init() throws IllegalArgumentException;
	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Nutzer
	 * *************************************************************************
	 * **
	 */
	public boolean pruefeObNutzerNeu(String userEmail) throws IllegalArgumentException;
	
	public Nutzer getNutzerByEmail (String email)  throws IllegalArgumentException;

	
//	public void login() throws IllegalArgumentException;
//	
//	public void logout() throws IllegalArgumentException;
	


	
	public void speicherNutzer(int NutzerId, String vorname, String name) throws IllegalArgumentException;
	
	public Notiz erstelleNotiz(int notizId, String titel, String subtitel, String inhalt, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException;

	public Notizbuch erstelleNotizbuch(int notizbuchID, String titel, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException;
	
	public Nutzer erstelleNutzer(String name, String vorname, String emailAddress) throws IllegalArgumentException;
	
	public Notizquelle erstelleNotizquelle(int notizquelleId, String url) throws IllegalArgumentException;
	
	public Faelligkeit erstelleFaelligkeit(int faelligkeitId, Date datum) throws IllegalArgumentException;
	
	public Freigabe erstelleNotizFreigabe(int notizfreigabeId, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException;
	
	public Freigabe erstelleNotizbuchFreigabe(int notizbuchFreigabeId, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException;
	
	public void loescheNotiz(Notiz notiz, Eintragung eintragung, int notizid) throws IllegalArgumentException;
	
	public void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung, int notizbuchid) throws IllegalArgumentException;
	
	public void loescheNutzer(int Nutzerid) throws IllegalArgumentException;
	
	public void loescheNotizquelle(Notizquelle notizquelle, int notizquelleid) throws IllegalArgumentException;
	
	public void loescheFaelligkeit(Faelligkeit faelligkeit, int faelligkeitid) throws IllegalArgumentException;
	
	public void loescheFreigabe(Freigabe freigabe, int freigabeId) throws IllegalArgumentException;
	
	public Notiz bearbeiteNotiz(Notiz notiz, Eintragung eintragung) throws IllegalArgumentException;
	
	public Notizbuch bearbeiteNotizbuch(Notizbuch notizbuch, Eintragung eintragung) throws IllegalArgumentException;
	
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException;
	
	public Nutzer bearbeiteNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Freigabe bearbeiteNotizFreigabe(Freigabe notizfreigabe) throws IllegalArgumentException;

	public Freigabe bearbeiteNotizbuchFreigabe(Freigabe notizbuchfreigabe) throws IllegalArgumentException;

	public Notiz zuweisungNotiz(Notizbuch notizbuch, Vector<Notiz> notiz) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByFaelligkeit(Date fdatum) throws IllegalArgumentException;
	
	public Notiz getNotizByNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Freigabe getBerechtigungByNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException;

	public Nutzer getNutzerById(int nutzerId) throws IllegalArgumentException;
	
	
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
	
	Vector<Notiz> getNotizenByNutzer(Nutzer nutzer)
			throws IllegalArgumentException;
	
	Vector<Notiz> getNotizen() throws IllegalArgumentException;
	
	Map<Vector<Notiz>, Vector<Faelligkeit>> getNotizByKriterium(String titel, Date edatum, Date mdatum,
			Date fdatum) throws IllegalArgumentException;
	
	
}


