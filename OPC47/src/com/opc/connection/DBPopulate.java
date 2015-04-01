//package com.opc.connection;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Random;
//import java.util.UUID;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class DBPopulate
// */
//public class DBPopulate extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Connection conn = null;
//		try {
//			conn= ConnectionManager.getConnection();
//			Statement statement = conn.createStatement();
//
//			String StuId1 = "", name="";
//			String qury ="";
//			Random rn = new Random();
//			for(int i=0; i<=100; i++) {
//
//				//StuDeg = UUID.randomUUID().toString().substring(0, 4).toUpperCase(); 
//
//				
//				StuId1 = (rn.nextInt(102)+2000)+""; 
//				name = UUID.randomUUID().toString().substring(0, 5);
//				
//					
//				
//				qury = "insert into  ola_leave_detail values(1, '13-MAR-12', '13-MAR-12', 3, '"+UUID.randomUUID().toString().substring(0, 5)+"', 'a', '"+UUID.randomUUID().toString().substring(0, 5)+"')";
//				//System.out.println(qury);
//				statement.executeUpdate(qury);
//				
//				
//				/*qury = "insert into ola_attendance_detail values("+StuId1+",  '07-02-2012', 'Present', '"+name+"', 'chn11pt002')"; 
//				//System.out.println(qury);
//				statement.executeUpdate(qury);
//				
//				qury = "insert into ola_leave_detail values("+StuId1+", '23-02-2012', '28-02-2012', 6, 'Fever', 'Pending', '"+name+"')"; 
//				//System.out.println(qury);
//				statement.executeUpdate(qury);*/
//
//				
//			}
//			System.out.println("Done");
//			conn.close();
//		}catch(Exception e) {
//			System.out.println(e.toString());
//		}
//	}
//	
//	void pop() {
//		
//		Connection conn = null;
//		try {
//			conn= ConnectionManager.getConnection();
//			Statement statement = conn.createStatement();
//
//			String StuId1 = "", StuName = "Test", StuDOB = "10-MAR-90" ,
//			StuAdd = "Address", StuGen = "MF", StuMob = "0000000000", StuEnroll = "STU", StuEmail = "@stu.com",
//							StuPass = "STUSTU", StuCol = "IGCAS-PU", StuPer = "", StuDeg = "", StuType = "";
//			String QueryString = "SELECT  OPC_ID_CNT.nextval from dual";
//			String qury ="";
//			Random rn = new Random();
//			for(int i=0; i<=100; i++) {
//
//				ResultSet rs = statement.executeQuery(QueryString);
//				rs.next();
//				StuId1 = rs.getString(1);
//				
//				StuName = "Test"+StuId1;
//				StuDOB = "1998-02-01";	
//				StuAdd =  UUID.randomUUID().toString(); 
//				StuGen = "MF";
//				StuMob = "0000000000";
//				StuEnroll = "STU"+StuId1;
//				StuEmail = StuId1+"@stu.com";
//				StuPass = UUID.randomUUID().toString().substring(0, 5); 
//				StuCol = UUID.randomUUID().toString().substring(0, 9); 
//				StuPer = (rn.nextInt(99)+1)+"";
//				StuDeg = UUID.randomUUID().toString().substring(0, 4).toUpperCase(); 
//				StuType = "SA";
//				
//				//System.out.println( new BigInteger(130, new SecureRandom() ).toString(32) ); 
//
//				qury = "insert into OPC_LOGIN ( ID, USER_EMAIL, USER_PWD, USER_TYPE) values("+ StuId1+", '"+StuEmail+"', '"+StuPass+"', '"+StuType.charAt(rn.nextInt(2))+"')";
//				//System.out.println(qury);
//
//				//statement.executeUpdate(qury);
//
//				qury = "INSERT INTO OPC_STU_DET (STU_ID, SNAME, STU_DOB, STU_ADDRESS, STU_GENDER, STU_MOBILE, STU_ENROL, STU_COURSE, STU_COLLEGE, STU_PER) "+
//				"values ("+ StuId1+", '"+StuName+"', to_date('" + StuDOB + "', 'YYYY-MM-DD'), '" + StuAdd + "',  '" + StuGen.charAt(rn.nextInt(2)) + "',   '" + StuMob + "',  '" + StuEnroll + "', '" + StuDeg + "', '" + StuCol + "'," + StuPer + " )"; 
//				//System.out.println(qury);
//				//statement.executeUpdate(qury);
//			}
//			System.out.println("Done");
//			conn.close();
//		}catch(Exception e) {
//			System.out.println(e.toString());
//		}
//		
//	}
//
//}
