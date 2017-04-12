package servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Agency;
import model.Agent;
import model.Customer;

/**
 * Servlet implementation class Agents
 */
@WebServlet("/Agents")
public class Agents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String agentid = request.getParameter("id");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
			PreparedStatement pst = con.prepareStatement("SELECT AgentId,AgtFirstName,AgtLastName,AgtBusPhone,AgtEmail,AgtPosition,agt.AgencyId,AgncyAddress,AgncyCity,AgncyProv,AgncyPostal FROM agents agt INNER JOIN Agencies a WHERE AgentId = ?");
			//PreparedStatement ps= con.prepareStatement("Select AgncyAddress,AgncyCity,AgncyProv,AgncyPostal FROM agencies WHERE AgentId = ?" );
			pst.setString(1,agentid );
			//ps.setString(1, agentid);
			System.out.println(agentid);
			
			ResultSet rs = pst.executeQuery();
			
			//Customer c = new Customer();
			Agent a= new Agent();
			Agency ag= new Agency();
			if(rs.next()){
				a.setAgentId(rs.getInt("AgentId"));
				a.setAgtFirstName(rs.getString("AgtFirstName"));
				a.setAgtLastName(rs.getString("AgtLastName"));
				a.setAgtBusPhone(rs.getString("AgtBusPhone"));
				a.setAgtEmail(rs.getString("AgtEmail"));
				a.setAgtPosition(rs.getString("AgtPosition"));
			//	a.setAgencyId(rs.getInt("agt.AgencyId"));
				ag.setAgncyAddress(rs.getString("AgncyAddress"));
			    ag.setAgncyCity(rs.getString("AgncyCity"));
			    ag.setAgncyProv(rs.getString("AgncyProv"));
			    ag.setAgncyPostal(rs.getString("AgncyPostal"));
			    a.setAgncy(ag);
			
			
				//c.setCustomerId(Integer.parseInt(customerid));
				
			}
			Gson gson = new Gson();
			Type type = new TypeToken<Agent>(){}.getType();
			
			String JSON = gson.toJson(a,type);
			response.setContentType("text/plain");
			response.getWriter().write(JSON);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("null");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
