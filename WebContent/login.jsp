<%@page import="bean.Login"%>  
<jsp:useBean id="obj" class="bean.Credentials"/>  
<jsp:setProperty property="*" name="obj"/>  
  
<%
boolean status=Login.checkLogin(obj);
if(status){
out.println("You r successfully logged in");  
session.setAttribute("session","TRUE");
}  
else
{  
session.setAttribute("login","TRUE");
%>
<jsp:include page="index.jsp"></jsp:include>  
<%
}
%>