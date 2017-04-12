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

/**
 * Servlet implementation class Agencies
 */
@WebServlet("/Agencies")
public class Agencies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agencies() {
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
			//PreparedStatement pst = con.prepareStatement("SELECT AgentId,AgtFirstName,AgtLastName,AgtBusPhone,AgtEmail,AgtPosition,AgencyId FROM agents WHERE AgentId = ?");
			PreparedStatement ps= con.prepareStatement("Select AgncyAddress,AgncyCity,AgncyProv,AgncyPostal FROM agencies WHERE AgentId = ?" );
			//pst.setString(1,agentid );
			ps.setString(1, agentid);
			System.out.println(agentid);
			
			ResultSet rs = ps.executeQuery();
			
			//Customer c = new Customer();
			//Agent a= new Agent();
			Agency a = new Agency();
			
			if(rs.next()){
			
				a.setAgncyAddress(rs.getString("AgncyAddress"));
				a.setAgncyCity(rs.getString("AgncyCity"));
				a.setAgncyProv(rs.getString("AgncyProv"));
				a.setAgncyPostal(rs.getString("AgncyPostal"));
				
			}
			Gson gson = new Gson();
			Type type = new TypeToken<Agency>(){}.getType();
			
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
