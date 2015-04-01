<?xml version="1.0" encoding="iso-8859-1"?>
<%@ page import="java.sql.*"%><%@ page import="java.io.*"%><%@ page import="java.util.*"%><%
	String user_typ = session.getAttribute("TYPE") + "";
	if (!user_typ.equalsIgnoreCase("A")) {
		response.sendRedirect("index.jsp?msg=" + 8);
	}
%>
<!DOCTYPE taglib SYSTEM "http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd">
<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 25, 2012
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description: Placement Statistics
  --%>

<HTML>
<HEAD>
  <META NAME="generator"
        CONTENT="HTML Tidy for Linux/x86 (vers 11 February 2007), see www.w3.org">

  <TITLE>Outmaster Placement Cell | Statistics Update</TITLE><%@ include file="frame/header.jsp"%>
  <script type="text/javascript">
	jQuery(document).ready(function(){
	    jQuery("#frmStatsUpdate").validationEngine({autoHidePrompt:true});
	});
</script>
</HEAD>


<BODY>
  <DIV CLASS="jsError">
    <H1>Error: Javascript Disabled</H1>

    <P>To use this site, you must have JavaScript enabled on your browser.</P>
  </DIV>

  <DIV CLASS="jsNoError"
       STYLE="visibility: hidden;">
    <%@ include
            file="login.jsp"%><%@ include
            file="frame/nav.jsp"%>

    <DIV CLASS="wrapper col3">
      <DIV ID="container">
        <H1>UPDATION</H1>

        <FORM NAME="frmStatsUpdate" id="frmStatsUpdate"
              METHOD="post" action="StatsUpdate">
          <TABLE>
            <TR>
              <TH WIDTH="50%">Company Name</TH>

              <TD WIDTH="50%"><INPUT  class="validate[required]"  TYPE="text"
                     NAME="C_Name" ID="C_Name"></TD>
            </TR>

            <TR>
              <TH WIDTH="50%">Year</TH>

              <TD WIDTH="50%"><INPUT TYPE="text"
                   class="validate[required, custom[integer],min[2005], max[<%=(new
java.util.GregorianCalendar()).get(java.util.Calendar.YEAR)%>]]"  NAME="YEAR" ID="YEAR"></TD>
            </TR>

            <TR>
              <TH WIDTH="50%">No of Students</TH>

              <TD WIDTH="50%"><INPUT TYPE="text"
                    class="validate[required, custom[integer],min[1]] " NAME="N_STUDENTS" ID="N_STUDENTS"></TD>
            </TR>

            <TR>
            	<th></th>
              <TD WIDTH="50%"><INPUT TYPE="submit"
                     VALUE="Add"></TD>
            </TR>
          </TABLE>
        </FORM>
      </DIV>
    </DIV><%@ include file="frame/footer.jsp"%>
  </DIV>
</BODY>
</HTML>
