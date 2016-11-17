package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.notizbuchsystem.shared.bo.*;

public class FreigabeMapper {

private static FreigabeMapper freigabeMapper = null;
	
	protected FreigabeMapper() {
		
	}
	
	public static FreigabeMapper freigabeMapper() {
		if (freigabeMapper == null) {
			freigabeMapper = new FreigabeMapper();
		}
		
	return freigabeMapper;
	}
	
	public Freigabe erstellen(Freigabe f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Freigabe");
			
			if (rs.next()) {
				
				f.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("sql");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return f;
	}
	
	public Freigabe bearbeiten(Freigabe f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("sql");
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return f;
	}
	
	public void loeschen(Freigabe f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Freigabe " + "WHERE id=" + f.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
}
