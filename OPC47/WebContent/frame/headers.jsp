<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />

<link rel="stylesheet" href="styles/msg.css" type="text/css" />

<script type="text/javascript" src="scripts/jquery-1.7.1.js"></script>

<script type="text/javascript" src="scripts/jquery.slidepanel.setup.js"></script>


<!-- JQuery Validation Engine -->
<script src="scripts/jquery.validationEngine-en.js"	type="text/javascript" charset="utf-8"></script>
<script src="scripts/jquery.validationEngine.js" type="text/javascript"	charset="utf-8"></script>
<link rel="stylesheet" href="styles/validationEngine.jquery.css" type="text/css" />

<!-- JavaScript Check -->

<SCRIPT type="text/javascript">
	$(document).ready(function() {
		$('div.jsError').hide();
		$('div.jsNoError').css("visibility", "visible");
	});

</SCRIPT>
</HEAD>

<BODY>
  <DIV CLASS="jsError">
    <H1>Error: Javascript Disabled</H1>

    <P>To use this site, you must have JavaScript enabled on your browser.</P>
  </DIV>

  <DIV CLASS="jsNoError"
       STYLE="visibility: hidden;">
    <%@ include file="../login.jsp"%>
    <%@ include file="nav.jsp"%>

    <DIV CLASS="wrapper col3">
      <DIV ID="container">