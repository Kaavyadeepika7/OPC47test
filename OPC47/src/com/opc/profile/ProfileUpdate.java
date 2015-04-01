package com.opc.profile;

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
 * @Date: January 30, 2011
 * @Copyright Notice: © 2012 Cognizant, all rights reserved
 * @Description: Profile - Updating the user Profile.
 */
public class ProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String hh=request.getSession().getAttribute("SID")+"";
		System.out.println(hh);
		Connection conn = null;
		try {

			conn = ConnectionManager.getConnection();
			Statement statement = conn.createStatement();
			String StuId1 = request.getParameter("StudentID"), StuName = request
					.getParameter("StudentName"), StuDOB = request
					.getParameter("StudentDOB"), StuAdd = request
					.getParameter("StudentAddress"), StuGen = request
					.getParameter("frmStuGender"), StuMob = request
					.getParameter("StudentPhone"), StuEnroll = request
					.getParameter("StudentRegNo"), StuEmail = request
					.getParameter("frmEmail"), StuPass = request
					.getParameter("frmPass"), StuRePass = request
					.getParameter("frmRePass"), StuCol = request
					.getParameter("StudentColl"), StuPer = request
					.getParameter("StudentPer"), StuDeg = request
					.getParameter("StudentDeg"), StuType = request
					.getParameter("StudentType");

			String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(StuEmail);
			if (!matcher.matches()) {
				response.sendRedirect("index.jsp?msg=E"+ "&sessionid="+hh);
			}

			String user_typ = request.getSession().getAttribute("TYPE") + "";
			
			if(!user_typ.equalsIgnoreCase("A")) {
				String s = request.getSession().getAttribute("ID")+"";
				if(! StuId1.equalsIgnoreCase(s)  )
					response.sendRedirect("index.jsp?msg=" + 8+ "&sessionid="+hh);
			}


			if (!StuPass.equalsIgnoreCase(StuRePass)) {
				response.sendRedirect("index.jsp?sessionid="+hh);
			}
			
			if(StuType == null)
				StuType = user_typ;

			String qury = "UPDATE OPC_LOGIN SET " + "USER_EMAIL = '" + StuEmail
					+ "', " + "USER_PWD = '" + StuPass + "',  USER_TYPE = '"
					+ StuType.charAt(0) + "' WHERE OPC_LOGIN.ID = " + StuId1;
			
			statement.executeUpdate(qury);

			qury = "UPDATE OPC_STU_DET  SET " + "SNAME = '" + StuName + "', "
					+ "STU_DOB = '" + StuDOB + "', "
					+ "STU_ADDRESS = '" + StuAdd + "', " + "STU_GENDER = '"
					+ StuGen + "', " + "STU_MOBILE = '" + StuMob + "', "
					+ "STU_ENROL = '" + StuEnroll + "', " + "STU_COURSE = '"
					+ StuDeg + "', " + "STU_COLLEGE = '" + StuCol + "', "
					+ "STU_PER = " + StuPer + " " + "WHERE STU_ID = " + StuId1;

			statement.executeUpdate(qury);
			response.sendRedirect("index.jsp?msg=" + 7+ "&sessionid="+hh);

		} catch (Exception e) {
			//Logger.getLogger(ProfileUpdate.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
			if (e.toString().indexOf("unique constraint") != -1)
				response.sendRedirect("index.jsp?msg=K"+ "&sessionid="+hh);
			else
				response.sendRedirect("index.jsp?msg=" + 9+ "&sessionid="+hh);
		}
	}
}
