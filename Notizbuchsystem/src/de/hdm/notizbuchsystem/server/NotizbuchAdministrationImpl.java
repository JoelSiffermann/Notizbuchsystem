package de.hdm.notizbuchsystem.server;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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

		Nutzer n = new Nutzer();
		n.setEmail(userEmail);
		
		if (nutzerMapper.getNutzerByEmail(n) == null) {
			
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
	public void loescheNotiz(int notizId, String nutzer)
			throws IllegalArgumentException {
		Notiz n = new Notiz();
		n.setId(notizId);
		if(n.getEigentuemer()==nutzer){
			this.notizMapper.loeschen(n);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(notizId);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getLoeschberechtigung()==true){
					this.notizMapper.loeschen(n);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
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
	public void loescheNotizbuch(int notizbuchId, String nutzer)
			throws IllegalArgumentException {
		Notizbuch nb = new Notizbuch();
		nb.setId(notizbuchId);
		if(nb.getEigentuemer()==nutzer){
			this.notizbuchMapper.loeschen(nb);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(notizbuchId);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getLoeschberechtigung()==true){
					this.notizbuchMapper.loeschen(nb);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
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
	public void loescheFaelligkeit(int notizId, String nutzer)
			throws IllegalArgumentException {
		Faelligkeit f = new Faelligkeit();
		f.setNotiz(notizId);
		Notiz n = this.getNotizbyID(notizId);
		if(n.getEigentuemer()==nutzer){
			this.faelligkeitMapper.loeschen(f);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(notizId);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getLoeschberechtigung()==true){
					this.faelligkeitMapper.loeschen(f);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
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
	public Notiz bearbeiteNotiz(int id, String nutzer, String titel, String subtitel, String inhalt, Date modifikationsdatum) throws IllegalArgumentException{
		Notiz n = new Notiz();
		n.setId(id);
		n.setTitel(titel);
		n.setSubtitel(subtitel);
		n.setInhalt(inhalt);
		n.setModifikationsdatum(modifikationsdatum);
		
		if(n.getEigentuemer()==nutzer){
			return this.notizMapper.bearbeiten(n);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(id);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getAenderungsberechtigung()==true){
					return this.notizMapper.bearbeiten(n);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
		return null;
	}
	
	@Override
	public Notizbuch bearbeiteNotizbuch(int id, String nutzer, String titel, Date modifikationsdatum) throws IllegalArgumentException{
		Notizbuch nb = new Notizbuch();
		nb.setId(id);
		nb.setTitel(titel);
		nb.setModifikationsdatum(modifikationsdatum);
		if(nb.getEigentuemer()==nutzer){
			return this.notizbuchMapper.bearbeiten(nb);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(id);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getAenderungsberechtigung()==true){
					return this.notizbuchMapper.bearbeiten(nb);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
		return null;
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
	public Faelligkeit bearbeiteFaelligkeit(Date datum, int notizId, String nutzer) throws IllegalArgumentException {
		Faelligkeit f = new Faelligkeit();
		f.setDatum(datum);
		f.setNotiz(notizId);
		Notiz n = this.getNotizbyID(notizId);
		
		if(n.getEigentuemer()==nutzer){
			return this.faelligkeitMapper.bearbeiten(f);
		} else {
			Vector<Freigabe> freigaben = this.getFreigabeByEintragung(notizId);
			for(Freigabe ergebnis : freigaben) {
				if(ergebnis.getFreigegebenerNutzer()==nutzer && ergebnis.getAenderungsberechtigung()==true){
					return this.faelligkeitMapper.bearbeiten(f);
				} else {
					System.out.println("Dir fehlen die Berechtigungen");
				}
			}
		}
		return null;
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
	public void zuweisungNotiz(int notizbuch, int notiz, String nutzer) throws IllegalArgumentException{
		Notizbuch nb = new Notizbuch();
		Notiz n = new Notiz();
		nb.setId(notizbuch);
		n.setId(notiz);
		if(nb.getEigentuemer()==nutzer && n.getEigentuemer()==nutzer){
			this.notizMapper.zuweisen(nb, n);
		} else {
			Vector<Freigabe> nf = this.getFreigabeByEintragung(notiz);
			Vector<Freigabe> nbf = this.getFreigabeByEintragung(notizbuch);
			for(Freigabe f : nbf) {
				for(Freigabe f2: nf){
					if(f.getFreigebenderNutzer()==nutzer && f.getAenderungsberechtigung()==true){
						if(f2.getFreigebenderNutzer()==nutzer && f2.getAenderungsberechtigung()==true){
							this.notizMapper.zuweisen(nb, n);
						} else {
							System.out.println("Dir fehlen die Berechtigungen");
						}	
					System.out.println("Dir fehlen die Berechtigungen");
					}
				}
			}
		}
	}
	
	@Override
	public Nutzer getNutzerByEMail(String email) throws IllegalArgumentException{
		Nutzer n = new Nutzer();
		n.setEmail(email);
		return this.nutzerMapper.getNutzerByEmail(n);
		
	}
	
	@Override
	public Vector<Nutzer> getNutzerByName(String name, String vorname) throws IllegalArgumentException{
		Nutzer n = new Nutzer();
		n.setName(name);
		n.setVorname(vorname);
		return this.nutzerMapper.getNutzerByName(n);
		
	}
	
	@Override
	public Vector<Notiz> getNotizByTitel(String titel) throws IllegalArgumentException{
		Notiz n = new Notiz();
		n.setTitel(titel);
		return this.notizMapper.getNotizByTitel(n);
		
	}
	
	@Override
	public Vector<Notizbuch> getNotizbuchByTitel(String titel) throws IllegalArgumentException{
		Notizbuch n = new Notizbuch();
		n.setTitel(titel);
		return this.notizbuchMapper.getNotizBuchByTitel(n);
		
	}
	
	@Override
	public Vector<Faelligkeit> getFaelligkeitByDatum(Date datum) throws IllegalArgumentException{
		Faelligkeit f = new Faelligkeit();
		f.setDatum(datum);
		return this.faelligkeitMapper.getFaelligkeitByDatum(f);
		
	}
	
	@Override
	public Vector<Freigabe> getFreigabeByEintragung(int id) throws IllegalArgumentException{
		Freigabe f = new Freigabe();
		f.setFreigegebeneEintragung(id);
		return this.freigabeMapper.getFreigabeByEintragung(f);
		
	}
	
	@Override
	public Notiz getNotizbyID(int id) throws IllegalArgumentException{
		Notiz n = new Notiz();
		n.setId(id);
		return this.notizMapper.getNotizByID(n);
		
	}
	
	@Override
	public Notizbuch getNotizbuchbyID(int id) throws IllegalArgumentException{
		Notizbuch n = new Notizbuch();
		n.setId(id);
		return this.notizbuchMapper.getNotizbuchByID(n);
		
	}
		
	public Vector<Notiz> getNotizByNotizbuch(int notizbuchid, String nutzer) throws IllegalArgumentException{
		Notizbuch nb = new Notizbuch();
		nb.setId(notizbuchid);
		if(nb.getEigentuemer()==nutzer){
			return this.notizMapper.getNotizenByNotizbuch(nb);
		}
		Vector<Freigabe> f = this.getBerechtigungByNutzer(nutzer);
		for(Freigabe freigabe : f) {
				if(freigabe.getFreigegebenerNutzer()==nutzer && freigabe.getLeseberechtigung()==true){
					 return this.notizMapper.getNotizByFreigabe(freigabe);
				}
			}
		return null;
	}


	//TODO brauchen wir des?
	@Override
	public Freigabe getFreigabe(String email){
		Nutzer n = new Nutzer();
		Freigabe f = new Freigabe();
		n.setEmail(email);
		Vector<Freigabe> v = this.freigabeMapper.getFreigabeByNutzer(n);
		for (int i = 0 ; i < v.size(); i++) {
			f.setLeseberechtigung(v.get(i).getLeseberechtigung());
			f.setAenderungsberechtigung(v.get(i).getAenderungsberechtigung());
			f.setLoeschberechtigung(v.get(i).getLoeschberechtigung());
		}
		return f;
	}

	//TODO
	@Override
	public Vector<Notiz> getNotizByFaelligkeit(Date fdatum)
			throws IllegalArgumentException {
		
		return this.notizMapper.getNotizByFaelligkeit(fdatum);
		
	}


	@Override
	public Vector<Notiz> getNotizenByNutzer(String Email)
			throws IllegalArgumentException {
		Vector<Notiz> result = new Vector<Notiz>();
		result.addAll(this.notizMapper.getNotizenByNutzer(Email));
		Vector<Freigabe> f = this.getBerechtigungByNutzer(Email);
		for(Freigabe freigabe : f) {
			if(freigabe.getFreigegebenerNutzer()==Email && freigabe.getLeseberechtigung()==true){
				result.addAll(this.notizMapper.getNotizByFreigabe(freigabe));
			}
		}
		return result;
	}


	@Override
	public Vector<Freigabe> getBerechtigungByNutzer(String email)
			throws IllegalArgumentException {
		Nutzer n = new Nutzer();
		n.setEmail(email);
		return this.freigabeMapper.getFreigabeByNutzer(n);
	}

	//TODO
	@Override
	public Nutzer getNutzerByNotiz(Notiz notiz) throws IllegalArgumentException {
		
		//return this.nutzerMapper.getNutzerByNotiz(notiz);
		return null;
	}

	@Override
	public Vector<Notiz> getNotizen() {
		return this.notizMapper.getNotizen();
	}
	
	//TODO
	public Vector<Notizbuch> getNotizbuecherByNutzer(String Email)
			throws IllegalArgumentException {
		
		return this.notizbuchMapper.getNotizbuecherByNutzer(Email);
		
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

	//TODO
	@Override
	public Vector<Notiz> getNotizByEDatum(Date erstelldatum)
			throws IllegalArgumentException {


		return this.notizMapper.getNotizByErstelldatum(erstelldatum);
	}

	//TODO
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
	public Notiz getNotizByNutzer(Nutzer nutzer)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getStringforSuggestBox(){
		List<String> result = new ArrayList<String>();
		Vector<Nutzer> n = this.nutzerMapper.getAllNutzer();
		for(int i = 0; i < n.size() ; i++){
			String email = n.get(i).getEmail();
			result.add(email);
		}
		return result;
	}

	@Override
	public Vector<Nutzer> getAllNutzer(){
		return this.nutzerMapper.getAllNutzer();
	}

}
