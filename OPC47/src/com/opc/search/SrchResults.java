package com.opc.search;

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
 * @author Jayarathina Madharasan. Y
 * @Date: 30 January 2012
 * @Copyright_Notice: © 2012 Cognizant, all rights reserved 
 * @Description: Search - To view details of the students.
 */
public class SrchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//getting the session attribute
		String user_typ = request.getSession().getAttribute("TYPE") + "";
		if (!user_typ.equalsIgnoreCase("A")) {
			response.sendRedirect("index.jsp?msg=" + 8);//You are not authorized to view this page
		}
		
		PrintWriter out = response.getWriter();

		out.print("<?xml version='1.0' encoding='ISO-8859-1' ?>");

		out.print("<!DOCTYPE taglib\n");
		out
				.print("PUBLIC \"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN\"\n");
		out.print("\"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd\">");

		out
				.print("<HTML><HEAD><TITLE>Outmaster Placement Cell | Search Results</TITLE>");

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/frame/headers.jsp");

		if (dispatcher != null) {
			dispatcher.include(request, response);
		}

		String ID = request.getParameter("SearchID");
		String Name = request.getParameter("SearchName");
		String Percentage = request.getParameter("SearchPercentage");
		String College = request.getParameter("SearchCol");
		String Course = request.getParameter("SearchCourse");

		if (((ID == null) || (ID.equalsIgnoreCase("")))
				&& ((Name == null) || (Name.equalsIgnoreCase("")))
				&& ((Percentage == null) || (Percentage.equalsIgnoreCase("")))
				&& ((College == null) || (College.equalsIgnoreCase("")))
				&& ((Course == null) || (Course.equalsIgnoreCase("")))) {
			response.sendRedirect("search.jsp?msg=1");//Enter something to search
		} else {
			String op = "";

			switch (request.getParameter("Percentage").charAt(0)) {
			case '1':
				op = "=";
				break;
			case '2':
				op = ">";
				break;
			case '3':
				op = "<";
				break;
			case '4':
				op = ">=";
				break;
			case '5':
				op = "<=";
				break;
			default:
				response.sendRedirect("search.jsp?msg=1");
			}

			String preQuery = "";
			if (ID != null && !ID.equalsIgnoreCase("")) {
				preQuery += "Stu_ID = '" + ID + "' ";
			}

			if (Name != null && !Name.equalsIgnoreCase("")) {
				preQuery += (preQuery.isEmpty()) ? (" SName like '%" + Name + "%' ")
						: (" AND SName like '" + Name + "' ");
			}

			if (Percentage != null && !Percentage.equalsIgnoreCase("")) {
				preQuery += (preQuery.isEmpty()) ? (" Stu_per" + op + ""
						+ Percentage + " ") : (" AND Stu_per" + op + "'"
						+ Percentage + "' ");
			}

			if (Course != null && !Course.equalsIgnoreCase("")) {
				preQuery += (preQuery.isEmpty()) ? (" STU_COURSE like '"
						+ Course + "' ")
						: (" AND STU_COURSE like '" + Course + "' ");
			}

			if (College != null && !College.equalsIgnoreCase("")) {
				preQuery += (preQuery.isEmpty()) ? (" STU_COLLEGE like '"
						+ College + "' ") : (" AND STU_COLLEGE like '"
						+ College + "' ");
			}
			Connection conn = null;
			Statement statement = null;
			ResultSet rs  = null;
			try {
			 conn = ConnectionManager.getConnection();
		      statement = conn.createStatement();

				// sql query to retrieve values from the table
				String QueryString = "SELECT Stu_ID, SName, Stu_Gender, Stu_DOB, STU_COLLEGE, STU_COURSE, Stu_per "
						+ " from OPC_Stu_Det where " + preQuery + " ORDER BY SName";
				
				
				
				//out.print(QueryString);
				rs = statement.executeQuery(QueryString);

				out.print("<H1>Results</H1>");
				out
						.print("<TABLE SUMMARY='Results table' CELLPADDING='0' CELLSPACING='0'>");
				out.print("<THEAD>" + "<TR>" + "<TH>Student ID</TH>"
						+ "<TH>Student Name</TH>" + "<TH>Gender</TH>"
						+ "<TH>Date of Birth</TH>" + "<TH>College Name</TH>"
						+ "<TH>Degree</TH>" + "<TH>Percentage</TH>"
						+ "</TR></THEAD><TBODY>");

				int rsCount = 0;
				int id = 0;
				String rowStyle[] = { "light", "dark" };
				// loop through the result set
				while (rs.next()) {
					rsCount++;
					id = rs.getInt(1);

					out.print("<TR CLASS=" + rowStyle[rsCount % 2] + ">");
					out.print("<TD>" + id + "</TD>");
					out.print("<TD><A HREF=\"profile.jsp?stuid=" + id + "\">"
							+ rs.getString(2) + "</A></TD>");
					out.print("<TD>" + rs.getString(3) + "</TD>");
					out.print("<TD>" + rs.getDate(4) + "</TD>");
					out.print("<TD>" + rs.getString(5) + "</TD>");
					out.print("<TD>" + rs.getString(6) + "</TD>");
					out.print("<TD>" + rs.getInt(7) + "</TD></TR>");
				}
				if (rsCount == 0) {
					out.print("<div class='info'>Your search did not match any records.</div>");
				}


				out.print("</TBODY></TABLE>");

			} catch (Exception e) {
				//Save the error message in LOG
				//Logger.getLogger(SrchResults.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
				
				/*****************************************************************************************/
				//                                  Reflected XSS                                        //
				/*****************************************************************************************/

				out.print("<div class='error'>Sorry something went wrong for your search query:<ul>");
				
				if((ID != null) && (!ID.equalsIgnoreCase(""))){
					out.print("<li>ID:"+ID+"</li>");
				}
				if ((Name != null) & (!Name.equalsIgnoreCase(""))){
					out.print("<li>Name:"+Name+"</li>");
				}
				
				if ((Percentage != null) && (!Percentage.equalsIgnoreCase(""))){
					out.print("<li>Percentage:"+Percentage+"</li>");
				}
				if ((College != null) && (!College.equalsIgnoreCase(""))){
					out.print("<li>College:"+College+"</li>");
				}
				if ((Course != null) && (!Course.equalsIgnoreCase(""))){
					out.print("<li>Course:"+Course+"</li>");
				}
				
				out.print("</ui></div>");
				
				
				
			}
			finally
			{
				try {
					//close all the connections
					rs.close();
					statement.close();
					conn.close();
				} catch (Exception e) {
					//Logger.getLogger(SrchResults.class.getName()).log(Level.SEVERE, null, e);
					e.printStackTrace();
				}
			}
		}
		dispatcher = getServletContext().getRequestDispatcher("/frame/footers.jsp");
		if (dispatcher != null) {
			dispatcher.include(request, response);
		}
	}
}
