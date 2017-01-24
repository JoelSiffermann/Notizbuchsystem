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
						f.getFreigegebeneEintragung() + "','" + f.getLoeschberechtigung() + "','"
						+ f.getAenderungsberechtigung() + "','" + f.getLeseberechtigung() + "','" + f.getFreigebenderNutzer() + "','" + f.getFreigegebenerNutzer() + "')");
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
			
			stmt.executeUpdate("UPDATE nutzerfreigabe " + "SET FreigegebeneEintragung=\"" + f.getFreigegebeneEintragung()
					+ "\", " + "Loeschberechtigung=\"" + f.getLoeschberechtigung() + "\", " + 
					"Aenderungsberechtigung=\"" + f.getAenderungsberechtigung() + "\", " +
					"Leseberechtigung=\"" + f.getLeseberechtigung() + "\", " + 
					"FreigegebenerNutzer=\"" + f.getFreigegebenerNutzer() + "\" " +"WHERE FreigabeID=" + f.getId());
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
			
			stmt.executeUpdate("DELETE FROM nutzerfreigabe " + "WHERE FreigabeID='" + f.getId() + "'");
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

	public Vector<Freigabe> getFreigabeByNotiz(Notiz notiz) {
		// TODO Auto-generated method stub
		return null;
	}

	public Freigabe insertNotizFreigabe(Freigabe notizFreigabe,
			int notizFreigabeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Freigabe insertNotizbuchFreigabe(Freigabe notizbuchFreigabe,
			int notizbuchFreigabeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void loescheNotizFreigabe(int notizFreigabeId) {
		// TODO Auto-generated method stub
		
	}

	public void loescheNotizbuchFreigabe(int notizbuchFreigabeId) {
		// TODO Auto-generated method stub
		
	}

	public void bearbeiteNotizbuchFreigabe(NotizbuchFreigabe notizbuchFreigabe) {
		// TODO Auto-generated method stub
		
	}
}
