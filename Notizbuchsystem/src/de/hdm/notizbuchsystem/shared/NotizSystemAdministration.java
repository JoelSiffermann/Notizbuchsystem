package de.hdm.notizbuchsystem.shared;


import java.util.Collection;
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
	
	public Nutzer getNutzerByEMail(String email)  throws IllegalArgumentException;

	
//	public void login() throws IllegalArgumentException;
//	
//	public void logout() throws IllegalArgumentException;
	


	
	
	public Notiz erstelleNotiz(String titel, String subtitel, String inhalt, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException;

	Notizbuch erstelleNotizbuch(String titel,
			String eigentuemer, Date erstelldatum,
			Date modifikationsdatum);
	
	public Nutzer erstelleNutzer(String name, String vorname, String emailAddress) throws IllegalArgumentException;
	
	public Notizquelle erstelleNotizquelle(int notizquelleId, String url) throws IllegalArgumentException;
	
	public Faelligkeit erstelleFaelligkeit(int EintragungID, Date datum) throws IllegalArgumentException;
	
	public void loescheNotiz(int notizid, String nutzer) throws IllegalArgumentException;
	
	public void loescheNotizbuch(int notizbuchid, String nutzer) throws IllegalArgumentException;
	
	public void loescheNutzer(String email) throws IllegalArgumentException;
	
	public void loescheNotizquelle(Notizquelle notizquelle, int notizquelleid) throws IllegalArgumentException;
	
	public void loescheFaelligkeit(int notizId, String Nutzer) throws IllegalArgumentException;
	
	public void loescheFreigabe(String nutzer, int eintragungId) throws IllegalArgumentException;
	
	public Notiz bearbeiteNotiz(int id, String nutzer, String titel, String subtitel, String inhalt, Date modifikationsdatum) throws IllegalArgumentException;
	
	public Notizbuch bearbeiteNotizbuch(int id, String nutzer, String titel, Date modifikationsdatum) throws IllegalArgumentException;
	
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException;
	
	public Nutzer bearbeiteNutzer(String email, String name, String vorname) throws IllegalArgumentException;
	
	public Freigabe bearbeiteNotizFreigabe(boolean lb, boolean ab, boolean lob, String nutzer) throws IllegalArgumentException;

	public Freigabe bearbeiteNotizbuchFreigabe(boolean lb, boolean ab, boolean lob, String nutzer) throws IllegalArgumentException;

	public void zuweisungNotiz(int notizbuch, int notiz, String nutzer) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByFaelligkeit(Date fdatum) throws IllegalArgumentException;
	
	public Notiz getNotizByNutzer(Nutzer nutzer) throws IllegalArgumentException;
	
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException;

	
	
	public Vector<Nutzer> getNutzerByName(String name, String vorname) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByTitel(String titel) throws IllegalArgumentException;
	
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException;
	
	public Vector<Faelligkeit> getFaelligkeitByDatum(Date datum) throws IllegalArgumentException;
	
	public Vector<Freigabe> getFreigabeByEintragung(int id) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByEDatum(Date erstelldatum) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByMDatum(Date modifikationsdatum) throws IllegalArgumentException;
	
	public Vector<Notiz> getNotizByNotizbuch(int notizbuchId, String nutzer) throws IllegalArgumentException;

	public Faelligkeit bearbeiteFaelligkeit(Date datum, int notizId, String nutzer)
			throws IllegalArgumentException;
	
	Vector<Notiz> getNotizenByNutzer(String email)
			throws IllegalArgumentException;
	
	Vector<Notizbuch> getNotizbuecherByNutzer(String email)
			throws IllegalArgumentException;
	
	
	Vector<Notiz> getNotizen() throws IllegalArgumentException;
	
	Map<Vector<Notiz>, Vector<Faelligkeit>> getNotizByKriterium(String titel, Date edatum, Date mdatum,
			Date fdatum) throws IllegalArgumentException;
	
	Map<Vector<Notiz>, Vector<Freigabe>> getNotizByNutzerUndFreigabe(Nutzer n,
			Freigabe f) throws IllegalArgumentException;
	
	Freigabe erstelleNotizbuchFreigabe(int eintragungsid, boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			String email, String freigegebenerNutzer)
			throws IllegalArgumentException;
	
	Freigabe erstelleNotizFreigabe(int eintragungsid, boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			String email, String freigegebenerNutzer)
			throws IllegalArgumentException;
	
	Notiz getNotizbyID(int id) throws IllegalArgumentException;
	
	Collection<String> getStringforSuggestBox() throws IllegalArgumentException;
	
	Freigabe getFreigabe(String email) throws IllegalArgumentException;
	
	Vector<Freigabe> getBerechtigungByNutzer(String email)
			throws IllegalArgumentException;
	Vector<Nutzer> getAllNutzer() throws IllegalArgumentException;
	
	Notizbuch getNotizbuchbyID(int id) throws IllegalArgumentException;
	
	
}


