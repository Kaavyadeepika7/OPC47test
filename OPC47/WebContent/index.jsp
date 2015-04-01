<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE taglib
    PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN"
    "http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd">

<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 26, 2011
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description: Home page - contains the start page for the project. Displays suitable message(error/info) depending on the msg param. 
  --%>

<html>
<head>
<title>Outmaster Placement Cell | Home</title>

<%@ include file="frame/header.jsp"%>

</head>
<body>
<div class="jsError">
<H1>Error: Javascript Disabled</H1>
<p>To use this site, you must have JavaScript enabled on your
browser.</p>
</div>
<div class="jsNoError" style="visibility: hidden;"><%@ include
	file="login.jsp"%> <%@ include
	file="frame/nav.jsp"%>
<div class="wrapper col3">
<div id="container">
 
<%
	if (request.getParameter("msg") != null
			&& request.getParameter("msg") != "") {

		for (int i = 0; i < request.getParameter("msg").length(); i++) {
			char value = request.getParameter("msg").charAt(i);

			out.print("<div class=");
			switch (value) {
			case '0':
				out.print("'error'>Please enter your Email ID");
				break;
			case '1':
				out.print("'error'>Please enter your password");
				break;
			case '2':
				out.println("'error'>Fatal Error: Could not connect to database");
				break;
			case '3':
				if(session.getAttribute( "NAME" ) != null
						&& session.getAttribute( "NAME" ) != "")
					out.println("'info'>Welcome " + session.getAttribute( "NAME" ));
					session.setAttribute("TYPE",request.getParameter("type"));
					session.setAttribute("SID",request.getParameter("session_id").toString());

				break;
			case '4':
				out.println("'warning'>The username or password you entered is incorrect.");
				break;
			case '5':
				out.println("'info'>Logout Successful");
				break;
			case '6':
				out.println("'info'>You are already logged-in");
				break;
			case '7':
				out.println("'info'>Information Saved Sucessfully");
				break;
			case '8':
				out.println("'error'>You are not authorized to view this page");
				break;
			case '9':
				out.println("'error'>Information Not Saved. Please check the entered data.");
				break;
			case 'A':
				out.println("'info'>You can login now");
				break;
			case 'B':
				out.println("'info'>Deleted Sucessfully");
				break;
			case 'C':
				out.println("'error'>Not Deleted. No records matched for your input");
				break;
			case 'D':
				out.println("'error'>Record with this ID already exists");
				break;
			case 'E':
				out.println("'error'>Email entered is of invalid format");
				break;
			case 'F':
				out.println("'error'>URL entered is of invalid format");
				break;
			case 'G':
				out.println("'error'>Data you entered is of invalid format or incomplete");
				break;
			case 'H':
				out.println("'error'>Company ID not found.");
				break;
			case 'I':
				out.println("'error'>Cannot delete company, because it is present in the schedule");
				break;
			case 'J':
				out.println("'error'>Already logged in");
				break;
			case 'K':
				out.println("'error'>This email is already associated with another account.");
				break;
			}
			out.print("</div>");
		}
	}
%>
<h1>Welcome</h1>
<p>The aim of Project Outmaster is to make a tangible difference worldwide in the field of education... through the volunteer efforts of our associates and the financial and administrative support of our company. Progress to date includes these accomplishments:</p>

<b>In U.S.</b>
<P>Junior Achievement pilot in six schools in Chicago, IL and Hartford, CT. 
Recording for the blind and dyslexic; partnering with School for Autism.</P>

<b>In UK </b>
<P>Junior Achievement / Young Enterprise, The Prince's Trust.
Cognizant teams raised 7,200 and voted as having the best spirit and camaraderie during the Cap Gemini Wild Challenge.</P>

<b>India</b><br /><P>
School adoption programs 200+ activity based projects completed (coaching classes, science awareness, talent competitions).
Alumni-led Campus Engagement Program pilot with Anna University, Chennai and RVCE, Bangalore.</P>

<P>TalHunt-164 winners chosen from 5000+ students at 14 under-privileged schools in Chennai.</P>

<b>China</b><P>
Scholarship, stationery, lab support for two schools.  </P>

</div>
</div>

<%@ include file="frame/footer.jsp"%></div>
</body>
</html>