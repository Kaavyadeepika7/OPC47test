<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE taglib PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 26, 2011
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description:
  -  Home page: contains the starting of a page with 
  --%>
<%@ page import="com.opc.profile.*"%>
<%
	String StuId1 = "", StuName = "", StuDOB = "", StuAdd = "", StuGen = " ", StuMob = "", StuEnroll = "", StuEmail = "", StuPass = "", StuCol = "", StuCrs = "", StuPer = "", StuType = " ";

	StuId1 = request.getParameter("stuid");

	String newUsr = request.getParameter("newuser");

	boolean newUser = (newUsr != null) ? true : false;

	String user_typ = session.getAttribute("TYPE") + "";
	if (user_typ.equalsIgnoreCase("S")) {
		StuId1 = session.getAttribute("ID") + "";
	} else if ((!user_typ.equalsIgnoreCase("A")) && (!newUser)) {
		response.sendRedirect("index.jsp?msg=" + 8);
	} else if (user_typ.equalsIgnoreCase("A")) {
		if ((!newUser) && (StuId1 == null))
			StuId1 = session.getAttribute("ID") + "";
	}
	
	profileRetrive p;
	
	try {
		if (StuId1 != null) {
			p = new profileRetrive(StuId1);
			StuName = p.StuName;
			StuDOB = p.StuDOB;
			StuAdd = p.StuAdd;
			StuGen = p.StuGen;
			StuMob = p.StuMob;
			StuEnroll = p.StuEnroll;
			StuEmail = p.StuEmail;
			StuPass = p.StuPass;
			StuCol = p.StuCol;
			StuCrs = p.StuCrs;
			StuPer = p.StuPer;
			StuType = p.StuType;
		} else {
			StuId1 = "";
			newUser = true;
		}
	} catch (Exception e) {
		out.print(e.toString());
	}
%>

<HTML>
<HEAD>
	<TITLE>Outmaster Placement Cell | Profile</TITLE>
	<%@ include file="frame/header.jsp"%>
	<script type="text/javascript">
		jQuery(document).ready(function(){
		    jQuery("#frmStudent").validationEngine({autoHidePrompt:true});
		});
	</script>
</HEAD>

<BODY>
  <DIV CLASS="jsError">
    <H1>Error: Javascript Disabled</H1>

    <P>To use this site, you must have JavaScript enabled on your browser.</P>
  </DIV>

  <DIV CLASS="jsNoError" STYLE="visibility: hidden;">
    <%@ include file="login.jsp"%>
    <%@ include file="frame/nav.jsp"%>

    <DIV CLASS="wrapper col3">
      <DIV ID="container">

      
      
        <H1>My Profile</H1>
        <!-- ID="fm-intro" -->
        <P>Fields in <STRONG>bold</STRONG> are required.</P>

        <FORM ID="frmStudent" METHOD="post" ACTION="<%=(newUser) ? "ProfileAddNew" : "ProfileUpdate"%>" NAME="frmStudent">
          <FIELDSET>
            <LEGEND>Personal Information</LEGEND>
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentName">Name:</LABEL>
              <INPUT class="validate[required, minSize[2], maxSize[15]]"  NAME="StudentName" ID="StudentName" TYPE="text" value="<%=StuName%>">
            </DIV>
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentDOB">Birth Date:</LABEL>
			  <INPUT  class="validate[required, custom[date], past[NOW] ]"  NAME="StudentDOB" ID="StudentDOB" TYPE="text" value="<%=StuDOB%>">(YYYY-MM-DD)
            </DIV>
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentGender">Gender:</LABEL> <SELECT ID="StudentGender" NAME=
              "frmStuGender">
                <OPTION VALUE="M" <%if (StuGen.toLowerCase().startsWith("m"))
				out.print("selected='selected'");%>>Male</OPTION>
                <OPTION VALUE="F" <%if (StuGen.toLowerCase().startsWith("f"))
				out.print("selected='selected'");%>>Female</OPTION>
              </SELECT>
            </DIV>
            <%
            	if (!newUser) {
            %>
             <INPUT  class="validate[required]" NAME="StudentID" ID="StudentID"
              TYPE="hidden" value="<%=StuId1%>">
            
           <%
              	}
            	if ( (!StuId1.equalsIgnoreCase(session.getAttribute("ID") + "")) && (!newUser) ) {
            %>
             <DIV CLASS="fm-req">
              <LABEL FOR="StudentType">Type:</LABEL>
              <SELECT ID="StudentType" NAME= "StudentType">
                <OPTION VALUE="S" <%if (StuType.toLowerCase().charAt(0) == 's')
					out.print("selected='selected'");%>>Student</OPTION>
                <OPTION VALUE="A" <%if (StuType.toLowerCase().charAt(0) == 'a')
					out.print("selected='selected'");%>>Admin</OPTION>
              </SELECT>
            </DIV>
            <%
            	}
            %>
          </FIELDSET>
          <FIELDSET>
            <LEGEND>Contact Information</LEGEND>
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentAddress">Address:</LABEL> 
              <TEXTAREA class="validate[required, maxSize[100], minSize[5]]" NAME="StudentAddress" ID="frmAddress" ROWS="5" COLS="20"><%=StuAdd%></TEXTAREA>
            </DIV>
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentPhone">Phone:</LABEL> <INPUT  class="validate[required, maxSize[15]]" ID="StudentPhone" NAME=
              "StudentPhone" TYPE="text"  value="<%=StuMob%>">
            </DIV>
            <DIV CLASS="fm-req">
              <LABEL FOR="frmEmail">Email:</LABEL> <INPUT value="<%=StuEmail%>" class="validate[required, custom[email], minSize[5], maxSize[30]]" ID="frmEmail" NAME="frmEmail" TYPE="text">
            </DIV>
            <DIV CLASS="fm-req">
             <LABEL FOR="frmPass">Password:</LABEL> <INPUT value="<%=StuPass%>" class="validate[required], minSize[6], maxSize[20]]" ID="frmPass" NAME="frmPass" TYPE="password">
            </DIV>
            <DIV CLASS="fm-req">
             <LABEL FOR="frmRePass">Re-Enter Password:</LABEL> <INPUT value="<%=StuPass%>" class="validate[required, equals[frmPass]]" ID="frmRePass" NAME="frmRePass" TYPE="password">
            </DIV>
          </FIELDSET>
          <FIELDSET>
            <LEGEND> Educational Qualification</LEGEND>
            
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentColl">College:</LABEL> <INPUT class="validate[required, maxSize[20]]"  NAME=
              "StudentColl" ID="StudentColl" TYPE="text" value="<%=StuCol%>">
            </DIV>
            
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentRegNo">Sl.No:</LABEL> <INPUT class="validate[required, maxSize[20]]"  NAME=
              "StudentRegNo" ID="StudentRegNo" TYPE="text" value="<%=StuEnroll%>">
            </DIV>
            
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentDeg">Course:</LABEL> <INPUT class="validate[required, maxSize[15]]"  NAME=
              "StudentDeg" ID="StudentDeg" TYPE="text" value="<%=StuCrs%>">
            </DIV>
            
            <DIV CLASS="fm-req">
              <LABEL FOR="StudentPer">Percentage:</LABEL> <INPUT class="validate[required, custom[number], min[0], maxsize[100]]"  NAME=
              "StudentPer" ID="StudentPer" TYPE="text" value="<%=StuPer%>">
            </DIV>
          </FIELDSET>
          <%
          	if (newUser) {
          %>
          <fieldset><legend>Verification</legend>
			<div class="fm-req"><label for="verfy">Enter the text shown below: </label> <input
		class="validate[required]" type="text" name="verfy" id="verfy" size="35" /><img src="capt.jsp" align="middle"/></div>
			<div id="fm-submit" class="fm-req">
				<input name="Submit" value="Register Account" type="submit" />
			</div>
		</fieldset>
          <%
          	} else {
          %>
	          <DIV ID="fm-submit" CLASS="fm-req">
	            <INPUT NAME="Submit" VALUE="Update" TYPE="submit">
	          </DIV>
          <%
          	}
          %>
        </FORM>
      </DIV>
    </DIV>
    <%@ include file="frame/footer.jsp"%>
  </DIV>
</BODY>
</HTML>