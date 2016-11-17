package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.notizbuchsystem.shared.bo.*;

public class NotizquelleMapper {

private static NotizquelleMapper notizquelleMapper = null;
	
	protected NotizquelleMapper() {
		
	}
	
	public static NotizquelleMapper notizquelleMapper() {
		if (notizquelleMapper == null) {
			notizquelleMapper = new NotizquelleMapper();
		}
		
	return notizquelleMapper;
	}
	
	public Notizquelle erstellen(Notizquelle n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Notizquelle");
			
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
	
	public Notizquelle bearbeiten(Notizquelle n) {
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
	
	public void loeschen(Notizquelle n) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Notizquelle " + "WHERE id=" + n.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
}
