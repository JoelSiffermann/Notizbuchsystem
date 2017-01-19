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
		return nutzerMapper.findByNutzerMitEmail(email);

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
		
		nutzer.setEmailAddress(emailAddress);
		
		nutzer.setNutzerId(1);
		
		nutzer = this.nutzerMapper.insertNutzer(nutzer);

		return nutzer;
		
	}
	
	/**
	 * Ein Nutzer-Objekt aktualisieren.
	 * @param nutzerId Nutzer-ID des Nutzers, das aktualisiert werden soll.
	 * @param vorname Vorname.
	 * @param name Name.
	 * @throws IllegalArgumentException
	 */
	public void saveNutzer(int nutzerId, String vorname, String name)
			throws IllegalArgumentException {

		Nutzer nutzer = new Nutzer();
		nutzer.setVorname(vorname);
		nutzer.setName(name);
	

		nutzer.setNutzerId(nutzerId);

		this.nutzerMapper.updateNutzer(nutzer);
	}

	/**
	 * Ein Nutzer-Objekt loeschen.
	 * @param nutzerId Die Nutzer-ID des Nutzers, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNutzer(int nutzerId)
			throws IllegalArgumentException {
		this.nutzerMapper.loeschenutzer(nutzerId);
	}
	
	/**
	 * Ein Nutzer-Objekt anhand der Nutzer-ID auslesen.
	 * @param nutzerId Nutzer-ID des Nutzers, das ausgelesen werden soll. 
	 * @return Das ausgelesene Nutzer-Objekt.
	 * @throws IllegalArgumentException
	 */
	public Nutzer getNutzerById(int nutzerId)
			throws IllegalArgumentException {
		return this.nutzerMapper.findByNutzerId(nutzerId);
	}

		
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
	
	@Override
	public Notiz erstelleNotiz(int notizId, String titel, String subtitel, String inhalt, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException{
		
		Notiz notiz = new Notiz();
		
		notiz.setTitel(titel);
		
		notiz.setSubtitel(subtitel);
		
		notiz.setInhalt(inhalt);
		
		notiz.setEigentuemer(eigentuemer);
		
		notiz.setErstelldatum(erstelldatum);
		
		notiz.setModifikationsdatum(modifikationsdatum);
	
		return this.notizMapper.insertNotiz(notiz, notizId);
						 
	}
	
	/**
	 * Ein Notiz-Objekt loeschen.
	 * @param notizId Die Notiz-ID der Notiz, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotiz(int notizId)
			throws IllegalArgumentException {
		this.notizMapper.loeschenotiz(notizId);
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
	
	@Override
	public Notizbuch erstelleNotizbuch(int notizbuchId, String titel, String eigentuemer, Date erstelldatum, Date modifikationsdatum) throws IllegalArgumentException {
		
		Notizbuch notizbuch = new Notizbuch();
		
		notizbuch.setTitel(titel);
		
		notizbuch.setEigentuemer(eigentuemer);
		
		notizbuch.setErstelldatum(erstelldatum);
		
		notizbuch.setModifikationsdatum(modifikationsdatum);
	
		return this.notizbuchMapper.insertNotizbuch(notizbuch, notizbuchId);
		
	}
	
	/**
	 * Ein Notizbuch-Objekt loeschen.
	 * @param notizbuchId Die Notizbuch-ID des Notizbuches, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotizbuch(int notizbuchId)
			throws IllegalArgumentException {
		this.notizbuchMapper.loeschenotizbuch(notizbuchId);
	}
	
	
	/**
	 * Ein Notizquelle-Objekt anlegen.
	 * @param notizquelleId Notizquelle-ID der Notizquelle, das angelegt werden soll. 
	 * @param url Url.  
	 * @return Das angelegte Notizquelle-Objekt.
	 * @throws IllegalArgumentException
	 */

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
	public Faelligkeit erstelleFaelligkeit(int faelligkeitId, Date datum) throws IllegalArgumentException{
		
		Faelligkeit faelligkeit = new Faelligkeit();
		
		faelligkeit.setDatum(datum);
		
		return this.faelligkeitMapper.insertNotizquelle(faelligkeit, faelligkeitId);
		
	}
	
	/**
	 * Ein Faelligkeit-Objekt loeschen.
	 * @param faelligkeitId Die Faelligkeit-ID der Faelligkeit, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheFaelligkeit(int faelligkeitId)
			throws IllegalArgumentException {
		this.faelligkeitMapper.loescheFaelligkeit(faelligkeitId);
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
	public Freigabe erstelleNotizFreigabe(int notizFreigabeId, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException{
	
		
		NotizFreigabe NotizFreigabe = new NotizFreigabe();
		
		NotizFreigabe.setLeseberechtigung(leseberechtigung);
		
		NotizFreigabe.setAenderungsberechtigung(aenderungsberechtigung);
		
		NotizFreigabe.setLoeschberechtigung(loeschberechtigung);
		
		
		return this.freigabeMapper.insertNotizFreigabe(NotizFreigabe, notizFreigabeId);
		
	}
	

	/**
	 * Ein NotizFreigabe-Objekt loeschen.
	 * @param notizFreigabeId Die NotizFreigabe-ID der NotizFreigabe, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotizFreigabe(int notizFreigabeId)
			throws IllegalArgumentException {
		this.freigabeMapper.loescheNotizFreigabe(notizFreigabeId);
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
	public Freigabe erstelleNotizbuchFreigabe(int notizbuchFreigabeId, boolean leseberechtigung, boolean aenderungsberechtigung, boolean loeschberechtigung) throws IllegalArgumentException{
		
		NotizbuchFreigabe NotizbuchFreigabe = new NotizbuchFreigabe();
		
		NotizbuchFreigabe.setLeseberechtigung(leseberechtigung);
		
		NotizbuchFreigabe.setAenderungsberechtigung(aenderungsberechtigung);
		
		NotizbuchFreigabe.setLoeschberechtigung(loeschberechtigung);
		
		
		return this.freigabeMapper.insertNotizbuchFreigabe(NotizbuchFreigabe, notizbuchFreigabeId);
		
	}
	
	
	/**
	 * Ein NotizbuchFreigabe-Objekt loeschen.
	 * @param notizbuchFreigabeId Die NotizbuchFreigabe-ID der NotizbuchFreigabe, das geloescht werden soll.
	 * @throws IllegalArgumentException
	 */
	public void loescheNotizbuchFreigabe(int notizbuchFreigabeId)
			throws IllegalArgumentException {
		this.freigabeMapper.loescheNotizbuchFreigabe(notizbuchFreigabeId);
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
		
		return this.notizMapper.getNotizByTitel(titel);
		
	}
	
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException{
		
		return this.notizbuchMapper.getNotizBuchByTitel(titel);
		
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
	public Vector<Notiz> getNotizenByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		
		return this.notizMapper.getNotizenByNutzer(nutzer);
		
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
	public void speicherNutzer(int NutzerId, String vorname, String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void loescheNotiz(Notiz notiz, Eintragung eintragung, int notizid)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung,
			int notizbuchid) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
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
	public void loescheFreigabe(Freigabe freigabe, int freigabeId)
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
