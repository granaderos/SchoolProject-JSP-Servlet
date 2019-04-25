package com.mare.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	
	public Connection getConnection() {
		Connection connection = null;
		String dbname = "school_project";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			DriverManager.setLoginTimeout(10);
			connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/"+dbname, "root", "");
			System.out.println("Connection: " + connection);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
