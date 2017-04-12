package servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Customer;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CustomerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			String customerid = request.getParameter("id");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
			PreparedStatement pst = con.prepareStatement("SELECT CustFirstName,CustLastName,CustAddress,CustCity,CustProv,CustPostal,CustCountry,CustHomePhone,CustBusPhone,CustEmail,AgentId FROM customers WHERE CustomerId = ?");
			pst.setString(1, customerid);
			
			ResultSet rs = pst.executeQuery();
			
			Customer c = new Customer();
			
			if(rs.next()){
				c.setCustomerId(Integer.parseInt(customerid));
				c.setCustFirstName(rs.getString("CustFirstName"));
				c.setCustLastName(rs.getString("CustLastName"));
				c.setCustAddress(rs.getString("CustAddress"));
				c.setCustCity(rs.getString("CustCity"));
				c.setCustProv(rs.getString("CustProv"));
				c.setCustPostal(rs.getString("CustPostal"));
				c.setCustCountry(rs.getString("CustCountry"));
				c.setCustHomePhone(rs.getString("CustHomePhone"));
				c.setCustBusPhone(rs.getString("CustBusPhone"));
				c.setCustEmail(rs.getString("CustEmail"));
				c.setAgentId(rs.getInt("AgentId"));
			}
			Gson gson = new Gson();
			Type type = new TypeToken<Customer>(){}.getType();
			
			String JSON = gson.toJson(c,type);
			response.setContentType("text/plain");
			response.getWriter().write(JSON);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("null");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}