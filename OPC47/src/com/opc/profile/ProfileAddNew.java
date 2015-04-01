package com.opc.profile;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * @Date: January 30, 2011
 * @Copyright Notice: © 2012 Cognizant, all rights reserved
 * @Description: Profile - Adding new user.
 */
public class ProfileAddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement statement = null;
		String y=request.getSession().getAttribute("SID")+"";
		System.out.println(y);
		
		try {
			conn= ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			statement = conn.createStatement();

			String StuId1 = request.getParameter("StudentID"),
							StuName = request.getParameter("StudentName"),
							StuDOB = request.getParameter("StudentDOB"),
							StuAdd = request.getParameter("StudentAddress"),
							StuGen = request.getParameter("frmStuGender"),
							StuMob = request.getParameter("StudentPhone"),
							StuEnroll = request.getParameter("StudentRegNo"),
							StuEmail = request.getParameter("frmEmail"),
							StuPass = request.getParameter("frmPass"),
							StuRePass = request.getParameter("frmRePass"),
							StuCol = request.getParameter("StudentColl"),
							StuPer = request.getParameter("StudentPer"),
							StuDeg = request.getParameter("StudentDeg"),
							StuType = request.getParameter("StudentType");
			
			String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(StuEmail);
			if (!matcher.matches()) {
				response.sendRedirect("index.jsp?msg=E"+ "&session_id="+y);
			}
			
			
			String capt = request.getSession().getAttribute( "captcha" )+"";
			if( ! capt.equalsIgnoreCase(request.getParameter("verfy")) ){
				response.sendRedirect("index.jsp?msg=" + 9+ "&session_id="+y);
			}
			
			if (!StuPass.equalsIgnoreCase(StuRePass)) {
				response.sendRedirect("index.jsp?session_id="+y);
			}
			
			if(StuType == null){
					StuType = "S";
			}


			
			String qury = "insert into OPC_LOGIN (USER_EMAIL, USER_PWD, USER_TYPE) values( '"+StuEmail+"', '"+StuPass+"', '"+StuType+"')";


			statement.executeUpdate(qury);
			
			
			
		 	String QueryString = "SELECT  ID from OPC_LOGIN WHERE USER_EMAIL =  '"+StuEmail+"'";
			ResultSet rs = statement.executeQuery(QueryString);
			rs.next();
			StuId1 = rs.getString(1);
			
			

			qury = "INSERT INTO OPC_STU_DET (STU_ID, SNAME, STU_DOB, STU_ADDRESS, STU_GENDER, STU_MOBILE, STU_ENROL, STU_COURSE, STU_COLLEGE, STU_PER) "+
			"values ("+ StuId1+", '"+StuName+"', '" + StuDOB + "', '" + StuAdd + "','"+ StuGen + "',  '" + StuMob + "',  '" + StuEnroll + "', '" + StuDeg + "', '" + StuCol + "'," + StuPer + " )"; 
			
			statement.executeUpdate(qury);

			conn.commit();
			
			response.sendRedirect("index.jsp?msg=7A"+ "&session_id="+y);

		} catch (Exception e) {
			//Logger.getLogger(ProfileAddNew.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			if (e.toString().indexOf("unique constraint") != -1) {
				response.sendRedirect("index.jsp?msg=K"+ "&session_id="+y);//email address already exists
			}
			
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				//Logger.getLogger(ProfileAddNew.class.getName()).log(Level.SEVERE, null, e1);
			}
		}finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e) {
				//Logger.getLogger(ProfileAddNew.class.getName()).log(Level.SEVERE, null, e);
				e.printStackTrace();
			}
			
		}
	}
}
