<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.sql.*, java.io.*" %>
    <jsp:include page="header.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link href="bootstrap/css/contact.css" rel="stylesheet" type="text/css" />

<link href="style.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	
    <br></br>
    <br></br>
    <h2 class="display-1">Please select your agent... </h2>
   
<span>&nbsp&nbsp&nbsp  <select name="agentlist" id="agentlist">
	<option value='' id="contactName">Select a customer...</option>
	<%
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT AgtFirstName,AgtLastName,AgentId FROM agents");
	
		while(rs.next()){
		out.print("<option value='"+rs.getString("AgentId")+"'>"+rs.getString("AgtFirstName")+"  "+rs.getString("AgtLastName")+"</option>");
		}
	}
	catch(ClassNotFoundException e1){
		e1.printStackTrace();
	}
	catch(SQLException e2){
		e2.printStackTrace();
	}
	%>
  </select>
  
    </span>
   
<!-- <h4 class="title">Demo Card with everything</h4> -->	
<div class="demo-card">
    <div class="card">
        <div class="card-header">
            <label id="agentName"></label>
        </div>
        <img class="card-img-top" src="images/travelAgent.png" align="middle" id="agentImage">
        <div class="card-block">
            <h4 class="card-title"><label id="fName"></label></h4>
           
            <ul class="list-group list-group-flush">
                
                <li class="list-group-item"><span class="glyphicon glyphicon-phone">&nbsp<label id="agentPhone"></label></li>
                <li class="list-group-item"><span class="glyphicon glyphicon-envelope">&nbsp</span> <label id="agentEmail"></label></li>
                <li class="list-group-item"><span class="glyphicon glyphicon-home">&nbsp<label id="agentAddress"></label></li>
                
            </ul>
        </div>
        <div class="card-block">
          <div class="card-footer text-muted">
            At your service always!
        </div>
        </div>
        <div class="card-block">
            <a href="#" class="btn btn-primary">Home</a>
        </div>
        
    </div>
</div>
</div>  <script type="text/javascript">
  $(document).ready(function(){
		$("#agentlist").change(function(){
			var agentid = $("#agentlist").val();
			$.ajax({
	                url:'Agents',
	                data:{id:agentid},
	                type:'GET',
	                success:function(response){
	                	//alert(response);
	                obj = JSON.parse(response);
	                	document.getElementById('agentName').setAttribute('text',obj.agtFirstName+" "+ obj.agtLastName);
	                	document.getElementById('fName').innerHTML=obj.agtFirstName+" "+ obj.agtLastName;
	                	document.getElementById('agentPhone').innerHTML=obj.agtBusPhone;
	                	document.getElementById('agentEmail').innerHTML=obj.agtEmail;
	                	agencyObj= obj.agncy;
	                	document.getElementById('agentAddress').setAttribute('value',agencyObj.agncyAddress+" "+agencyObj.agncyPostal+" "+agencyObj.agncyCity);
	                	
	                	document.getElementById('agentAddress').innerHTML=agencyObj.agncyAddress+" "+agencyObj.agncyPostal+" "+agencyObj.agncyCity;
	                	document.getElementById('agentEmail').setAttribute('value',obj.agtEmail);
	                	document.getElementById('agentPhone').setAttribute('value',obj.agtBusPhone);
	                	document.getElementById('agentPosition').setAttribute('value',obj.agtPosition);
	                   
	                	
	                	
	                },
	                error:function(){alert('error');}
			});
		});
	});

  
  
  
  </script>
 
 <!-- <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>  --> 
  
  <!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>