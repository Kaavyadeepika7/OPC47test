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
 * @Description: Upcoming Events - Deleting a Schedule
 */
public class ScheduleDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//getting session attributes
		String user_typ = request.getSession().getAttribute("TYPE") + "";
		if (!user_typ.equalsIgnoreCase("A")) {
			//display You are not authorized to view this page
			response.sendRedirect("index.jsp?msg=" + 8);
		}

		String id = request.getParameter("S_ID");
		Connection conn = null;
			Statement statement =null;
		try {

			 conn = ConnectionManager.getConnection();

		 statement = conn.createStatement();
			int i = statement
					.executeUpdate("DELETE FROM OPC_schedule WHERE S_ID =  "
							+ id + " ");
			if (i > 0)
				//Display Deleted Sucessfully
				response.sendRedirect("index.jsp?msg=B");
			else
				//No records matched for your input
				response.sendRedirect("index.jsp?msg=C");

			
		}

		catch (Exception e) {
			//save the error message in the log
			//Logger.getLogger(ScheduleDel.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			response.sendRedirect("index.jsp?msg=C");
		}
		finally
		{
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				//save the error message in the log
				//Logger.getLogger(ScheduleDel.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		}

	}

}
