<%--
  - Author(s): Jayarathina Madharasan. Y
  - Date: January 26, 2012
  - Copyright Notice: © 2012 Cognizant, all rights reserved 
  - Description: Login Box - displays login form to login if not already logged in, else displays links to log out
  --%>

<script type="text/javascript">
	jQuery(document).ready(function(){
	    jQuery("#formLogin").validationEngine({autoHidePrompt:true});
	});
</script>

<div class="wrapper col0">
	<div id="topbar">
	<% String l1=session.getAttribute("SID")+"";%>
	
		<%if(session.getAttribute("NAME") == null){ %>
		<div id="slidepanel">
			<div class="topbox">
				<h2>Frank Speaks...</h2>
				<p>Each of us at Cognizant, in one form or another, has received
					the gift of a solid education, which has given us entry into the
					service industry, one of the best platforms within the global
					economy. Through the Outmaster program and other CSR initiatives, we
					will have the opportunity to give such gifts to others.</p>
				<p class="readmore">Francisco D' Souza, CEO</p>
			</div>
			<div class="topbox">
				<h2>Outmaster Team Welcomes You</h2>
				<p>Please sign in with your email and password to access this
					site.</p>
				<p>If you don't have an account, please <a href="profile.jsp?newuser=t" >create a new account</a></p>
				<p class="readmore">
					<a href="Contact.jsp">Goto Contact Us Page</a>
				</p>
			</div>
			<div class="topbox last">
				<h2>Login Here</h2>
				<form id="formLogin" action="LoginVal" method="get">
					<fieldset>
						<legend>Login Here</legend>
						<label>Email:
							<input value="" class="validate[required, maxSize[20]]" type="text" name="email" id="email" /><!-- SQL Injection custom[email], -->
						</label>
						<label>Password:<!--  minSize[6], maxSize[20] -->
							<input value="" class="validate[required ]" type="password" name="password" id="password" />
						</label>
						<p>
							<input type="submit" name="btnLogin" id="btnLogin" value="Login" />
							<input type="reset" name="btnReset" id="btnReset" value="Reset" />
						</p>
						<p>
							<a href="profile.jsp?newuser=t<%=l1%>" >Register</a>
						</p>
					</fieldset>
				</form>
			</div>
			<br class="clear" />
		</div>
		<%}%>
		<div id="loginpanel">
			<ul>
				<li class="left">You Can</li>
				<%if(session.getAttribute("NAME") == null){ %>
				<li class="right" id="toggle"><a id="slideit"
					href="#slidepanel">Log In Here</a><a id="closeit"
					style="display: none;"  href="#slidepanel">Close Panel</a></li>
				<%} else{ %>
					<li class="right"  id="toggle"> <%if((session.getAttribute( "TYPE" )+"").equalsIgnoreCase("A")){ %><a  href='profile.jsp?newuser=t'>Add a new user</a><% } %>
					<a  id="closeit" href="LoginVal?logout=1">Logout</a></li>
				<%}%>
			</ul>
		</div>
		<br class="clear" />
	</div>
</div>