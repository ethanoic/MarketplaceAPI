package com.sp.marketplaceapi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.ServletContext;

import com.sp.marketplaceapi.application.AppPropertyValues;

public class ConnectionManager {
	
	public static Connection Get() {
		Connection conn = null;
		
		AppPropertyValues configValues = new AppPropertyValues();
		Dictionary config = new Hashtable();
		
		try {
			config = configValues.getPropValues();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		// config must not be empty
		if (!config.isEmpty()) {
			String dbHostname = config.get("dbHostname").toString(); 
			String dbSchema = config.get("dbSchema").toString();
			String dbUser = config.get("dbUser").toString();
			String dbPassword = config.get("dbPassword").toString();

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			try {
			    conn =
			       DriverManager.getConnection("jdbc:mysql://" + dbHostname + "/" + dbSchema + "?" +
			                                   "user=" + dbUser + "&password=" + dbPassword + "&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");

			    // Do something with the Connection

			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		
		
		return conn;
	}
	
}
