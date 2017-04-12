<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, java.io.*" %>
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
if(session.getAttribute("login")=="TRUE"){
%>
<select name="customerlist" id="customerlist">
<option value=''>Select a customer...</option>
<%
try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT CustomerId,CustFirstName,CustLastName FROM customers");
	
	while(rs.next()){
		out.print("<option value='"+rs.getString("CustomerId")+"'>"+rs.getString("CustFirstName")+" "+rs.getString("CustLastName")+"</option>");
	}
}catch(ClassNotFoundException e1){
	e1.printStackTrace();
}catch(SQLException e2){
	e2.printStackTrace();
}
%>
</select>
<%
}
%>
</div>

<br/><br/>
<div id="customerdetails">
Customer ID:<span id='customerid'>NUM</span><br/>
ID of assigned agent:<span id='agentid'>NUM</span><br/>
<br/>
<form id='customerform' action="javascript:getCustomer();"/>
	Name
	<hr>
	First:<input type='text' id='custfirstname' disabled/><br/>
	Last:<input type='text' id='custlastname' disabled/><br/>
	<hr>
	Address
	<hr>
	Street:<input type='text' id='custaddress' disabled/><br/>
	City:<input type='text' id='custcity' disabled/><br/>
	Province/State:<input type='text' id='custprov' disabled/><br/>
	Postal/ZIP:<input type='text' id='custpostal' disabled/><br/>
	Country:<input type='text' id='custcountry' disabled/><br/>
	<hr>
	Contact
	<hr>
	Email:<input type='text' id='custemail' disabled/><br/>
	Telephone<br/>
	Home:<input type='text' id='custhomephone' disabled/><br/>
	Work:<input type='text' id='custbusphone' disabled/><br/>
	<br/>
	<input type="button" value="Edit" id="editcustomer"/><input id="savecustomer" type='submit' value='Update'/>
</form>
</div>

<script type="text/javascript">

$("#editcustomer").click(function(){
	document.getElementById('custfirstname').removeAttribute("disabled");
	document.getElementById('custlastname').removeAttribute("disabled");
	document.getElementById('custaddress').removeAttribute("disabled");
	document.getElementById('custcity').removeAttribute("disabled");
	document.getElementById('custprov').removeAttribute("disabled");
	document.getElementById('custpostal').removeAttribute("disabled");
	document.getElementById('custcountry').removeAttribute("disabled");
	document.getElementById('custemail').removeAttribute("disabled");
	document.getElementById('custhomephone').removeAttribute("disabled");
	document.getElementById('custbusphone').removeAttribute("disabled");
});

$("#savecustomer").click(function(){
	document.getElementById('custfirstname').disabled = true;
	document.getElementById('custlastname').disabled = true;
	document.getElementById('custaddress').disabled = true;
	document.getElementById('custcity').disabled = true;
	document.getElementById('custprov').disabled = true;
	document.getElementById('custpostal').disabled = true;
	document.getElementById('custcountry').disabled = true;
	document.getElementById('custemail').disabled = true;
	document.getElementById('custhomephone').disabled = true;
	document.getElementById('custbusphone').disabled = true;

	$.ajax({
        url:'UpdateCustomer',
        data:{
            'CustomerId':document.getElementById('customerid').innerHTML,
            'CustFirstName':$("#custfirstname").val(),
            'CustLastName':$("#custlastname").val(),
            'CustAddress':$("#custaddress").val(),
            'CustCity':$("#custcity").val(),
            'CustProv':$("#custprov").val(),
            'CustPostal':$("#custpostal").val(),
            'CustCountry':$("#custcountry").val(),
            'CustEmail':$("#custemail").val(),
            'CustHomePhone':$("#custhomephone").val(),
            'CustBusPhone':$("#custbusphone").val(),
            'AgentId':$("#agentid").val()
            },
        type:'POST',
        success:function(response){alert(response);},
        error:function(){alert('Error updating customer');}
	});
});

$(document).ready(function(){
	$("#customerlist").change(function(){
		var customerid = $("#customerlist").val();
		$.ajax({
                url:'CustomerServlet',
                data:{id:customerid},
                type:'GET',
                success:function(response){
                obj = JSON.parse(response);
                	document.getElementById('customerid').innerHTML = obj.CustomerId;
                	document.getElementById('agentid').innerHTML = obj.AgentId;
					document.getElementById('custfirstname').setAttribute('value', obj.CustFirstName);
					document.getElementById('custlastname').setAttribute('value', obj.CustLastName);
					document.getElementById('custaddress').setAttribute('value', obj.CustAddress);
					document.getElementById('custcity').setAttribute('value', obj.CustCity);
					document.getElementById('custprov').setAttribute('value', obj.CustProv);
					document.getElementById('custpostal').setAttribute('value', obj.CustPostal);
					document.getElementById('custcountry').setAttribute('value', obj.CustCountry);
					document.getElementById('custemail').setAttribute('value', obj.CustEmail);
					document.getElementById('custhomephone').setAttribute('value', obj.CustHomePhone);
					document.getElementById('custbusphone').setAttribute('value', obj.CustBusPhone);
                },
                error:function(){alert('error');}
		});
	});
});
</script>

</body>
</html>