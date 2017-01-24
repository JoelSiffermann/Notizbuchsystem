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


      stmt.executeUpdate("UPDATE nutzer " + "SET Name=\""
          + nutzer.getName() + "\", " + "Vorname=\"" +nutzer.getVorname() + "\" "
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


// Methode zum suchen und ausgeben eines bestimmten Nutzers anhand seiner ID
public Nutzer getNutzerByID(int ID) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE id=" + ID + " ORDER BY Name");

      /*
       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
       * werden. Prüfe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Nutzer nutzer = new Nutzer();
        nutzer.setId(rs.getInt("ID"));
        nutzer.setName(rs.getString("Name"));
        nutzer.setVorname(rs.getString("Vorname"));

        return nutzer;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
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

          .executeQuery("SELECT * FROM Notiz WHERE Email LIKE '%" + n.getEmail() + "%' ");


    if(rs.next()) {  
      
   
   n.setEmail(rs.getString("Email"));
   n.setName(rs.getString("Name"));
   n.setVorname(rs.getString("Vorname"));
   
    }}
      
	
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return n;
}

public Nutzer insertNutzer(Nutzer nutzer) {
	// TODO Auto-generated method stub
	return null;
}


public Nutzer findByNutzerMitEmail(String email) {
	// TODO Auto-generated method stub
	return null;
}

public void updateNutzer(Nutzer nutzer) {
	// TODO Auto-generated method stub
	
}

public void loeschenutzer(int nutzerId) {
	// TODO Auto-generated method stub
	
}

public Nutzer findByNutzerId(int nutzerId) {
	// TODO Auto-generated method stub
	return null;
}

}
