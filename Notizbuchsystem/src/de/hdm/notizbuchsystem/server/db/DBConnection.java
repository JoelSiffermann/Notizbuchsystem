package de.hdm.notizbuchsystem.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class DBConnection {
	   // Zugangsdaten für DB anlegen 
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "207.223.163.76";
	   static final String USER = "NBS";
	   static final String PASS = "notiz";
	   
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
	   Connection conn = null;
	   
	   
	   Class.forName("com.mysql.jdbc.Driver");
	   
	   
	   conn = DriverManager.getConnection(DB_URL,USER,PASS);
	   return conn;   
	   }}