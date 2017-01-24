package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;


public class NotizMapper {

	private static NotizMapper notizMapper = null;
	
	protected NotizMapper() {
		
	}
	
	public static NotizMapper notizMapper() {
		if (notizMapper == null) {
			notizMapper = new NotizMapper();
		}
		
	return notizMapper;
	}
	
	public Notiz erstellen(Notiz n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(`Eintragung-ID`) AS maxid "
		          + "FROM eintragung ");
			
			if (rs.next()) {
				int i = 1;
				n.setId(rs.getInt("maxid") + i);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO eintragung ( `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`) " + "VALUES ('"
		            + n.getId() + "','" + n.getEigentuemer() + "','" + getSqlDateFormat(n.getModifikationsdatum()) + "','" + getSqlDateFormat(n.getErstelldatum()) + "','" + n.getTitel() + "' )");
				stmt.executeUpdate("INSERT INTO notiz (`ID`, `Inhalt`, Subtitel) VALUES ('" + n.getId() + "','" + n.getInhalt() + "','" + n.getSubtitel() + "')");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	
	private String getSqlDateFormat(Date erstelldatum) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(erstelldatum);
		return result;
	}

	// Es können bis jetzt nur Modifikationsdatum, Titel, Subtitel und Inhalt bearbeitet werden, da die restlichen Werte nicht veränderbar sein sollten
	public Notiz bearbeiten(Notiz n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE eintragung, notiz" + "SET Titel=\""
          + n.getTitel() + "\", " + "Modifikationsdatum=\"" +n.getModifikationsdatum()+ "\", " + "Subtitel=\"" + n.getSubtitel()+ "\", " + "SET Inhalt=\"" + n.getInhalt()
          + "WHERE `Eintragung-ID`='" + n.getId() + "'");
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	
	public void loeschen(Notiz n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Notiz " + "WHERE id=" + n.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Vector<Notiz> getNotizenByNutzer(String email) {
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`, `Subtitel`, notiz.`Inhalt`"
		    		  + "FROM eintragung INNER JOIN notizbuchdb.notiz ON `Eintragung-ID` = `ID` WHERE Eigentuemer ='" + email
		          + "' ORDER BY `Eintragung-ID`");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		    	  
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragung-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        e.setErstelldatum(rs.getDate("Erstelldatum"));
		        e.setTitel(rs.getString("Titel"));
		        e.setSubtitel(rs.getString("Subtitel"));
		        e.setInhalt(rs.getString("Inhalt"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	
public Vector<Notiz> getNotizen() {
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, Eigentuemer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt"
		    		  + "Eigentuemer FROM eintragung INNER JOIN notizbuchdb.notiz ON Eintragung-ID = Notiz-ID"
		          + " ORDER BY `Eintragung-ID`");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragung-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        e.setErstelldatum(rs.getDate("Erstelldatum"));
		        e.setTitel(rs.getString("Titel"));
		        e.setSubtitel(rs.getString("Subtitel"));
		        e.setInhalt(rs.getString("Inhalt"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	

	public Vector<Notiz> getNotizByTitel(Notiz n) {
		
		Connection con = DBConnection.getConnection();
		Vector<Notiz> result = new Vector<Notiz>();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Eintragung INNERJOIN Notiz ON `Eintragung-ID` = ID WHERE titel LIKE '%" + n.getTitel() + "%' ");
			
			while(rs.next()){
				Notiz n1 = new Notiz();
				n1.setId(rs.getInt("Eintragungs-ID"));
		        n1.setEigentuemer(rs.getString("Eigentuemer"));
		        n1.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        n1.setErstelldatum(rs.getDate("Erstelldatum"));
		        n1.setTitel(rs.getString("Titel"));
		        n1.setSubtitel(rs.getString("Subtitel"));
		        n1.setInhalt(rs.getString("Inhalt"));
		        
		        result.addElement(n1);
				}
			} catch (SQLException e1){
				e1.printStackTrace();
			}
			
			return result;
		
		}
		
	public Vector<Notiz> getNotizByFaelligkeit(Date fdatum) {

		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();
//TODO Query anpassen!
		      ResultSet rs = stmt.executeQuery("SELECT Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt"
		    		  + "Eigentuemer FROM Eintragung INNER JOIN Notiz ON Eintragung-ID = Notiz-ID"
		          + " ORDER BY Eintragung-ID");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragungs-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        e.setErstelldatum(rs.getDate("Erstelldatum"));
		        e.setTitel(rs.getString("Titel"));
		        e.setSubtitel(rs.getString("Subtitel"));
		        e.setInhalt(rs.getString("Inhalt"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	

	public void zuweisen(Notizbuch notizbuch, Notiz notiz) {
		Connection con = DBConnection.getConnection();
		
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeQuery("INSERT INTO notiz (notizbuch) VALUES ('" + notizbuch.getEintragungId() + "') WHERE notiz.`ID`='" + notiz.getEintragungId() + "'");
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}

	public Vector<Notiz> getNotizByErstelldatum(Date erstelldatum) {

		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt"
		    		  + "Eigentuemer FROM Eintragung INNER JOIN Notiz ON Eintragung-ID = Notiz-ID"
		          + "WHERE Erstelldatum=" + erstelldatum + " ORDER BY Eintragung-ID");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragungs-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        e.setErstelldatum(rs.getDate("Erstelldatum"));
		        e.setTitel(rs.getString("Titel"));
		        e.setSubtitel(rs.getString("Subtitel"));
		        e.setInhalt(rs.getString("Inhalt"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	

	public Vector<Notiz> getNotizByModifikationsdatum(Date modifikationsdatum) {
Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt"
		    		  + "Eigentuemer FROM Eintragung INNER JOIN Notiz ON Eintragung-ID = Notiz-ID"
		          + "WHERE Erstelldatum=" + modifikationsdatum + " ORDER BY Eintragung-ID");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragungs-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        e.setErstelldatum(rs.getDate("Erstelldatum"));
		        e.setTitel(rs.getString("Titel"));
		        e.setSubtitel(rs.getString("Subtitel"));
		        e.setInhalt(rs.getString("Inhalt"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(e);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
	}
	
	public Map<Vector<Notiz>, Vector<Faelligkeit>> getNotizenByKriterium(String titel, Date edatum, Date mdatum, Date fdatum) {
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> notiz = new Vector<Notiz>();
		Vector<Faelligkeit> v = new Vector<Faelligkeit>();
		Map<Vector<Notiz>, Vector<Faelligkeit>> result = new HashMap<Vector<Notiz>, Vector<Faelligkeit>>();
		
		StringBuffer whereQuery = new StringBuffer("WHERE `Eintragung-ID` IS NOT NULL");
		
		String titelQuery = "";
		String eQuery = "";
		String mQuery = "";
		String fQuery = "";
		
				if(titel != null){
					titelQuery = "Titel LIKE '%" + titel + "%' ";
					whereQuery.append(" AND " + titelQuery);
			        
				} 
				if(edatum != null) {
					eQuery = "Erstelldatum = '" + getSqlDateFormat(edatum) + "'";
					whereQuery.append(" AND " + eQuery);
					
				}
				if(mdatum != null) {
					mQuery = "Modifikationsdatum = '" + getSqlDateFormat(mdatum) + "'";
					whereQuery.append(" AND " + mQuery);
					
				} 
				if(fdatum != null) {
					fQuery = "Faelligkeit.Datum = '" + getSqlDateFormat(fdatum) + "'";
					whereQuery.append(" AND " + fQuery);
				}
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, Eigentuemer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt, Faelligkeit.Datum"
				    		  + "FROM eintragung LEFT JOIN notiz ON `Eintragung-ID` = notiz.ID LEFT JOIN faelligkeit ON notiz.ID = faelligkeit.Eintragung"
				          + whereQuery + " ORDER BY `Eintragung-ID`");
				while(rs.next()){
					
					Notiz n = new Notiz();
					Faelligkeit f = new Faelligkeit();
					n.setId(rs.getInt("Eintragungs-ID"));
			        n.setEigentuemer(rs.getString("Eigentuemer"));
			        n.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
			        n.setErstelldatum(rs.getDate("Erstelldatum"));
			        n.setTitel(rs.getString("Titel"));
			        n.setSubtitel(rs.getString("Subtitel"));
			        n.setInhalt(rs.getString("Inhalt"));
			        f.setDatum(rs.getDate("Faelligkeit.Datum"));
			        v.add(f);
			        notiz.add(n);
			        result.put(notiz, v);
			} 
				}
				catch(SQLException e1) {
				e1.printStackTrace();
			}
			
			return result;
		
	}

public Map<Vector<Notiz>, Vector<Freigabe>> getNotizenByNutzerUndFreigabe(Nutzer n, Freigabe f) {
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> notiz = new Vector<Notiz>();
		Vector<Freigabe> v = new Vector<Freigabe>();
		Map<Vector<Notiz>, Vector<Freigabe>> result = new HashMap<Vector<Notiz>, Vector<Freigabe>>();
		
		
				
			    
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, Eigentuemer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt, nutzerfreigabe.Leseberechtigung,"
				    		  + "nutzerfreigabe.Aenderungsberechtigung, nutzerfreigabe.Loeschberechtigung nutzerfreigabe.FreigegebenerNutzer FROM eintragung INNER JOIN notiz ON `Eintragung-ID` = notiz.ID LEFT JOIN nutzerfreigabe ON eintragung.Eigentuemer = nutzerfreigabe.FreigebenderNutzer"
				          + "WHERE eintragung.Eigentuemer LIKE '%" + n.getEmail() + "%' ORDER BY `Eintragung-ID`");
				while(rs.next()){
					
					Notiz no = new Notiz();
					Freigabe fr = new Freigabe();
					no.setId(rs.getInt("Eintragungs-ID"));
			        no.setEigentuemer(rs.getString("Eigentuemer"));
			        no.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
			        no.setErstelldatum(rs.getDate("Erstelldatum"));
			        no.setTitel(rs.getString("Titel"));
			        no.setSubtitel(rs.getString("Subtitel"));
			        no.setInhalt(rs.getString("Inhalt"));
			        fr.setLeseberechtigung(rs.getBoolean("Leseberechtigung"));
			        fr.setAenderungsberechtigung(rs.getBoolean("Aenderungsberechtigung"));
			        fr.setLoeschberechtigung(rs.getBoolean("Loeschberechtigung"));
			        fr.setFreigegebenerNutzer(rs.getString("FreigegebenerNutzer"));
			        v.add(fr);
			        notiz.add(no);
			        result.put(notiz, v);
			} 
				}
				catch(SQLException e1) {
				e1.printStackTrace();
			}
			
			return result;
		
	}

	public Notiz getNotizByID(Notiz n) {
	
	Connection con = DBConnection.getConnection();
	Notiz e = new Notiz();
	
	try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`, `Subtitel`, `Inhalt`"
	    		  + "FROM eintragung INNER JOIN notizbuchdb.notiz ON `Eintragung-ID` = `ID` WHERE `Eintragung-ID` ='" + n.getId()
	          + "' ORDER BY `Eintragung-ID`");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      if (rs.next()) {
	    	  
	        
	        e.setId(rs.getInt("Eintragung-ID"));
	        e.setEigentuemer(rs.getString("Eigentuemer"));
	        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
	        e.setErstelldatum(rs.getDate("Erstelldatum"));
	        e.setTitel(rs.getString("Titel"));
	        e.setSubtitel(rs.getString("Subtitel"));
	        e.setInhalt(rs.getString("Inhalt"));
	        

	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	
	return e;

	  }
	
	public Vector<Notiz> getNotizByNotizbuch(String titel) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
