package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
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
			
			
					
			
			ResultSet rs = stmt.executeQuery("SELECT MAX('Eintragung-ID') AS maxid "
		          + "FROM eintragung ");
			
			if (rs.next()) {
				
				n.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO eintragung ( `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`, `Subtitel`) " + "VALUES ('"
		            + n.getId() + "','" + n.getEigentuemer() + "','" + n.getModifikationsdatum() + "','" + n.getErstelldatum() + "','" + n.getTitel() + "','" + n.getSubtitel() + "' )");
				stmt.executeUpdate("INSERT INTO notiz (`ID`, `Inhalt`) VALUES ('" + n.getId() + "','" + n.getInhalt() + "')");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	// Es können bis jetzt nur Modifikationsdatum, Titel, Subtitel und Inhalt bearbeitet werden, da die restlichen Werte nicht veränderbar sein sollten
	public Notiz bearbeiten(Notiz n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE Notiz" + "SET Titel=\""
          + n.getTitel() + "\", " + "Modifikationsdatum=\"" +n.getModifikationsdatum()+ "\", " + "Subtitel=\"" + n.getSubtitel()+ "\", " + "SET Inhalt=\"" + n.getInhalt()
          + "WHERE id=" + n.getId());
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

	public Vector<Notiz> getNotizenByNutzer(Nutzer n) {
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> result = new Vector<Notiz>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt"
		    		  + "Eigentuemer FROM Eintragung WHERE Eigentuemer =" + n.getEmail()
		          + " ORDER BY Eintragung-ID");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
		      while (rs.next()) {
		        Notiz e = new Notiz();
		        e.setId(rs.getInt("Eintragungs-ID"));
		        e.setEigentuemer(rs.getString("Eigentuemer"));
		        e.setModifikationsdatum(rs.getTimestamp("Modifikationsdatum"));
		        e.setErstelldatum(rs.getTimestamp("Erstelldatum"));
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
	

	public Vector<Notiz> getNotizByTitel(String titel) {
		
		Connection con = DBConnection.getConnection();
		Vector<Notiz> result = new Vector<Notiz>();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Notiz WHERE titel LIKE '%" + titel + "%' ");
			
			while(rs.next()){
				Notiz n = new Notiz();
				n.setId(rs.getInt("Eintragungs-ID"));
		        n.setEigentuemer(rs.getString("Eigentuemer"));
		        n.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        n.setErstelldatum(rs.getDate("Erstelldatum"));
		        n.setTitel(rs.getString("Titel"));
		        n.setSubtitel(rs.getString("Subtitel"));
		        n.setInhalt(rs.getString("Inhalt"));
		        
		        result.addElement(n);
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
	

	public Notiz zuweisen(Notizbuch notizbuch) {
		// TODO Auto-generated method stub
		return null;
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
		//TODO Kriterium Object bzw Klasse anlegen mit den attributen
		//TODO Map mit Notiz und Faelligkeit siehe Partnerboerse
		Connection con = DBConnection.getConnection();
		
		Vector<Notiz> notiz = new Vector<Notiz>();
		Vector<Faelligkeit> v = new Vector<Faelligkeit>();
		Map<Vector<Notiz>, Vector<Faelligkeit>> result = new HashMap<Vector<Notiz>, Vector<Faelligkeit>>();
		
		StringBuffer whereQuery = new StringBuffer("WHERE Eintragung-ID IS NOT NULL");
		
		String titelQuery = "";
		String eQuery = "";
		String mQuery = "";
		String fQuery = "";
		
				if(titel != null){
					titelQuery = "Titel LIKE '%" + titel + "%' ";
					whereQuery.append(" AND " + titelQuery);
			        
				} 
				if(edatum != null) {
					eQuery = "Erstelldatum = " + edatum;
					whereQuery.append(" AND " + eQuery);
					
				}
				if(mdatum != null) {
					mQuery = "Modifikationsdatum = " + mdatum;
					whereQuery.append(" AND " + mQuery);
					
				} 
				if(fdatum != null) {
					fQuery = "Faelligkeit.Datum = " + fdatum;
					whereQuery.append(" AND " + fQuery);
				}
				try {
					Statement stmt = con.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt, Faelligkeit.Datum"
				    		  + "Eigentuemer FROM Eintragung INNER JOIN Notiz ON Eintragung-ID = Notiz-ID LEFT JOIN Faelligkeit ON Notiz-ID=Faelligkeit.Notiz-ID"
				          + whereQuery + " ORDER BY Eintragung-ID");
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

	public Vector<Notiz> getNotizByNotizbuch(String titel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Notiz insertNotiz(Notiz notiz, int notizId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public void loeschenotiz(int notizId) {
		// TODO Auto-generated method stub
		
	}
	
	
}
