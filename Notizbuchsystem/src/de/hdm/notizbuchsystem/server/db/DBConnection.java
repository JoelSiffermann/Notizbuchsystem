package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;





public class DBConnection {
	   // Zugangsdaten f�r DB anlegen 
//	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   private static String DB_URL = "jdbc:mysql://207.223.163.76:3306/notizbuch-db/NotizbuchDBtest?user=NBS&password=notiz";
//	   static final String USER = "NBS";
//	   static final String PASS = "notiz";
//	   static final String Local_URL = "Noch nicht erstellt";
	   
	   private static Connection con = null;
	   
	   public static Connection getConnection() {

				if (con == null) {
					String url = null;
					try {
						if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
							
							Class.forName("com.mysql.jdbc.GoogleDriver");
							url = DB_URL;
							
						} else {
//							Class.forName("com.mysql.jdbc.Driver");
//							url = DB_URL;						
					}
						
						con = DriverManager.getConnection(url);
					} catch (Exception e) {
						con = null;
						e.printStackTrace();
					}
				}

				return con;
			}
		}

