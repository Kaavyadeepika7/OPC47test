package com.opc.company;


import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
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
 * @Description: Company Profile - View the Company Profile
 */
public class CompanyView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html;charset=UTF-8");
		
		out.print("<?xml version='1.0' encoding='ISO-8859-1' ?>");

		out.print("<!DOCTYPE taglib\n");
		out
				.print("PUBLIC \"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN\"\n");
		out.print("\"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd\">");

		out
				.print("<HTML><HEAD><TITLE>Outmaster Placement Cell | Company View</TITLE>");

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/frame/headers.jsp");

		if (dispatcher != null) {
			dispatcher.include(request, response);
		}

		out.println("<h1>Company Profile</h1>");
		out.println("<TABLE cellpadding=\"0\" cellspacing=\"0\">");
		out.println("<thead>");
		out.println("<TR>");
		out.println("<th>Company ID</th>");
		out.println("<th>Company Name</th>");
		out.println("<th>HR Name</th>");
		out.println("<th>HR Email</th>");
		out.println("<th>Company URL</th>");
		out.println("<th>HR Phone</th>");
		out.println("</TR>");
		out.println("</thead>");
		
		Connection conn = null;
		Statement statement = null;
		ResultSet rs  = null;

		// SQL query to retrieve values from the specified table.
		try {
			conn = ConnectionManager.getConnection();
			statement = conn.createStatement();
			String QueryString = "SELECT * from OPC_Comp_Prof";
			rs = statement.executeQuery(QueryString);
			
			int rsCount = 0;
			String rowStyle[] = { "light", "dark" };
			out.println("<tr>");
			//Loop through the result set
			while (rs.next()) {
				rsCount++;

				out.println("<TR  class=\"" + rowStyle[rsCount % 2] + "\">");

				out.println("<td>" + rs.getInt(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(4) + "</td>");
				out.println("<td>" + rs.getString(5) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("</tr>");

			}

			out.println("</table>");
			
			if(rsCount == 0){
				out.print("<div class='info'>We will update our Recruiters details soon.</div>");
			}
			//Getting the session attribute
			String user_typ = request.getSession().getAttribute( "TYPE" )+"";
			
			if (user_typ.equalsIgnoreCase("A")) {

				out.println("<table border='0'  cellpadding='0' cellspacing='0'>");
				out.println("<tr><td>");
				if(rsCount>0)
				out.println("<FORM action='CompanyDel' method='post'><input type='submit' value='DELETE'> <INPUT TYPE=\"text\" NAME=\"C_ID\" VALUE=\"\"> </FORM>");
				out.println("</td>");
				out.println("<td><FORM action='companyAdd.jsp' method='post'>");
				out.println("<input type='submit' value='INSERT'>");
				out.println("</FORM></td> </tr>");
				out.println("</table>");
			}

		} catch (Exception ex) {
			//Save the error message in LOG 
			//Logger.getLogger(CompanyView.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			out.println("<div class='error'>" + ex.toString()
					+ "Unable to connect to database.</div>");
		}
	finally
		{
		try {
			rs.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			//Save the error message in LOG 
			//Logger.getLogger(CompanyView.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

		}

	}

}

