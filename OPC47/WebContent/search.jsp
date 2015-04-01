<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE taglib PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 25, 2012
  - Copyright_Notice: © 2012 Cognizant, all rights reserved 
  - Description: To search for the students according to the given criteria.
  --%>
<%
	//validating user
	String user_typ = session.getAttribute("TYPE") + "";
	if (!user_typ.equalsIgnoreCase("A")) {
		response.sendRedirect("index.jsp?msg=" + 8);
	}
%>

<HTML>
<HEAD>
  <TITLE>Outmaster Placement Cell | Search</TITLE><%@ include file="frame/header.jsp"%>
</HEAD>

<BODY>
  <DIV CLASS="jsError">
    <H1>Error: Javascript Disabled</H1>

    <P>To use this site, you must have JavaScript enabled on your browser.</P>
  </DIV>

  <DIV CLASS="jsNoError"
       STYLE="visibility: hidden;">
    <%@ include file="login.jsp"%><%@ include file="frame/nav.jsp"%>

    <DIV CLASS="wrapper col3">
      <DIV ID="container"><%
      	if (request.getParameter("msg") != null) {
      		out
      				.print("<div class='error'>Please enter something to search for</div>");
      	}
      %>

        <FORM ID="frmStudent"
              METHOD="post"
              NAME="frmStudent"
              ACTION="SrchResults">
          <FIELDSET>
            <LEGEND>Search Here</LEGEND>

            <DIV CLASS="fm-req">
              <LABEL FOR="SearchName">Name:</LABEL> <INPUT NAME="SearchName"
                   ID="SearchName"
                   TYPE="text">
            </DIV>

            <DIV CLASS="fm-req">
              <LABEL FOR="SearchID">Student ID:</LABEL> <INPUT NAME="SearchID"
                   ID="SearchID"
                   TYPE="text">
            </DIV>

            <DIV CLASS="fm-req">
              <LABEL FOR="SearchPercentage">Percentage:</LABEL> <SELECT ID="Percentage"
                   NAME="Percentage"
                   STYLE="width: 40px">
                <OPTION VALUE="1">
                  =
                </OPTION>

                <OPTION VALUE="2">
                  &gt;
                </OPTION>

                <OPTION VALUE="3">
                  &lt;
                </OPTION>

                <OPTION VALUE="4">
                  &gt;=
                </OPTION>

                <OPTION VALUE="5">
                  &lt;=
                </OPTION>
              </SELECT> <INPUT NAME="SearchPercentage"
                   ID="SearchPercentage"
                   TYPE="text">
            </DIV>

            <DIV CLASS="fm-req">
              <LABEL FOR="SearchCourse">Course:</LABEL> <INPUT NAME="SearchCourse"
                   ID="SearchCourse"
                   TYPE="text">
            </DIV>

            <DIV CLASS="fm-req">
              <LABEL FOR="SearchCol">College:</LABEL> <INPUT NAME="SearchCol"
                   ID="SearchCol"
                   TYPE="text">
            </DIV>

            <DIV ID="fm-submit"
                 CLASS="fm-req">
              <INPUT NAME="Submit"
                   VALUE="Submit"
                   TYPE="submit">
            </DIV>
          </FIELDSET>
        </FORM>
      </DIV>
    </DIV><%@ include file="frame/footer.jsp"%>
  </DIV>
</BODY>
</HTML>
