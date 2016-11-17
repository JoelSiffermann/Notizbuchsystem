package de.hdm.notizbuchsystem.server.db;

import java.sql.*;
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

public Nutzer erstellen(Nutzer n) {
	Connection con = DBConnection.getConnection();
	
	try{
		Statement stmt = con.createStatement();
		 
		ResultSet rs = stmt.executeQuery("SELECT MAX(Nutzer.ID) AS maxid "
		          + "FROM Nutzer ");
		
		   if (rs.next()) {
		     n.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        
		        stmt.executeUpdate("INSERT INTO Nutzer (ID, Name, Vorname, E-Mail) " + "VALUES ("
		            + n.getId() + "," + n.getName() + "," + n.getVorname() + n.getEmail() +" )");
		      }}
		   catch (SQLException e1) {
			      e1.printStackTrace();
			    }
	return n;
}
	

public Nutzer bearbeiten(Nutzer n) {
    Connection con = DBConnection.getConnection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Nutzer " + "SET Name=\""
          + n.getName() + "\", " + "Vorname=\"" +n.getVorname() + "\" "
          + "WHERE id=" + n.getId());

    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return n;
  }


public void löschen(Nutzer n) {
    Connection con = DBConnection.getConnection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM Nutzer " + "WHERE id=" + n.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }


// Methode zum suchen und ausgeben eines bestimmten Nutzers anhand seiner ID
public Customer getNutzerByID(int ID) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE id=" + ID + " ORDER BY Name");

      /*
       * Da id PrimÃ¤rschlÃ¼ssel ist, kann max. nur ein Tupel zurÃ¼ckgegeben
       * werden. PrÃ¼fe, ob ein Ergebnis vorliegt.
       */
      if (rs.next()) {
        // Ergebnis-Tupel in Objekt umwandeln
        Nutzer n = new Nutzer();
        n.setId(rs.getInt("ID"));
        n.setFirstName(rs.getString("Name"));
        n.setLastName(rs.getString("Vorname"));

        return n;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }


public Nutzer getNutzerByName(String name) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE name=" + name + " ORDER BY Name");

      // Methode zum prüfen ob ein Ergebnis vorliegt nicht implementiert, da Name kein PK ist
      
    }
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return null;
}

public Nutzer getNutzerByName(String Email) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE E-Mail=" + Email + " ORDER BY Name");

      // Methode zum prüfen ob ein Ergebnis vorliegt nicht implementiert, da Email kein PK ist
      
    }
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return null;
}

}
