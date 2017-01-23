package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;

public interface NotizSystemAdministrationAsync {

//	void login(AsyncCallback<Void> callback);
	
	//prüft ob der Nuter neu angelegt wird
	void pruefeObNutzerNeu(String userEmail, AsyncCallback<Boolean> callback);
	
//	void logout(AsyncCallback<Void> callback);
	
	void init(AsyncCallback<Void> callback);
	
	void erstelleNotiz(String titel, String subtitel,
			String inhalt, String eigentuemer, Date erstelldatum,
			Date modifikationsdatum, AsyncCallback<Notiz> callback);

	void erstelleNotizbuch(String titel, String eigentuemer,
			Date erstelldatum, Date modifikationsdatum,
			AsyncCallback<Notizbuch> callback);
	
	void erstelleNutzer(String name, String vorname, String email, AsyncCallback<Nutzer> callback);
	
	void erstelleNotizquelle(int NotizquelleID, String url,
			AsyncCallback<Notizquelle> callback);
	
	void erstelleFaelligkeit(int faelligkeitId, Date datum,
			AsyncCallback<Faelligkeit> callback);
	
	void erstelleNotizFreigabe(int notizfreigabeId, boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			AsyncCallback<Freigabe> callback);
	
	void erstelleNotizbuchFreigabe(int notizbuchFreigabeId,
			boolean leseberechtigung, boolean aenderungsberechtigung,
			boolean loeschberechtigung, AsyncCallback<Freigabe> callback);
	
	void loescheNotiz(Notiz notiz, Eintragung eintragung, int notizid,
			AsyncCallback<Void> callback);
	
	void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung,
			int notizbuchid, AsyncCallback<Void> callback);
	
	void loescheNutzer(int nutzerId, AsyncCallback<Void> callback);
	
	void loescheNotizquelle(Notizquelle notizquelle, int notizquelleid,
			AsyncCallback<Void> callback);
	
	void loescheFaelligkeit(Faelligkeit faelligkeit, int faelligkeitid,
			AsyncCallback<Void> callback);
	
	void loescheFreigabe(Freigabe freigabe, int freigabeId,
			AsyncCallback<Void> callback);	
	
	void bearbeiteNotiz(Notiz notiz, Eintragung eintragung, AsyncCallback<Notiz> callback);
	
	void bearbeiteNotizbuch(Notizbuch notizbuch, Eintragung eintragung, AsyncCallback<Notizbuch> callback);
	
	void bearbeiteNotizquelle(Notizquelle notizquelle, AsyncCallback<Notizquelle> callback);
	
	void bearbeiteFaelligkeit(Faelligkeit faelligkeit, Date datum,
			AsyncCallback<Faelligkeit> callback);
	
	void bearbeiteNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);
	
	void bearbeiteNotizFreigabe(Freigabe notizfreigabe, AsyncCallback<Freigabe> callback);

	void bearbeiteNotizbuchFreigabe(Freigabe notizbuchfreigabe, AsyncCallback<Freigabe> callback);

	void zuweisungNotiz(Notizbuch notizbuch, Vector<Notiz> notiz,
			AsyncCallback<Notiz> callback);
	
	void getNotizByFaelligkeit(Date fdatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByNutzer(Nutzer nutzer, AsyncCallback<Notiz> callback);
	
	void getBerechtigungByNutzer(Nutzer nutzer, AsyncCallback<Freigabe> callback);
	
	void getNutzerByNotiz(Notiz notiz, AsyncCallback<Nutzer> callback);
	
	
	
	void getNutzerByEmail(String emailAddress, AsyncCallback<Nutzer> callback);
	
	void getNutzerByName(String name, String vorname, AsyncCallback<Vector<Nutzer>> callback);
	
	void getNotizByTitel(String titel, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizbuchByTitel(String titel, AsyncCallback<Vector<Notizbuch>> callback);
	
	void getFaelligkeitByDatum(Date datum, AsyncCallback<Vector<Faelligkeit>> callback);
	
	void getFreigabeByNotiz(Notiz notiz, AsyncCallback<Vector<Freigabe>> callback);
	
	void getNotizByEDatum(Date erstelldatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByMDatum(Date modifikationsdatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByNotizbuch(String titel, AsyncCallback<Vector<Notiz>> callback);
	
	
	void speicherNutzer(int notizId, String vorname, String Name, AsyncCallback<Void> callback);

	void getNutzerById(int nutzerId, AsyncCallback<Nutzer> asyncCallback);

	void getNotizenByNutzer(Nutzer nutzer, AsyncCallback<Vector<Notiz>> callback);

	void getNotizen(AsyncCallback<Vector<Notiz>> callback);

	void getNotizByKriterium(String titel, Date edatum, Date mdatum,
			Date fdatum, AsyncCallback<Map<Vector<Notiz>, Vector<Faelligkeit>>> callback);

	void getNotizByNutzerUndFreigabe(Nutzer n, Freigabe f,
			AsyncCallback<Map<Vector<Notiz>, Vector<Freigabe>>> callback);

	

	
}
