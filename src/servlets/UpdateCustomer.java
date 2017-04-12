package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCustomer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
			
			PreparedStatement pst = con.prepareStatement("UPDATE customers SET CustFirstName = ?,"+
			"CustLastName = ?,CustAddress = ?,CustCity = ?,CustProv = ?,CustPostal = ?,CustCountry = ?,CustHomePhone = ?,CustBusPhone = ?,CustEmail = ? "+
			"WHERE CustomerId = ?");
			pst.setString(1, request.getParameter("CustFirstName"));
			pst.setString(2, request.getParameter("CustLastName"));
			pst.setString(3, request.getParameter("CustAddress"));
			pst.setString(4, request.getParameter("CustCity"));
			pst.setString(5, request.getParameter("CustProv"));
			pst.setString(6, request.getParameter("CustPostal"));
			pst.setString(7, request.getParameter("CustCountry"));
			pst.setString(8, request.getParameter("CustHomePhone"));
			pst.setString(9, request.getParameter("CustBusPhone"));
			pst.setString(10, request.getParameter("CustEmail"));
			pst.setInt(11, Integer.parseInt(request.getParameter("CustomerId")));
			
			if(pst.executeUpdate()==1){
				response.setContentType("text/plain");
				response.getWriter().write("Customer " + request.getParameter("CustomerId") + " updated");
			}else{
				response.setContentType("text/plain");
				response.getWriter().write("Something went wrong, contact admin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/plain");
			response.getWriter().write("Update failed");
		}
	}
}