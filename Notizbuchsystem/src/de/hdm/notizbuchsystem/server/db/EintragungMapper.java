package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;

public class EintragungMapper {

	private static EintragungMapper eintragungMapper = null;
	
	protected EintragungMapper() {
		
	}
	
	public static EintragungMapper eintragungMapper() {
		if (eintragungMapper == null) {
			eintragungMapper = new EintragungMapper();
		}
		
	return eintragungMapper;
	}
	
	public Eintragung erstellen(Eintragung e) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Eintragung");
			
			if (rs.next()) {
				
				e.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("sql");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return e;
	}
	
	public Eintragung bearbeiten(Eintragung e) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("sql");
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return e;
	}
	
	public void loeschen(Eintragung e) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Eintragung " + "WHERE id=" + e.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
