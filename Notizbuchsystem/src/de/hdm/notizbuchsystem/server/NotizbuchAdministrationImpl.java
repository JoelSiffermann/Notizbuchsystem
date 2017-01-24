package de.hdm.notizbuchsystem.server;


import java.util.Date;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.notizbuchsystem.server.db.*;
import de.hdm.notizbuchsystem.shared.bo.*;
import de.hdm.notizbuchsystem.shared.*;

/**
 * Implementierungsklasse des Interface NotizSystemAdministration.
 * @see NotizSystemAdministration
 * @see NotizSystemAdministrationAsync
 * @see RemoteServiceServlet
 */

@SuppressWarnings("serial")
public class NotizbuchAdministrationImpl extends RemoteServiceServlet implements NotizSystemAdministration{
	
	private NutzerMapper nutzerMapper = null;
	
	private NotizMapper notizMapper= null;
	
	private NotizbuchMapper notizbuchMapper = null;
	
	private FaelligkeitMapper faelligkeitMapper = null;
	
	private NotizquelleMapper notizquelleMapper = null;
	
	private FreigabeMapper freigabeMapper = null;
	
	/**
	 * No-Argument-Konstruktor, der dazu dient, Client-seitig ein RemoteServiceServlet 
	 * GWT.create(Klassenname.class) zu erzeugen. 
	 * @see #init()
	 * @throws IllegalArgumentException
	 */
	public NotizbuchAdministrationImpl() throws IllegalArgumentException {
	}
	
	/**
	 * Initialisierungsmethode. 
	 * @throws IllegalArgumentException
	 */
	
	@Override
	public void init() throws IllegalArgumentException {
		
		this.nutzerMapper = NutzerMapper.nutzerMapper();
		
		this.notizMapper = NotizMapper.notizMapper();
		
		this.notizbuchMapper = NotizbuchMapper.notizbuchMapper();
		
		this.faelligkeitMapper = FaelligkeitMapper.faelligkeitMapper();
		
		this.notizquelleMapper = NotizquelleMapper.notizquelleMapper();
		
		this.freigabeMapper = FreigabeMapper.freigabeMapper();
		
	}
	
	
	/* 
	 * Gibt das aktuelle Profil anhand der EMail zur�ck
	 * return nutzer
	 */
	@Override
	public Nutzer getNutzerByEmail (String email) {
		Nutzer n = new Nutzer();
		n.setEmail(email);
		return nutzerMapper.getNutzerByEmail(n);

	}

	/*
	 * *************************************************************************
	 * ** ABSCHNITT, Beginn: Nutzerprofil
	 * *************************************************************************
	 * **
	 */
	/**
	 * Pruefen, ob der Nutzer in der Datenbank schon existiert.
	 * 
	 * @see de.hdm.notizbuchsystem.shared.NotizSystemAdministration#login(String)
	 */
	
	
	@Override
	public boolean pruefeObNutzerNeu(String userEmail) throws IllegalArgumentException {

		if (nutzerMapper.findByNutzerMitEmail(userEmail) == null) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Ein Nutzer-Objekt anlegen.
	 * @param vorname Vorname.
	 * @param nach Name.
	 * @param emailAddress E-Mail.
	 * @return Das angelegte Nutzerprofil-Objekt.
	 * @throws IllegalArgumentException
	 */
	
	@Override
	public Nutzer erstelleNutzer(String name, String vorname, String emailAddress) throws IllegalArgumentException{
		
		Nutzer nutzer = new Nutzer();
		
		nutzer.setName(name);
		
		nutzer.setVorname(vorname);
		
		nutzer.setEmail(emailAddress);
		
		nutzer = this.nutzerMapper.erstellen(nutzer);

		return nutzer;
		
	}
	
	/**
	 * Ein Nutzer-Objekt aktualisieren.
	 * @param nutzerId Nutzer-ID des Nutzers, das aktualisiert werden soll.
	 * @param vorname Vorname.
	 * @param name Name.
	 * @throws IllegalArgumentException
	 */
//	public void saveNutzer(int nutzerId, String vorname, String name)
//			throws IllegalArgumentException {
//
//		Nutzer nutzer = new Nutzer();
//		nutzer.setVorname(vorname);
//		nutzer.setName(name);
//	
//
//		nutzer.setNutzerId(nutzerId);
//
//		this.nutzerMapper.updateNutzer(nutzer);
//	}

	/**
	 * Ein Nutzer-Objekt loeschen.
	 * @param nutzerId Die Nutzer-ID des Nutzers, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNutzer(String email)
			throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setEmail(email);
		this.nutzerMapper.loeschen(n);
	}
	
	/**
	 * Ein Nutzer-Objekt anhand der Nutzer-ID auslesen.
	 * @param nutzerId Nutzer-ID des Nutzers, das ausgelesen werden soll. 
	 * @return Das ausgelesene Nutzer-Objekt.
	 * @throws IllegalArgumentException
	 */
//	public Nutzer getNutzerById(int nutzerId)
//			throws IllegalArgumentException {
//		return this.nutzerMapper.findByNutzerId(nutzerId);
//	}

		
	/**
	 * Ein Notiz-Objekt anlegen.
	 * @param notizId Notiz-ID der Notiz, das angelegt werden soll. 
	 * @param titel Titel. 
	 * @param subtitel Subtitel. 
	 * @param inhalt Inhalt. 
	 * @param eigentuemer Eigentuemer. 
	 * @param erstelldatum Erstelldatum.
	 * @param modifikationsdatum Modifikationsdatum.  
	 * @return Das angelegte Notiz-Objekt.
	 * @throws IllegalArgumentException
	 */
	
	public Notiz erstelleNotiz(String titel, String subtitel, String inhalt, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException{
		
		Notiz notiz = new Notiz();
		
		notiz.setTitel(titel);
		
		notiz.setSubtitel(subtitel);
		
		notiz.setInhalt(inhalt);
		
		notiz.setEigentuemer(eigentuemer);
		
		notiz.setErstelldatum(erstelldatum);
		
		notiz.setModifikationsdatum(modifikationsdatum);
	
		return this.notizMapper.erstellen(notiz);
						 
	}
	
	/**
	 * Ein Notiz-Objekt loeschen.
	 * @param notizId Die Notiz-ID der Notiz, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotiz(int notizId)
			throws IllegalArgumentException {
		Notiz n = new Notiz();
		n.setId(notizId);
		this.notizMapper.loeschen(n);
	}
	
	/**
	 * Ein Notizbuch-Objekt anlegen.
	 * @param notizbuchId Notizbuch-ID des Notizbuches, das angelegt werden soll. 
	 * @param titel Titel.   
	 * @param eigentuemer Eigentuemer. 
	 * @param erstelldatum Erstelldatum.
	 * @param modifikationsdatum Modifikationsdatum.  
	 * @return Das angelegte Notizbuch-Objekt.
	 * @throws IllegalArgumentException
	 */
	
	public Notizbuch erstelleNotizbuch(String titel, String eigentuemer, Date erstelldatum, Date modifikationsdatum)
	throws IllegalArgumentException {
		
		Notizbuch notizbuch = new Notizbuch();
		
		notizbuch.setTitel(titel);
		
		notizbuch.setEigentuemer(eigentuemer);
		
		notizbuch.setErstelldatum(erstelldatum);
		
		notizbuch.setModifikationsdatum(modifikationsdatum);
	
		return this.notizbuchMapper.erstellen(notizbuch);
		
	}
	
	/**
	 * Ein Notizbuch-Objekt loeschen.
	 * @param notizbuchId Die Notizbuch-ID des Notizbuches, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotizbuch(int notizbuchId)
			throws IllegalArgumentException {
		Notizbuch nb = new Notizbuch();
		nb.setId(notizbuchId);
		this.notizbuchMapper.loeschen(nb);
	}
	
	
	/**
	 * Ein Notizquelle-Objekt anlegen.
	 * @param notizquelleId Notizquelle-ID der Notizquelle, das angelegt werden soll. 
	 * @param url Url.  
	 * @return Das angelegte Notizquelle-Objekt.
	 * @throws IllegalArgumentException
	 */
//TODO
	@Override
	public Notizquelle erstelleNotizquelle(int notizquelleId, String url) throws IllegalArgumentException{
		
		Notizquelle notizquelle = new Notizquelle();
		
		notizquelle.setUrl(url);
		
		return this.notizquelleMapper.insertNotizquelle(notizquelle, notizquelleId);
		
	}
	
	/**
	 * Ein Notizquelle-Objekt loeschen.
	 * @param notizquelleId Die Notizquelle-ID der Notizquelle, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	//TODO
	public void loescheNotizquelle(int notizquelleId)
			throws IllegalArgumentException {
		this.notizquelleMapper.loeschenotizquelle(notizquelleId);
	}
	
	
	/**
	 * Ein Faelligkeit-Objekt anlegen.
	 * @param faelligkeitId Faelligkeit-ID der Faelligkeit, das angelegt werden soll. 
	 * @param datum Datum.  
	 * @return Das angelegte Faelligkeit-Objekt.
	 * @throws IllegalArgumentException
	 */

	
	@Override
	public Faelligkeit erstelleFaelligkeit(int notizId, Date datum) throws IllegalArgumentException{
		
		Faelligkeit faelligkeit = new Faelligkeit();
		faelligkeit.setNotiz(notizId);
		faelligkeit.setDatum(datum);
		
		return this.faelligkeitMapper.erstellen(faelligkeit);
		
	}
	
	/**
	 * Ein Faelligkeit-Objekt loeschen.
	 * @param faelligkeitId Die Faelligkeit-ID der Faelligkeit, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheFaelligkeit(int notizId)
			throws IllegalArgumentException {
		Faelligkeit f = new Faelligkeit();
		f.setNotiz(notizId);
		this.faelligkeitMapper.loeschen(f);
	}
	
	
	/**
	 * Ein NotizFreigabe-Objekt anlegen.
	 * @param notizfreigabeId Notizfreigabe-ID der NotizFreigabe, das angelegt werden soll. 
	 * @param leseberechtigung Leseberechtigung. 
	 * @param aenderungsberechtigung Aenderungsberechtigung.
	 * @param loeschberechtigung Loeschberechtigung. 
	 * @return Das angelegte NotizFreigabe-Objekt.
	 * @throws IllegalArgumentException
	 */
	
	@Override
	public Freigabe erstelleNotizbuchFreigabe(int eintragungsid, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung, String email, String freigegebenerNutzer) throws IllegalArgumentException{
	
		
		Freigabe Freigabe = new Freigabe();
		
		Freigabe.setLeseberechtigung(leseberechtigung);
		Freigabe.setFreigegebeneEintragung(eintragungsid);
		Freigabe.setAenderungsberechtigung(aenderungsberechtigung);
		Freigabe.setFreigebenderNutzer(email);
		Freigabe.setLoeschberechtigung(loeschberechtigung);
		Freigabe.setFreigegebenerNutzer(freigegebenerNutzer);
		
		return this.freigabeMapper.erstellen(Freigabe);
		
	}
	

	/**
	 * Ein NotizFreigabe-Objekt loeschen.
	 * @param notizFreigabeId Die NotizFreigabe-ID der NotizFreigabe, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheFreigabe(String nutzer, int eintragungId)
			throws IllegalArgumentException {
		Freigabe f = new Freigabe();
		f.setFreigegebeneEintragung(eintragungId);
		f.setFreigegebenerNutzer(nutzer);
		this.freigabeMapper.loeschen(f);
	}
	
	
	
	/**
	 * Ein NotizbuchFreigabe-Objekt anlegen.
	 * @param notizbuchfreigabeId Notizbuchfreigabe-ID der NotizbuchFreigabe, das angelegt werden soll. 
	 * @param leseberechtigung Leseberechtigung. 
	 * @param aenderungsberechtigung Aenderungsberechtigung.
	 * @param loeschberechtigung Loeschberechtigung. 
	 * @return Das angelegte NotizbuchFreigabe-Objekt.
	 * @throws IllegalArgumentException
	 */
	
	@Override
	public Freigabe erstelleNotizFreigabe(int eintragungsid, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung, String email, String freigegebenerNutzer) throws IllegalArgumentException{
	
		
		Freigabe Freigabe = new Freigabe();
		
		Freigabe.setLeseberechtigung(leseberechtigung);
		Freigabe.setFreigegebeneEintragung(eintragungsid);
		Freigabe.setAenderungsberechtigung(aenderungsberechtigung);
		Freigabe.setFreigebenderNutzer(email);
		Freigabe.setLoeschberechtigung(loeschberechtigung);
		Freigabe.setFreigegebenerNutzer(freigegebenerNutzer);
		
		return this.freigabeMapper.erstellen(Freigabe);
		
	}
	
	
	/**
	 * Ein NotizbuchFreigabe-Objekt loeschen.
	 * @param notizbuchFreigabeId Die NotizbuchFreigabe-ID der NotizbuchFreigabe, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
//	public void loescheNotizbuchFreigabe(int notizbuchFreigabeId)
//			throws IllegalArgumentException {
//		this.freigabeMapper.loescheNotizbuchFreigabe(notizbuchFreigabeId);
//	}
	
	
	
	
	
	@Override
	public Notiz bearbeiteNotiz(String titel, String subtitel, String inhalt, Date modifikationsdatum) throws IllegalArgumentException{
		Notiz n = new Notiz();
		n.setTitel(titel);
		n.setSubtitel(subtitel);
		n.setInhalt(inhalt);
		n.setModifikationsdatum(modifikationsdatum);
		return this.notizMapper.bearbeiten(n);
		
	}
	
	@Override
	//TODO
	public Notizbuch bearbeiteNotizbuch(String titel, Date modifikationsdatum) throws IllegalArgumentException{
		Notizbuch nb = new Notizbuch();
		nb.setTitel(titel);
		nb.setModifikationsdatum(modifikationsdatum);
		return this.notizbuchMapper.bearbeiten(nb);
				
	}
	
	@Override
	public Nutzer bearbeiteNutzer(String email, String name, String vorname) throws IllegalArgumentException{
		Nutzer n = new Nutzer();
		n.setEmail(email);
		n.setName(name);
		n.setVorname(vorname);
		return this.nutzerMapper.bearbeiten(n);
		
	}
	
	@Override
	//TODO
	public Notizquelle bearbeiteNotizquelle(Notizquelle notizquelle) throws IllegalArgumentException{
		
		return this.notizquelleMapper.bearbeiten(notizquelle);
		
	}
	
	@Override
	public Faelligkeit bearbeiteFaelligkeit(Date datum, int notizId) throws IllegalArgumentException {
		Faelligkeit f = new Faelligkeit();
		f.setDatum(datum);
		f.setNotiz(notizId);
				
		return this.faelligkeitMapper.bearbeiten(f);
		
	}
	
	@Override
	public Freigabe bearbeiteNotizFreigabe(boolean lb, boolean ab, boolean lob, String nutzer) throws IllegalArgumentException{
		Freigabe f = new Freigabe();
		f.setLeseberechtigung(lb);
		f.setAenderungsberechtigung(ab);
		f.setLoeschberechtigung(lob);
		f.setFreigegebenerNutzer(nutzer);
		return this.freigabeMapper.bearbeiten(f);
		
	}
	
	@Override
	public Freigabe bearbeiteNotizbuchFreigabe(boolean lb, boolean ab, boolean lob, String nutzer) throws IllegalArgumentException{
		Freigabe f = new Freigabe();
		f.setLeseberechtigung(lb);
		f.setAenderungsberechtigung(ab);
		f.setLoeschberechtigung(lob);
		f.setFreigegebenerNutzer(nutzer);
		return this.freigabeMapper.bearbeiten(f);
	}
	
	
	
	
	
		
	
	
	@Override
	public void zuweisungNotiz(int notizbuch, int notiz) throws IllegalArgumentException{
		Notizbuch nb = new Notizbuch();
		Notiz n = new Notiz();
		nb.setEintragungId(notizbuch);
		n.setEintragungId(notiz);
		this.notizMapper.zuweisen(nb, n);
	}
	
	public Nutzer getNutzerByEMail(String email) throws IllegalArgumentException{
		Nutzer n = new Nutzer();
		n.setEmail(email);
		return this.nutzerMapper.getNutzerByEmail(n);
		
	}
	
	public Vector<Nutzer> getNutzerByName(String name, String vorname) throws IllegalArgumentException{
		Nutzer n = new Nutzer();
		n.setName(name);
		n.setVorname(vorname);
		return this.nutzerMapper.getNutzerByName(n);
		
	}
	
	public Vector<Notiz> getNotizByTitel(String titel) throws IllegalArgumentException{
		Notiz n = new Notiz();
		n.setTitel(titel);
		return this.notizMapper.getNotizByTitel(n);
		
	}
	
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException{
		Notizbuch n = new Notizbuch();
		n.setTitel(titel);
		return this.notizbuchMapper.getNotizBuchByTitel(n);
		
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


	


	

//TODO?
	


	@Override
	public Vector<Notiz> getNotizByFaelligkeit(Date fdatum)
			throws IllegalArgumentException {
		
		return this.notizMapper.getNotizByFaelligkeit(fdatum);
		
	}


	@Override
	public Vector<Notiz> getNotizenByNutzer(String Email)
			throws IllegalArgumentException {
		
		return this.notizMapper.getNotizenByNutzer(Email);
		
	}


	@Override
	public Freigabe getBerechtigungByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		
		//return this.freigabeMapper.getBerechtigungByNutzer(nutzer);
		return null;
	}


	@Override
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException {
		
		//return this.nutzerMapper.getNutzerByNotiz(notiz);
		return null;
	}

	@Override
	public Vector<Notiz> getNotizen() {
		return this.notizMapper.getNotizen();
	}


	@Override
	public Map<Vector<Notiz>, Vector<Faelligkeit>> getNotizByKriterium(String titel, Date edatum, Date mdatum, Date fdatum)
	throws IllegalArgumentException {
		return this.notizMapper.getNotizenByKriterium(titel, edatum, mdatum, fdatum);
	}

	@Override
	public Map<Vector<Notiz>, Vector<Freigabe>> getNotizByNutzerUndFreigabe(Nutzer n, Freigabe f)
	throws IllegalArgumentException {
		return this.notizMapper.getNotizenByNutzerUndFreigabe(n, f);
	}

	@Override
	public Vector<Notiz> getNotizByEDatum(Date erstelldatum)
			throws IllegalArgumentException {


		return this.notizMapper.getNotizByErstelldatum(erstelldatum);
	}


	@Override
	public Vector<Notiz> getNotizByMDatum(Date modifikationsdatum)
			throws IllegalArgumentException {


		return this.notizMapper.getNotizByModifikationsdatum(modifikationsdatum);
	}

	

	@Override
	public void loescheNotizquelle(Notizquelle notizquelle, int notizquelleid)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void loescheFaelligkeit(Faelligkeit faelligkeit, int faelligkeitid)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Notiz getNotizByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
