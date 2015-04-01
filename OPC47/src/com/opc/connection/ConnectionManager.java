package com.opc.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author: Jayarathina Madharasan. Y
 * @Date: January 25, 2012
 * @Copyright_Notice: © 2012 Cognizant, all rights reserved
 * @Description: Database Connectivity code
 */

public class ConnectionManager {
	private static Properties properties = new Properties();
	private static String DB_USERNAME= "";
	private static String DB_PASSWORD= "";
	private static String DB_URL= "";

	public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
		
		/*
		
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/opc");
		Connection connection = ((MysqlDataSource) ds).getConnection();
		
		*/
/*
        Connection connection;
		try {

	        MysqlDataSource mysqlDataSource = new MysqlDataSource();
	 
	        mysqlDataSource.setUser("cts");
	        mysqlDataSource.setPassword("cts");
	        mysqlDataSource.setServerName("localhost");
	        mysqlDataSource.setDatabaseName("opc");
			
			connection = mysqlDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}*/
        
		
//			
//			try {
//			properties.load(new FileInputStream("OPC.properties"));
//			} catch (FileNotFoundException e) 
//			{
//		
//				e.printStackTrace();
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//			
//Implementing new properties
			
			
			try {
				properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("OPC.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		    DB_USERNAME=properties.getProperty("DB_USERNAME");
		    DB_PASSWORD=properties.getProperty("DB_PASSWORD");
		    DB_URL=properties.getProperty("DB_URL");
			
		    System.out.println("DB_USERNAME :" + DB_USERNAME);
		    System.out.println("DB_PASSWORD :" + DB_PASSWORD);
		    System.out.println("DB_URL :" + DB_URL);

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/opc", "root", "password-1");
		Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		

		return connection;
	}
}