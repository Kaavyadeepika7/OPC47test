<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE taglib
    PUBLIC "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN"
    "http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd">

<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 26, 2011
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description:
  -  Home page: contains contact info of the site
  --%>

<html>
<head>
<title>Outmaster Placement Cell | Contact</title>

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
<h2>World Headquarters</h2>
<h3>Cognizant Technology Solutions</h3>
<p>500 Frank W.Burr Blvd.<br />
Teaneck, NJ 07666</p>
<p>Ph: +1 201 801 0233<br />
Fax: +1 201 801 0243<br />
Toll-free: +1 888 937 3277</p>
<p><a title="Map &amp; Directions"
	href="http://www.google.com/maps?q=500+Frank+W+Burr+Blvd,+Teaneck,+NJ+07666,+USA&amp;ie=UTF8&amp;ll=40.87838,-74.006395&amp;spn=0.009702,0.028582&amp;z=15&amp;iwloc=addr&amp;om=1"
	target="_blank">Map &amp; Directions &gt;</a></p>

</div>
</div>

<%@ include file="frame/footer.jsp"%></div>
</body>
</html>