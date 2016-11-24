package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
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
		            + n.getId() + "," + n.getEigentuemer() + "," + n.getModifikationsdatum() + n.getErstelldatum() + "," n.getTitel() + "," n.getSubtitel() + "," n.getInhalt() +" )");
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
	
	
}
