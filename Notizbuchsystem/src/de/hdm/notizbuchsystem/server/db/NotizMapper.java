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
			
			
			// SQL Statement muss noch eingefügt werden
			
			
			ResultSet rs = stmt.executeQuery("sql");
			
			if (rs.next()) {
				
				n.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("sql");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	
	public Notiz bearbeiten(Notiz n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("sql");
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
			
			stmt.executeUpdate("SQL");
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
