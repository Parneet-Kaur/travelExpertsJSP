<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp" />

<div id='nav'>
	<div align='right'>
		<a href="index.jsp">Home</a>
		<%
		if(session.getAttribute("login")=="TRUE")
		{
			out.print("<a href='customers.jsp'>Customers</a>");
			out.print("<a href='bookings.jsp'>Bookings</a>");
			out.print("<a href='packages.jsp'>Packages</a>");
		}
		%>
		<a href="contact.jsp">Contact</a>
	</div>
</div>

<div id='content'>

<%
if(session.getAttribute("login")!="TRUE"){
	%>
	<jsp:include page="loginform.jsp" />
	<%
}
%>

</div>
</body>
</html>