package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.notizbuchsystem.shared.bo.*;


public class NutzerMapper {


private static NutzerMapper nutzerMapper = null;
	
	
protected NutzerMapper(){
	
}	
	
public static NutzerMapper nutzerMapper(){
	 if (nutzerMapper == null){
		nutzerMapper = new NutzerMapper();
		
	 }
	 return nutzerMapper;
}

public Nutzer erstellen(Nutzer nutzer) {
	Connection con = DBConnection.getConnection();
	
	try{
		Statement stmt = con.createStatement();
		 

		stmt = con.createStatement();
		stmt.executeUpdate("INSERT INTO notizbuchdb.nutzer (Name, Vorname, EMail) " + " VALUES ('"
		            + nutzer.getName() + "','" + nutzer.getVorname() + "','" + nutzer.getEmail() +"' )");

		      }
		   catch (SQLException e1) {
			      e1.printStackTrace();
			    }
	return nutzer;
}
	

public Nutzer bearbeiten(Nutzer nutzer) {
    Connection con = DBConnection.getConnection();

    try {
      Statement stmt = con.createStatement();


      stmt.executeUpdate("UPDATE nutzer SET Name=\""
          + nutzer.getName() + "\", Vorname=\"" +nutzer.getVorname() + "\" "
          + "WHERE `Email`='" + nutzer.getEmail() + "'");


    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return nutzer;
  }


public void loeschen(Nutzer nutzer) {
    Connection con = DBConnection.getConnection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM nutzerfreigabe " + "WHERE `FreigebenderNutzer`='" + nutzer.getEmail() + "'");
      stmt.executeUpdate("DELETE faelligkeit FROM notizbuchdb.faelligkeit faelligkeit JOIN eintragung ON faelligkeit.`Eintragung` = eintragung.`Eintragung-ID` WHERE eintragung.Eigentuemer ='" + nutzer.getEmail() + "'");
      stmt.executeUpdate("DELETE notiz FROM notizbuchdb.notiz notiz JOIN eintragung ON notiz.`ID` = eintragung.`Eintragung-ID` WHERE eintragung.Eigentuemer ='" + nutzer.getEmail() + "'");
      stmt.executeUpdate("DELETE notizbuch FROM notizbuchdb.notizbuch notizbuch JOIN eintragung ON notizbuch.`ID` = eintragung.`Eintragung-ID` WHERE eintragung.Eigentuemer ='" + nutzer.getEmail() + "'");
      stmt.executeUpdate("DELETE FROM eintragung " + "WHERE `Eigentuemer`='" + nutzer.getEmail() + "'");
      stmt.executeUpdate("DELETE FROM nutzer WHERE Email='" + nutzer.getEmail() + "'");
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

public Vector<Nutzer> getNutzerByName(Nutzer n) {
    
    Connection con = DBConnection.getConnection();
    Vector<Nutzer> result = new Vector<Nutzer>();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT * FROM Nutzer WHERE name LIKE '%" + n.getName() + "%' ");

      while(rs.next()){
    	  Nutzer n1 = new Nutzer();
    	  n1.setEmail(rs.getString("Email"));
    	  n1.setName(rs.getString("name"));
    	  n1.setVorname(rs.getString("vorname"));
    	  result.add(n1);
      }
      
    }
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return result;
}

public Nutzer getNutzerByEmail(Nutzer n) {
    
    Connection con = DBConnection.getConnection();
    
    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt

          .executeQuery("SELECT * FROM nutzer WHERE Email = '" + n.getEmail() + "' ");


    if(rs.next()) {  
      
   
   n.setEmail(rs.getString("Email"));
   n.setName(rs.getString("Name"));
   n.setVorname(rs.getString("Vorname"));
   return n;
   
    }}
      
	
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return null;
}

public Vector<Nutzer> getAllNutzer() {

    Connection con = DBConnection.getConnection();
    
    Vector<Nutzer> v = new Vector<Nutzer>();
    
    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt

          .executeQuery("SELECT * FROM Nutzer");


    while (rs.next()) {  
      
   Nutzer n = new Nutzer();
   n.setEmail(rs.getString("Email"));
   n.setName(rs.getString("Name"));
   n.setVorname(rs.getString("Vorname"));
   v.add(n);
   
    }}
      
	
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return v;
}



}
