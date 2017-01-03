package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;

public class NotizbuchMapper {

	private static NotizbuchMapper notizbuchMapper = null;
	
	protected NotizbuchMapper() {
		
	}
	
	public static NotizbuchMapper notizbuchMapper() {
		if (notizbuchMapper == null) {
			notizbuchMapper = new NotizbuchMapper();
		}
		
	return notizbuchMapper;
	}
	
	public Notizbuch erstellen(Notizbuch n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			
			// SQL Statement muss noch eingefügt werden
			
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(Notizbuch.Eintragung-ID) AS maxid "
			          + "FROM Notizbuch");
			
			if (rs.next()) {
				
				n.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO Notizbuch (Eintragung-ID, Eigentuemer, Modifikationsdatum, Erstelldatum, Titel) " + "VALUES ("
				            + n.getId() + "," + n.getEigentuemer() + "," + n.getModifikationsdatum() + "," + n.getErstelldatum() + "," + n.getTitel());
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	
	// Es könne nur die Attribute Titel, Modifikationsdatum und Erstelldatum geändert werden, da die restlichen Attribute nicht veränderbar sein sollten
	
	public Notizbuch bearbeiten(Notizbuch n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE Notizbuch" + "SET Titel=\""
			          + n.getTitel() + "\", " + "Modifikationsdatum=\"" +n.getModifikationsdatum()+ "\", " + "Erstelldatum=\"" +n.getErstelldatum()+ ""
			          + "WHERE id=" + n.getId());
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	
	public void loeschen(Notizbuch n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELTE FROM Notizbuch WHERE ID=" + n.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Vector<Notiz> getNotizByTitel(String titel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Notizbuch> getNotizbuchByTitel(String titel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
