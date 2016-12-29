package de.hdm.notizbuchsystem.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.Vector;
import java.util.ArrayList;

import de.hdm.notizbuchsystem.shared.bo.Nutzer;
import de.hdm.notizbuchsystem.shared.bo.*;

public interface NotizSystemAdministrationAsync {

//	void login(AsyncCallback<Void> callback);
	
	//prüft ob der Nuter neu angelegt wird
	void pruefeObNutzerNeu(String userEmail, AsyncCallback<Boolean> callback);
	
//	void logout(AsyncCallback<Void> callback);
	
	void init(AsyncCallback<Void> callback);
	
	void speichereNutzer(int profilId, String vorname, String name,
			AsyncCallback<Void> callback);
	
	void erstelleNotiz(String titel, String subtitel, String inhalt, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum, AsyncCallback<Notiz> callback);

	void erstelleNotizbuch(String titel, Nutzer eigentuemer, Date erstelldatum, Date modifikationsdatum, AsyncCallback<Notizbuch> callback);
	
	void erstelleNutzer(String name, String vorname, String email, AsyncCallback<Nutzer> callback);
	
	void erstelleNotizquelle(String url, AsyncCallback<Notizquelle> callback);
	
	void erstelleFaelligkeit(Date datum, AsyncCallback<Faelligkeit> callback);
	
	void erstelleNotizFreigabe(Boolean leseberechtigung, Boolean aenderungsberechtigung, Boolean loeschberechtigung, String email, AsyncCallback<Freigabe> callback);
	
	void erstelleNotizbuchFreigabe(Boolean leseberechtigung, Boolean aenderungsberechtigung, Boolean loeschberechtigung, String email, AsyncCallback<Freigabe> callback);
	
	void loescheNotiz(Notiz notiz, Eintragung eintragung, AsyncCallback<Void> callback);
	
	void loescheNotizbuch(Notizbuch notizbuch, Eintragung eintragung, AsyncCallback<Void> callback);
	
	void loescheNutzer(int profilId, AsyncCallback<Void> callback);
	
	void loescheNotizquelle(Notizquelle notizquelle, AsyncCallback<Void> callback);
	
	void loescheFaelligkeit(Faelligkeit faelligkeit, AsyncCallback<Void> callback);
	
	void loescheFreigabe(Freigabe freigabe, AsyncCallback<Void> callback);
	
	void bearbeiteNotiz(Notiz notiz, Eintragung eintragung, AsyncCallback<Notiz> callback);
	
	void bearbeiteNotizbuch(Notizbuch notizbuch, Eintragung eintragung, AsyncCallback<Notizbuch> callback);
	
	void bearbeiteNotizquelle(Notizquelle notizquelle, AsyncCallback<Notizquelle> callback);
	
	void bearbeiteFaelligkeit(Date faelligkeit, AsyncCallback<Faelligkeit> callback);
	
	void bearbeiteNutzer(Nutzer nutzer, AsyncCallback<Nutzer> callback);
	
	void bearbeiteNotizFreigabe(Freigabe notizfreigabe, AsyncCallback<Freigabe> callback);

	void bearbeiteNotizbuchFreigabe(Freigabe notizbuchfreigabe, AsyncCallback<Freigabe> callback);

	void zuweisungNotiz(Notizbuch notizbuch, AsyncCallback<Notiz> callback);
	
	void getNotizByFaelligkeit(Faelligkeit faelligkeit, AsyncCallback<Notiz> callback);
	
	void getNotizByNutzer(Nutzer nutzer, AsyncCallback<Notiz> callback);
	
	void getBerechtigungByNutzer(Nutzer nutzer, AsyncCallback<Freigabe> callback);
	
	void getNutzerByNotiz(Notiz notiz, AsyncCallback<Nutzer> callback);
	
	void getNutzerById(int profilId, AsyncCallback<Nutzer> callback);
	
	void getNutzerByEmail(String email, AsyncCallback<Nutzer> callback);
	
	void getNutzerByName(String name, String vorname, AsyncCallback<Vector<Nutzer>> callback);
	
	void getNotizByTitel(String titel, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizbuchByTitel(String titel, AsyncCallback<Vector<Notizbuch>> callback);
	
	void getFaelligkeitByDatum(Date datum, AsyncCallback<Vector<Faelligkeit>> callback);
	
	void getFreigabeByNotiz(Notiz notiz, AsyncCallback<Vector<Freigabe>> callback);
	
	void getNotizByEDatum(Date erstelldatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByMDatum(Date modifikationsdatum, AsyncCallback<Vector<Notiz>> callback);
	
	void getNotizByNotizbuch(String titel, AsyncCallback<Vector<Notiz>> callback);
	
	void speicherNutzer(int profilId, String vorname, String Name, AsyncCallback<Void> callback);
}
