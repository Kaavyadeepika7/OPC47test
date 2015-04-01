<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE taglib PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 25, 2012
  - Copyright_Notice: © 2012 Cognizant, all rights reserved 
  - Description: Company Profile - To insert the Company Profile
  --%>
<%	//Getting the session attribute
        String user_typ = session.getAttribute("TYPE") + "";
        if (!user_typ.equalsIgnoreCase("A")) {
                response.sendRedirect("index.jsp?msg=" + 8);
        }
        
%>

<HTML>
<HEAD>
<META NAME="generator"
	CONTENT="HTML Tidy for Linux/x86 (vers 11 February 2007), see www.w3.org">
<TITLE>Outmaster Placement Cell | Company Profile - Add</TITLE><%@ include
	file="frame/header.jsp"%>
<SCRIPT TYPE="text/javascript">
      jQuery(document).ready(function() {
                jQuery("#frmCompanyInsert").validationEngine({
                        autoHidePrompt : true
                });
        });
  </SCRIPT>
</HEAD>

<BODY>
	<DIV CLASS="jsError">
		<H1>Error: Javascript Disabled</H1>

		<P>To use this site, you must have JavaScript enabled on your
			browser.</P>
	</DIV>

	<DIV CLASS="jsNoError" STYLE="visibility: hidden;">
		<%@ include file="login.jsp"%><%@ include
			file="frame/nav.jsp"%>

		<DIV CLASS="wrapper col3">
			<DIV ID="container">
				<!-- Get a connection to the database -->

				<H1>Add a New Company</H1>

				<FORM ID="frmCompanyInsert" NAME="frmCompanyInsert" method="post" action="CompanyAdd">
				
				
		          <FIELDSET>
		            <LEGEND>Company Profile</LEGEND>
		            <DIV CLASS="fm-req">
		              <LABEL FOR="C_Name">Name:</LABEL>
		              <INPUT CLASS="validate[required, minSize[2], maxSize[15]]" NAME="C_Name" ID="C_Name" TYPE="text" value="">
		            </DIV>
		            <DIV CLASS="fm-req">
		              <LABEL FOR="C_URL">URL:</LABEL>
		              <INPUT CLASS="validate[required,custom[url], minSize[2], maxSize[100]]" NAME="C_URL" ID="C_URL" TYPE="text" value="">
		            </DIV>
		            <LEGEND>HR Profile</LEGEND>
		            <DIV CLASS="fm-req">
		              <LABEL FOR="C_HR_Name">Name:</LABEL>
		              <INPUT CLASS="validate[required, minSize[2], maxSize[15]]" NAME="C_HR_Name" ID="C_HR_Name" TYPE="text" value="">
		            </DIV>
		            
		            <DIV CLASS="fm-req">
		              <LABEL FOR="C_Email">Email:</LABEL>
		              <INPUT CLASS="validate[required, custom[email], minSize[5], maxSize[30]]" NAME="C_Email" ID="C_Email" TYPE="text" value="">
		            </DIV>
		            <DIV CLASS="fm-req">
		            <LABEL FOR="C_HR_Phone">Phone:</LABEL>
		              <INPUT CLASS="validate[required, maxSize[10], minSize[10], custom[integer],min[0]]  ]" NAME="C_HR_Phone" ID="C_HR_Phone" TYPE="text" value="">
		            </DIV>
		            
		            <DIV ID="fm-submit" CLASS="fm-req">
		            	<INPUT TYPE="submit" VALUE="Add New Company">
		          	</DIV>
				</FIELDSET>
				</FORM>
			</DIV>
		</DIV>
		<%@ include file="frame/footer.jsp" %>
	</DIV>
</BODY>
</HTML>
