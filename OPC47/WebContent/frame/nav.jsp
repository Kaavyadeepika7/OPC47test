<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 26, 2011
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description: This page contains name and navigation menu items for the site
  --%>
<%
	response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
	response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
	response.setDateHeader("Expires", -1); //Causes the proxy cache to see the page as "stale"
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
%>
<div class="wrapper col1">
	<div id="header">
		<%-- Logo and Motto  --%>
		<div id="logo">
			<h1>
				<a href="index.jsp">Outmaster Placement Cell</a>
			</h1>
			<p>The Best Is Yet To Be</p>
		</div>
		<%-- Menu Items  --%>

		<%
			String fnme = application.getRealPath(request.getServletPath());
			String l = session.getAttribute("SID") + "";
		%>

		<div id="topnav">
			<ul>
				<%
					if (((String) (session.getAttribute("TYPE")) + "")
							.equalsIgnoreCase("A")
							|| ((String) (session.getAttribute("TYPE")) + "")
									.equalsIgnoreCase("S")) {
				%>
				<li <%=(fnme.endsWith("profile.jsp")) ? "class='active'"
						: ""%>><a
					href="profile.jsp?sessionid=<%=l%>">My Profile</a></li>
				<li <%=(fnme.endsWith("CompanyView")) ? "class='active'"
						: ""%>><a
					href="CompanyView?sessionid=<%=l%>">Recruiters</a></li>
				<li <%=(fnme.endsWith("ScheduleView")) ? "class='active'"
						: ""%>><a
					href="ScheduleView?sessionid=<%=l%>">Upcoming Events</a></li>
				<%
					if (((String) (session.getAttribute("TYPE")) + "")
								.equalsIgnoreCase("A")) {
				%>
				<li <%=(fnme.endsWith("search.jsp")) ? "class='active'"
							: ""%>><a
					href="search.jsp?sessionid=<%=l%>">Search</a></li>
				<%
					}
				%>
				<%
					} else {
				%>
				<li <%=(fnme.endsWith("index.jsp")) ? "class='active'" : ""%>><a
					href="index.jsp?sessionid=<%=l%>">Home</a>
				</li>
				<%
					}
				%>
				<li <%=(fnme.endsWith("stats")) ? "class='active'" : ""%>><a
					href="stats">Statistics</a></li>
				<li
					<%=(fnme.endsWith("Contact.jsp")) ? "class='active last'"
					: "class='last'"%>><a
					href="Contact.jsp">Contact</a></li>
			</ul>
		</div>
		<br class="clear" />
	</div>
</div>
<%-- Blue Bar  --%>
<div class="wrapper col2">
	<div id="breadcrumb"></div>
</div>