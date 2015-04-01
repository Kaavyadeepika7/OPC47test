package com.opc.statistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
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
 * @Date: January 30, 2011
 * @Copyright Notice: © 2012 Cognizant, all rights reserved
 * @Description: Placement Statistics - Displays the past statistics of the placement activities.
 */
public class stats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		boolean isAdmin = false;
		if (((String) (request.getSession().getAttribute("TYPE")) + "")
				.equalsIgnoreCase("A")) {
			isAdmin = true;
		}

		String yr = request.getParameter("yr");
		String c_yr = (Calendar.getInstance().get(Calendar.YEAR)) + "";
		if (yr == null || yr == "") {
			yr = c_yr;
		}

		out.print("<?xml version='1.0' encoding='ISO-8859-1' ?>");

		out.print("<!DOCTYPE taglib\n");
		out
				.print("PUBLIC \"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN\"\n");
		out.print("\"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd\">");

		out
				.print("<HTML><HEAD><TITLE>Outmaster Placement Cell | Statistics</TITLE>");

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/frame/headers.jsp");

		if (dispatcher != null) {
			dispatcher.include(request, response);
		}

		out.print("<h1>Our Past Statistics</h1>");

		out
				.print("<form name='statFilter' id ='statFilter' method='get'>View for year <SELECT name='yr' id ='yr'>");
		
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getConnection();
			statement = conn.createStatement();

			String QueryString = "SELECT distinct year from OPC_STATS ";
			rs = statement.executeQuery(QueryString);
			int y = 0;
			while (rs.next()) {
				y = rs.getInt(1);
				out.print("<OPTION VALUE='" + y + "'>" + y + "</OPTION>");
			}
			out.print("</SELECT>");
			out.print("<input type='submit' value='filter' ></input>");
			out.print("</form>");

			QueryString = "SELECT C_NAME, YEAR, N_STUDENTS from OPC_STATS WHERE YEAR=" + yr;
			rs = statement.executeQuery(QueryString);

			out
					.print("<TABLE summary='Stats table' cellpadding='0' cellspacing='0'>");
			out.print("<thead> <tr>");
			out.print("<th>Company Name</th>");
			out.print("<th>Year of Visit</th>");
			out.print("<th>Number of Students got Selected</th>");
			out.print("</tr></thead><tbody>");

			int rsCount = 0;
			String rowStyle[] = { "light", "dark" };
			while (rs.next()) {
				rsCount++;
				out.print("<TR class='" + rowStyle[rsCount % 2] + "'>");
				out.print("<TD>" + rs.getString(1) + "</TD>");
				out.print("<TD>" + rs.getString(2) + "</TD>");
				out.print("<TD>" + rs.getString(3) + "</TD></TR>");
			}
			out.print("</tbody></TABLE>");

			if (rsCount == 0) {
				out.print("<div class='info'>No company visited in the year "+ yr + "</div>");
			}

			
			if (isAdmin)
				out.print("<a href='statsUpdation.jsp'>Add New Stat</a>");

		} catch (NullPointerException e) {
			out.print("<div class='info'>No company visited in the given year</div>");
			e.printStackTrace();
		}catch (Exception e) {
			//Logger.getLogger(stats.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (Exception e) {
				//Logger.getLogger(stats.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
		}

		dispatcher = getServletContext().getRequestDispatcher("/frame/footers.jsp");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}

	}

}
