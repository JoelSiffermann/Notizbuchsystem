package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.util.Vector;

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
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(FreigabeID) AS maxid " + "FROM nutzerfreigabe");
			
			if (rs.next()) {
				int i = 1;
				f.setId(rs.getInt("maxid") + i);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO nutzerfreigabe (FreigabeID, FreigegebeneEintragung, Loeschberechtigung, "
						+ "Aenderungsberechtigung, Leseberechtigung, FreigebenderNutzer, FreigegebenerNutzer) " + "VALUES ('" + f.getId() + "','" + 
						f.getFreigegebeneEintragung() + "','" + sqlboolean(f.getLoeschberechtigung()) + "','"
						+ sqlboolean(f.getAenderungsberechtigung()) + "','" + sqlboolean(f.getLeseberechtigung()) + "','" + f.getFreigebenderNutzer() + "','" + f.getFreigegebenerNutzer() + "')");
			}
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return f;
	}
	
	public Freigabe bearbeiten(Freigabe f) {
		Connection con = DBConnection.getConnection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE nutzerfreigabe SET FreigegebeneEintragung=\"" + f.getFreigegebeneEintragung()
					+ "\", " + "Loeschberechtigung=\"" + sqlboolean(f.getLoeschberechtigung()) + "\", " + 
					"Aenderungsberechtigung=\"" + sqlboolean(f.getAenderungsberechtigung()) + "\", " +
					"Leseberechtigung=\"" + sqlboolean(f.getLeseberechtigung()) + "\", " + 
					"FreigegebenerNutzer=\"" + f.getFreigegebenerNutzer() + "\" " +"WHERE FreigabeID=" + f.getId() + "'");
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
			
			stmt.executeUpdate("DELETE FROM nutzerfreigabe WHERE FreigegebeneEintragung='" + f.getFreigegebeneEintragung() + "' OR FreigegebenerNutzer='" + f.getFreigegebenerNutzer() + "'");
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Vector<Freigabe> getFreigabeByEintragung(Freigabe fr) {
		Connection con = DBConnection.getConnection();
		
		Vector<Freigabe> result = new Vector<Freigabe>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT FreigabeID, FreigegebeneEintragung, FreigegebenerNutzer, Leseberechtigung, Aenderungsberechtigung,"
		    		  + "Loeschberechtigung, FreigebenderNutzer FROM nutzerfreigabe WHERE FreigegebeneEintragung = '"
		          + fr.getFreigegebeneEintragung() + "' ORDER BY FreigegebeneEintragung");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Freigabe-Objekt erstellt.
		      while (rs.next()) {
		        Freigabe f = new Freigabe();
		        f.setId(rs.getInt("FreigabeID"));
		        f.setFreigegebeneEintragung(rs.getInt("FreigegebeneEintragung"));
		        f.setFreigegebenerNutzer(rs.getString("FreigegebenerNutzer"));
		        f.setLeseberechtigung(rs.getBoolean("Leseberechtigung"));
		        f.setAenderungsberechtigung(rs.getBoolean("Aenderungsberechtigung"));
		        f.setLoeschberechtigung(rs.getBoolean("Loeschberechtigung"));
		        f.setFreigebenderNutzer(rs.getString("FreigebenderNutzer"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(f);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
	}
	
	public Vector<Freigabe> getFreigabeByNutzer(Nutzer n) {
		Connection con = DBConnection.getConnection();
		
		Vector<Freigabe> result = new Vector<Freigabe>();
		
		try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT FreigabeID, FreigegebeneEintragung, FreigegebenerNutzer, Leseberechtigung, Aenderungsberechtigung,"
		    		  + "Loeschberechtigung, FreigebenderNutzer FROM nutzerfreigabe WHERE FreigegebenerNutzer = '"
		          + n.getEmail() + "' ORDER BY FreigegebeneEintragung");

		      // Für jeden Eintrag im Suchergebnis wird nun ein Freigabe-Objekt erstellt.
		      while (rs.next()) {
		        Freigabe f = new Freigabe();
		        f.setId(rs.getInt("FreigabeID"));
		        f.setFreigegebeneEintragung(rs.getInt("FreigegebeneEintragung"));
		        f.setFreigegebenerNutzer(rs.getString("FreigegebenerNutzer"));
		        f.setLeseberechtigung(javaboolean(rs.getInt("Leseberechtigung")));
		        f.setAenderungsberechtigung(javaboolean(rs.getInt("Aenderungsberechtigung")));
		        f.setLoeschberechtigung(javaboolean(rs.getInt("Loeschberechtigung")));
		        f.setFreigebenderNutzer(rs.getString("FreigebenderNutzer"));
		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(f);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
	}
	
	public int sqlboolean(boolean wert){
		int i = 0;
		if(wert == true){
			i = 1;
		}
		return i;
	}
	
	public boolean javaboolean(int i){
		boolean wert = false;
		if(i==1){
			wert=true;
		}
		return wert;
	}

}
