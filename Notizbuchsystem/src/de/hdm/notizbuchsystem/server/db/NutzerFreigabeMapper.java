package de.hdm.notizbuchsystem.server.db;

import java.sql.*;

import de.hdm.notizbuchsystem.shared.bo.*;

public class NutzerFreigabeMapper {

private static NutzerFreigabeMapper nutzerFreigabeMapper = null;
	
	protected NutzerFreigabeMapper() {
		
	}
	
	public static NutzerFreigabeMapper nutzerFreigabeMapper() {
		if (nutzerFreigabeMapper == null) {
			nutzerFreigabeMapper = new NutzerFreigabeMapper();
		}
		
	return nutzerFreigabeMapper;
	}
	//TODO NutzerFreigabe Klasse im shared.bo Package erstellen

	
// Muss überarbeitet werden	
//	public Freigabe erstellen(Freigabe nf) {
//		Connection con = DBConnection.getConnection();
//		
//		try{
//			Statement stmt = con.createStatement();
//			//TODO Lösung für ID finden
//			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM NutzerFreigabe");
//			
//			if (rs.next()) {
//				
//				nf.setId(rs.getInt("maxid") + 1);
//				
//				stmt = con.createStatement();
//				
//				stmt.executeUpdate("INSERT INTO NutzerFreigabe (NutzerFreigabe-ID, Freigabe-ID, Nutzer-ID) + "VALUES + ("nf.getId() + "," + nf.getNutzerID() + "," + 
//						nf.getFreigabeID() + " )");
//			}
//		}
//		
//		catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		return nf;
//	}
//	
//	public Freigabe bearbeiten(Freigabe nf) {
//		Connection con = DBConnection.getConnection();
//		
//		try{
//			Statement stmt = con.createStatement();
//			
//			stmt.executeUpdate("UPDATE NutzerFreigabe " + "SET Nutzer-ID=\"" + nf.getNutzerID()
//					+ "\", " + "Freigabe-ID=\"" + nf.getLFreigabeID() + "\" " + "WHERE id=" + nf.getId());
//		}
//		
//		catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		
//		return nf;
//	}
//	
//	public void loeschen(Freigabe nf) {
//		Connection con = DBConnection.getConnection();
//		
//		try{
//			Statement stmt = con.createStatement();
//			
//			stmt.executeUpdate("DELETE FROM NutzerFreigabe " + "WHERE id=" + nf.getId());
//		}
//		catch(SQLException e1) {
//			e1.printStackTrace();
//		}
//	}
}

