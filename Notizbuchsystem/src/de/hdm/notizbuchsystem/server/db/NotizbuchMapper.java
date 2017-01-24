package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(`Eintragung-ID`) AS maxid "
		          + "FROM eintragung ");
			
			if (rs.next()) {
				int i = 1;
				n.setId(rs.getInt("maxid") + i);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO eintragung ( `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`) " + "VALUES ('"
		            + n.getId() + "','" + n.getEigentuemer() + "','" + getSqlDateFormat(n.getModifikationsdatum()) + "','" + getSqlDateFormat(n.getErstelldatum()) + "','" + n.getTitel() + "' )");
				stmt.executeUpdate("INSERT INTO notizbuch (`ID`) VALUES ('" + n.getId() + "')");
			}
		}
		
		catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		return n;
	}
	private String getSqlDateFormat(Date erstelldatum) {
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = dateFormat.format(erstelldatum);
		return result;
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

	

public Vector<Notizbuch> getNotizBuchByTitel(Notizbuch n) {
		
		Connection con = DBConnection.getConnection();
		Vector<Notizbuch> result = new Vector<Notizbuch>();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM Notizbuch WHERE titel LIKE '%" + n.getTitel() + "%' ");
			
			while(rs.next()){
				Notizbuch nb = new Notizbuch();
				nb.setId(rs.getInt("Eintragungs-ID"));
		        nb.setEigentuemer(rs.getString("Eigentuemer"));
		        nb.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
		        nb.setErstelldatum(rs.getDate("Erstelldatum"));
		        nb.setTitel(rs.getString("Titel"));
		        
		        result.addElement(nb);
				}
			} catch (SQLException e1){
				e1.printStackTrace();
			}
			
			return result;
		
		}

	
	
	public Vector<Notizbuch> getNotizbuecherByNutzer(String email){
		
		Connection con = DBConnection.getConnection();
		
		Vector<Notizbuch> result = new Vector<Notizbuch>();
		try{
			Statement stmt = con.createStatement();
			
		      ResultSet rs = stmt.executeQuery("SELECT `Eintragung-ID`, `Eigentuemer`, `Modifikationsdatum`, `Erstelldatum`, `Titel`"
		    		  + "FROM eintragung INNER JOIN notizbuchdb.notizbuch ON `Eintragung-ID` = `ID` WHERE Eigentuemer ='" + email
		          + "' ORDER BY `Eintragung-ID`");
		      
		      while(rs.next()){
		    	  Notizbuch e = new Notizbuch();
			        e.setId(rs.getInt("Eintragung-ID"));
			        e.setEigentuemer(rs.getString("Eigentuemer"));
			        e.setModifikationsdatum(rs.getDate("Modifikationsdatum"));
			        e.setErstelldatum(rs.getDate("Erstelldatum"));
			        e.setTitel(rs.getString("Titel"));
		      
		      result.addElement(e);
		      }
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
			
	}
	
	
}
