package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;


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
	
	void loescheNotiz(int notizid,
			AsyncCallback<Void> callback);
	
	void loescheNotizbuch(
			int notizbuchid, AsyncCallback<Void> callback);
	
	void loescheNutzer(String email, AsyncCallback<Void> callback);
	
	void loescheNotizquelle(Notizquelle notizquelle, int notizquelleid,
			AsyncCallback<Void> callback);
	
	void loescheFaelligkeit(Faelligkeit faelligkeit, int faelligkeitid,
			AsyncCallback<Void> callback);
	
	void loescheFreigabe(String nutzer, int eintragungId,
			AsyncCallback<Void> callback);	
	
	void bearbeiteNotiz(int id, String titel, String subtitel, String inhalt, Date modifikationsdatum, AsyncCallback<Notiz> callback);
	
	void bearbeiteNotizbuch(int id, String titel, Date modifikationsdatum, AsyncCallback<Notizbuch> callback);
	
	void bearbeiteNotizquelle(Notizquelle notizquelle, AsyncCallback<Notizquelle> callback);
	
	void bearbeiteFaelligkeit(Date datum, int notizId,
			AsyncCallback<Faelligkeit> callback);
	
	void bearbeiteNutzer(String email, String name, String vorname, AsyncCallback<Nutzer> callback);
	
	void bearbeiteNotizFreigabe(boolean lb, boolean ab, boolean lob, String nutzer, AsyncCallback<Freigabe> callback);

	void bearbeiteNotizbuchFreigabe(boolean lb, boolean ab, boolean lob, String nutzer, AsyncCallback<Freigabe> callback);

	void zuweisungNotiz(int notizbuch, int notiz,
			AsyncCallback<Void> callback);
	
	void getNotizByFaelligkeit(Date fdatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByNutzer(Nutzer nutzer, AsyncCallback<Notiz> callback);
	
	void getBerechtigungByNutzer(Nutzer nutzer, AsyncCallback<Freigabe> callback);
	
	void getNutzerByNotiz(Notiz notiz, AsyncCallback<Nutzer> callback);
	
	
	
	void getNutzerByEMail(String email, AsyncCallback<Nutzer> callback);
	
	void getNutzerByName(String name, String vorname, AsyncCallback<Vector<Nutzer>> callback);
	
	void getNotizByTitel(String titel, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizbuchByTitel(String titel, AsyncCallback<Vector<Notizbuch>> callback);
	
	void getFaelligkeitByDatum(Date datum, AsyncCallback<Vector<Faelligkeit>> callback);
	
	void getFreigabeByEintragung(int id, AsyncCallback<Vector<Freigabe>> callback);
	
	void getNotizByEDatum(Date erstelldatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByMDatum(Date modifikationsdatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByNotizbuch(int notizbuchId, AsyncCallback<Vector<Notiz>> callback);
	

	void getNotizenByNutzer(String email, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizbuecherByNutzer(String email, AsyncCallback<Vector<Notizbuch>> callback);

	void getNotizen(AsyncCallback<Vector<Notiz>> callback);

	void getNotizByKriterium(String titel, Date edatum, Date mdatum,
			Date fdatum, AsyncCallback<Map<Vector<Notiz>, Vector<Faelligkeit>>> callback);

	void getNotizByNutzerUndFreigabe(Nutzer n, Freigabe f,
			AsyncCallback<Map<Vector<Notiz>, Vector<Freigabe>>> callback);

	void erstelleNotizbuchFreigabe(int eintragungsid, boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			String email, String freigegebenerNutzer,
			AsyncCallback<Freigabe> callback);
	void erstelleNotizFreigabe(int eintragungsid, boolean leseberechtigung,
			boolean aenderungsberechtigung, boolean loeschberechtigung,
			String email, String freigegebenerNutzer,
			AsyncCallback<Freigabe> callback);

	void getNotizbyID(int id, AsyncCallback<Notiz> callback);

	void getStringforSuggestBox(AsyncCallback<String[]> callback);

	

	
}
