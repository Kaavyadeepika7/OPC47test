package com.opc.company;

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
 * @Description: Company Profile - To Delete the Company Profile
 */
public class CompanyDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//Getting the session attribute
		String user_typ = request.getSession().getAttribute("TYPE") + "";
		if (!user_typ.equalsIgnoreCase("A")) {
			response.sendRedirect("index.jsp?msg=" + 8);//You are not authorized to view this page
		}

		String id = request.getParameter("C_ID");
		Connection conn = null;
		Statement statement = null;
		try {

			conn = ConnectionManager.getConnection();

			statement = conn.createStatement();

			// SQL query to retrieve values from the specified table.

			int i = statement.executeUpdate("DELETE FROM OPC_COMP_PROF WHERE C_ID =  "
					+ id + " ");
			if (i > 0)
				response.sendRedirect("index.jsp?msg=B");//Deleted Successfully
			else
				response.sendRedirect("index.jsp?msg=C");//Not Deleted. No records matched for your input
				
		}

		catch (Exception ex) {
			//response.sendRedirect("index.jsp?msg=C");
			String err = ex.toString();
			//Save the error message in LOG 
			//Logger.getLogger(CompanyDel.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			if (err.indexOf("(PT002.C_ID_FK_01) violated - child record found") != -1)
				response.sendRedirect("index.jsp?msg=I");//Cannot delete company, because it is present in the schedule
			else
				response.sendRedirect("index.jsp?msg=C");//Not Deleted. No records matched for your input
					
		}
		finally
		{
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				//Save the error message in LOG 
				//Logger.getLogger(CompanyDel.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		}

	}

}
