package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.util.Date;
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
			
			
					
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(Notiz.Eintragung-ID) AS maxid "
		          + "FROM Notiz ");
			
			if (rs.next()) {
				
				n.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO Notiz (Eintragung-ID, Eigentümer, Modifikationsdatum, Erstelldatum, Titel, Subtitel, Inhalt) " + "VALUES ("
		            + n.getId() + "," + n.getEigentuemer() + "," + n.getModifikationsdatum() + n.getErstelldatum() + "," + n.getTitel() + "," + n.getSubtitel() + "," + n.getInhalt() +" )");
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
	
	
	public Notiz zuweisen(Notizbuch notizbuch) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Notiz> getNotizByErstelldatum(Date erstelldatum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Notiz> getNotizByModifikationsdatum(Date modifikationsdatum) {
		// TODO Auto-generated method stub
		return null;
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
