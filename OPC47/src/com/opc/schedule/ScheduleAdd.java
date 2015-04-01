package com.opc.schedule;

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
 * @Date: January 25, 2012
 * @Copyright_Notice: © 2012 Cognizant, all rights reserved
 * @Description: Upcoming Events - Adding a new Schedule
 */

public class ScheduleAdd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//getting session attributes
        String user_typ =  request.getSession().getAttribute("TYPE") + "";
        if (!user_typ.equalsIgnoreCase("A")) {
                response.sendRedirect("index.jsp?msg=" + 8);
        }
		
		String C_ID = request.getParameter("C_ID");
		String C_Date = request.getParameter("C_Date");
		String C_Venue = request.getParameter("C_Venue");
		
		Connection conn = null;
		Statement statement = null;
		try {

			// sql query to insert values for the specified table.
			conn = ConnectionManager.getConnection();

			statement = conn.createStatement();
			
			statement
					.executeUpdate( "INSERT INTO OPC_Schedule(C_ID,C_Date,C_Venue) VALUES ( '" //S_ID
							+ C_ID
							+ "', "
							+ "'" + C_Date + "', '" + C_Venue + "')" );

			//Display Information Saved Sucessfully
			response.sendRedirect("index.jsp?msg=7");
		} catch (Exception ex) {
			//save the error message in the log
			//Logger.getLogger(ScheduleAdd.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			if(ex.toString().indexOf("integrity constraint (PT002.C_ID_FK_01)") != -1){
				response.sendRedirect("index.jsp?msg=H");
			}else{
				response.sendRedirect("index.jsp?msg=G");
			}
		}
			finally
			{
				try {
					statement.close();
					conn.close();
				} catch (Exception e) {
					//save the error message in the log
					//Logger.getLogger(ScheduleAdd.class.getName()).log(Level.SEVERE, null, e);
					e.printStackTrace();
				}
			}
		}
	}


