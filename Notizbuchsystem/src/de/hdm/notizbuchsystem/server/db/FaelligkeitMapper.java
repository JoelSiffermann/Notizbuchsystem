package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxid " + "FROM faelligkeit");
			
			if (rs.next()) {
				int i = 1;
				f.setId(rs.getInt("maxid") + i);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO faelligkeit (ID, Datum, Eintragung) " + "VALUES ('" + f.getId()
						 + "','" + getSqlDateFormat(f.getDatum()) + "','" + f.getNotiz() + "')");
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
			
			stmt.executeUpdate("UPDATE faelligkeit " + "SET Datum=\"" + getSqlDateFormat(f.getDatum()) + "\" " + "WHERE Eintragung='" + f.getNotiz() + "'");
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
			
			stmt.executeUpdate("DELETE FROM faelligkeit " + "WHERE Eintragung='" + f.getNotiz() + "'");
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public Faelligkeit getFaelligkeitByNotiz(Notiz n) {
		
		Connection con = DBConnection.getConnection();
		Faelligkeit f = new Faelligkeit();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = 
			stmt.executeQuery("SELECT * FROM notizbuchdb.faelligkeit WHERE faelligkeit.`Eintragung` = '" + n.getId() + "'");
			
			if(rs.next()){
				f.setId(rs.getInt("ID"));
				f.setDatum(rs.getDate("Datum"));
				f.setNotiz(rs.getInt("Eintragung"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public Vector<Faelligkeit> getFaelligkeitByDatum(Faelligkeit f) {
		Connection con = DBConnection.getConnection();
		
		Vector<Faelligkeit> result = new Vector<Faelligkeit>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM faelligkeit WHERE Eintragung = '"
		          + f.getNotiz() + "' ORDER BY Datum");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Faelligkeit-Objekt erstellt.
		      while (rs.next()) {
		        Faelligkeit fa = new Faelligkeit();
		        fa.setId(rs.getInt("ID"));
		        fa.setDatum(rs.getDate("Datum"));
		        fa.setNotiz(rs.getInt("Eintragung"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(fa);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
	}
	
	private String getSqlDateFormat(Date erstelldatum) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(erstelldatum);
		return result;
	}

	
}
