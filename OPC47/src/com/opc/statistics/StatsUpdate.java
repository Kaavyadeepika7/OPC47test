package com.opc.statistics;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opc.connection.ConnectionManager;

/**
 * 
 * @author: Jayarathina Madharasan. Y
 * @Date: January 30, 2012
 * @Copyright Notice: © 2012 Cognizant, all rights reserved
 * @Description: Statistics - Adding a new stats data to the table
 */

public class StatsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
     	String C_Name = request.getParameter("C_Name");
      	String YEAR = request.getParameter("YEAR");
      	String N_STUDENTS = request.getParameter("N_STUDENTS");

      	try {

			Connection conn = ConnectionManager.getConnection();
			Statement statement = conn.createStatement();
			
			statement
				.executeUpdate("INSERT INTO OPC_STATS (C_NAME,YEAR,N_STUDENTS) VALUES ('"
						+ C_Name
						+ "','"
						+ YEAR
						+ "','"
						+ N_STUDENTS
						+ "')");
			
      		statement.close();
      		conn.close();
      		response.sendRedirect("index.jsp?msg=7");
      	} catch (Exception ex) {
      		//Logger.getLogger(StatsUpdate.class.getName()).log(Level.SEVERE, null, ex);
      		ex.printStackTrace();
      		response.sendRedirect("index.jsp?msg=9");
      	}
	}

}
