package com.opc.profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.opc.connection.ConnectionManager;

/**
 * 
 * @author: Jayarathina Madharasan. Y
 * @Date: January 30, 2011
 * @Copyright Notice: © 2012 Cognizant, all rights reserved
 * @Description: Profile - Retrieving from server to view in a jsp page.
 */

public class profileRetrive{
	public String StuId1 = "", StuName = "", StuDOB = "", StuAdd = "", StuGen = " ", StuMob = "", StuEnroll = "", StuEmail = "", StuPass = "", StuCol = "", StuCrs = "", StuPer = "", StuType = " ";

	public profileRetrive(String StuId1) throws NamingException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException{
		
		Connection conn = null;

			conn= ConnectionManager.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs= null;
			if (StuId1 != null) {
				String QueryString = "SELECT  STU_ID, SNAME, STU_DOB, STU_ADDRESS, STU_GENDER, STU_MOBILE, STU_ENROL, USER_EMAIL, USER_PWD, STU_COURSE, STU_COLLEGE, STU_PER, USER_TYPE from OPC_Stu_Det, OPC_Login where OPC_Login.ID = OPC_STU_DET.STU_ID AND STU_ID="
						+ StuId1;
				rs = statement.executeQuery(QueryString);

				if (rs.next()) {
					StuName = rs.getString(2) + "";
					StuDOB = rs.getString(3);
					StuAdd = rs.getString(4);
					StuGen = rs.getString(5);
					StuMob = rs.getString(6);
					StuEnroll = rs.getString(7);
					StuEmail = rs.getString(8);
					StuPass = rs.getString(9);
					StuCol = rs.getString(10);
					StuCrs = rs.getString(11);
					StuPer = rs.getString(12);
					StuType = rs.getString(13);
				}
			}
		
		
	}
}
