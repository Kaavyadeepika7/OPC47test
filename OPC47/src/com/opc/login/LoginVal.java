package com.opc.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opc.connection.ConnectionManager;

/**
 * 
 * @author: Jayarathina Madharasan. Y
 * @Date: January 30, 2012
 * @Copyright_Notice: © 2012 Cognizant, all rights reserved
 * @Description: Login validation page.
 */
public class LoginVal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String h;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		String msg="",  user_typ="";
		String t="";
		//String l2=request.getSession().getAttribute("SID")+"";

		try {
			user_typ = request.getSession().getAttribute( "TYPE" ).toString();
		}catch(java.lang.NullPointerException ne ){
			user_typ = "";
		}

		if( (user_typ != null) || !(user_typ).equalsIgnoreCase("")) {
			msg = "J";//Already logged-in
		}

		String email_id = request.getParameter("email");
		String psswd = request.getParameter("password");

		if (email_id == null || email_id == "")
			msg = msg + "0";//email not entered

		if (psswd == null || psswd == "")
			msg = msg + "1";//password not entered

		try {
			conn = ConnectionManager.getConnection();

			if(conn==null){
				msg="2";
				response.sendRedirect("index.jsp?msg=" + msg);
			}

			statement = conn.createStatement();

			String QueryString = "SELECT ID, USER_EMAIL, USER_TYPE, SNAME from OPC_Login, OPC_STU_DET where USER_EMAIL='"
				+ email_id
				+ "' AND USER_PWD='"
				+ psswd
				+ "' AND OPC_Login.ID = OPC_STU_DET.STU_ID";

			rs = statement.executeQuery(QueryString);
			if (rs.next()) {
				//HttpSession ssn=request.getSession();
				h = request.getSession().getId();
				System.out.println(h);
				//setting session attributes 
				request.getSession().setAttribute("ID", rs.getString("ID"));
				request.getSession().setAttribute("NAME", rs.getString("SNAME"));
				t=rs.getString("USER_TYPE");
				request.getSession().setAttribute("TYPE",	t);
				msg = "3";//successful login
			} else {
				msg = "4";//un-successful login
			}
			
		} catch (Exception ex) {
			//Logger.getLogger(LoginVal.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				//Logger.getLogger(LoginVal.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		
		try {
			if(request.getParameter("logout").toString().equalsIgnoreCase("1")) {
				//removing session attributes 
				//request.getSession().removeAttribute("ID");
				request.getSession().removeAttribute("NAME");
				//request.getSession().removeAttribute("TYPE");
				//request.getSession().invalidate();
				//response.sendRedirect("index.jsp?msg=5");
				msg="5";
			}
		}catch(NullPointerException npe) {
			//response.sendRedirect("index.jsp");
			//Logger.getLogger(LoginVal.class.getName()).log(Level.SEVERE, null, npe);
			npe.printStackTrace();

		}
		//response.sendRedirect("index.jsp?msg="+ msg);
		response.sendRedirect("index.jsp?msg=" + msg+ "&session_id="+h+ "&emailId="+email_id+ "&Password="+psswd+"&type="+t);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	//@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		
	}
}
