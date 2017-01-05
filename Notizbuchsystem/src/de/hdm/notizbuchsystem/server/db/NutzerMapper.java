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
		 
		ResultSet rs = stmt.executeQuery("SELECT MAX(Nutzer.ID) AS maxid "
		          + "FROM Nutzer ");
		
		   if (rs.next()) {
		     nutzer.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        
		        stmt.executeUpdate("INSERT INTO Nutzer (ID, Name, Vorname, E-Mail) " + "VALUES ("
		            + nutzer.getId() + "," + nutzer.getName() + "," + nutzer.getVorname() + nutzer.getEmailAddress() +" )");
		      }}
		   catch (SQLException e1) {
			      e1.printStackTrace();
			    }
	return nutzer;
}
	

public Nutzer bearbeiten(Nutzer nutzer) {
    Connection con = DBConnection.getConnection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE Nutzer " + "SET Name=\""
          + nutzer.getName() + "\", " + "Vorname=\"" +nutzer.getVorname() + "\" "
          + "WHERE id=" + nutzer.getId());

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

      stmt.executeUpdate("DELETE FROM Nutzer " + "WHERE id=" + nutzer.getId());
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


public Vector<Nutzer> getNutzerByName(String name, String vorname) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE name=" + name + " ORDER BY Name");

      // Methode zum pr�fen ob ein Ergebnis vorliegt nicht implementiert, da Name kein PK ist
      
    }
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return null;
}

public Nutzer getNutzerByEmail(String Email) {
    
    Connection con = DBConnection.getConnection();

    try {
    	
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, Name, Vorname, Email FROM Nutzer "
              + "WHERE E-Mail=" + Email + " ORDER BY Name");

      // Methode zum pr�fen ob ein Ergebnis vorliegt nicht implementiert, da Email kein PK ist
      
    }
    catch (SQLException e) {
          e.printStackTrace();
          return null;  }

	return null;
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
