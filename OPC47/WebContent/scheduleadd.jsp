<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE taglib
    PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN"
    "http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd">
<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 25, 2012
  - Copyright_Notice: © 2012 Cognizant, all rights reserved 
  - Description: Upcoming Events - Scheduling the placement
  --%>
  
    <% 
    //Set the session attribute 
	String user_typ = session.getAttribute("TYPE") + "";
	if (!user_typ.equalsIgnoreCase("A") && !user_typ.equalsIgnoreCase("S")) {
		response.sendRedirect("index.jsp?msg=" + 8);
	}
%>
  
  <html>
<head>
<title>Outmaster Placement Cell | Upcoming Events - Add</title>

<%@ include file="frame/header.jsp"%>
<script type="text/javascript">
	jQuery(document).ready(function(){
	    jQuery("#ScheduleInsert").validationEngine({autoHidePrompt:true});
	});
</script>
</head>
<body>
	<div class="jsError">
		<H1>Error: Javascript Disabled</H1>
		<p>To use this site, you must have JavaScript enabled on your
			browser.</p>
	</div>
	<div class="jsNoError" style="visibility: hidden;">

		<%@ include file="login.jsp"%>
		<%@ include file="frame/nav.jsp"%>
		<div class="wrapper col3">
			<div id="container">

			<h1>Add a New Schedule</h1>
			<form name="ScheduleInsert" id="ScheduleInsert" method="post" action="ScheduleAdd">

	          <FIELDSET>
	            <LEGEND>Schedule Information</LEGEND>
	            <DIV CLASS="fm-req">
	              <LABEL FOR="C_ID">Company ID:</LABEL>
	              <INPUT class="validate[required, minSize[1], maxSize[10]]"  NAME="C_ID" ID="C_ID" TYPE="text" value="">
	            </DIV>
	            <DIV CLASS="fm-req">
	              <LABEL FOR="C_Date">Date:</LABEL>
	              <INPUT class="validate[required, custom[date], future[NOW]]"  NAME="C_Date" ID="C_Date" TYPE="text" value="">(YYYY-MM-DD)
	            </DIV>
	            <DIV CLASS="fm-req">
	              <LABEL FOR="C_Venue">Venue:</LABEL>
	              <INPUT class="validate[required, minSize[2], maxSize[30]]"  NAME="C_Venue" ID="C_Venue" TYPE="text" value="">
	            </DIV>
				<DIV ID="fm-submit" CLASS="fm-req">
	            <INPUT TYPE="submit" VALUE="Add New Schedule">
	          	</DIV>
	          </FIELDSET>
			</form>
			</div>
		</div>
		<%@ include file="frame/footer.jsp"%>
	</div>
</body>
</html>