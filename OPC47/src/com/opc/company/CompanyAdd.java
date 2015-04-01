package com.opc.company;

import java.io.IOException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @Description: Company Profile - To insert the Company Profile
 */

public class CompanyAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user_typ = request.getSession().getAttribute("TYPE") + "";
		if (!user_typ.equalsIgnoreCase("A"))
			response.sendRedirect("index.jsp?msg=" + 8); // You are not authorized to view this page

		// get parameter from the request
		String C_Name = request.getParameter("C_Name");
		String C_HR_Name = request.getParameter("C_HR_Name");
		String C_Email = request.getParameter("C_Email");
		String C_URL = request.getParameter("C_URL");
		String C_HR_Phone = request.getParameter("C_HR_Phone");

		// Validating email

		String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(C_Email);
		if (!matcher.matches()) {
			response.sendRedirect("index.jsp?msg=E");
		}
		try {
			new java.net.URI(C_URL);
		} catch (Exception e) {
			//Logger.getLogger(CompanyAdd.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			response.sendRedirect("index.jsp?msg=F");
		}
		
		Connection conn = null;
		Statement statement = null;

		if (C_HR_Phone != null) {
			try {

				conn = ConnectionManager.getConnection();

				statement = conn.createStatement();

				// SQL query to retrieve values from the specified table.

				statement
						.executeUpdate("INSERT INTO OPC_Comp_Prof(C_Name,C_HR_Name,C_Email,C_URL,C_HR_Phone) VALUES ( '"
								+ C_Name
								+ "', '"
								+ C_HR_Name
								+ "','"
								+ C_Email
								+ "','"
								+ C_URL + "','" + C_HR_Phone + "')");

				response.sendRedirect("index.jsp?msg=7");//Information Saved Successfully
			} catch (Exception ex) {
				//Save the error message in LOG 
				//Logger.getLogger(CompanyAdd.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
				String err = ex.toString();
				if (err.indexOf("unique constraint (PT002.C_ID_PK) violated") != -1) {
					response.sendRedirect("index.jsp?msg=D"); //Record with this ID already exists
				} else {
					response.sendRedirect("index.jsp?msg=9");//Information Not Saved
				}

			}
		finally
		{
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				//Save the error message in LOG 
				//Logger.getLogger(CompanyAdd.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		}

		}
	}
}
