package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;

public class FaelligkeitMapper {

private static FaelligkeitMapper faelligkeitMapper = null;
	
	protected FaelligkeitMapper() {
		
	}
	
	public static FaelligkeitMapper faelligkeitMapper() {
		if (faelligkeitMapper == null) {
			faelligkeitMapper = new FaelligkeitMapper();
		}
		
	return faelligkeitMapper;
	}
	
	public Faelligkeit erstellen(Faelligkeit f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM Faelligkeit");
			
			if (rs.next()) {
				
				f.setId(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO Faelligkeit (Faelligkeit-ID, Datum) " + "VALUES (" + f.getId()
						 + "," + f.getDatum() + " )");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return f;
	}
	
	public Faelligkeit bearbeiten(Faelligkeit f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE Faelligkeit " + "SET Datum=\"" + f.getDatum() + "\" " + "WHERE id=" + f.getId());
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return f;
	}
	
	public void loeschen(Faelligkeit f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM Faelligkeit " + "WHERE id=" + f.getId());
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Vector<Faelligkeit> getFaelligkeitByDatum(Date datum) {
		// TODO Auto-generated method stub
		return null;
	}

	public Faelligkeit insertNotizquelle(Faelligkeit faelligkeit,
			int faelligkeitId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void loescheFaelligkeit(int faelligkeitId) {
		// TODO Auto-generated method stub
		
	}
}
