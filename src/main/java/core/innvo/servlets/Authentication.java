package core.sebas.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Authentication extends HttpServlet {
	
	private static Logger log = Logger.getLogger(Authentication.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (Exception e) {}
	}

	@Override
	protected void doPost(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

		PropertyConfigurator.configure("log4j.properties");

		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.debug("Login user: " + username);
		
		boolean success = false;

		String query = "select * from user where username='" + username + "' and password = '" + password + "'";
		Connection conn = null;
		Statement stmt = null;
		Properties prop = new Properties();

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		if (inputStream != null) {
			prop.load(inputStream);
		}
		else {   
			log.error("property file config.properties not found in the classpath");
			throw new FileNotFoundException("property file config.properties not found in the classpath");
		}
		
		String userDB = prop.getProperty("userDB");
		String pwdDB = prop.getProperty("pwdDB");
		String hostDB = prop.getProperty("hostDB");
		String portDB = prop.getProperty("portDB");
		
		String connectionChain = "jdbc:mysql://" + hostDB + ":" + portDB + "/chess?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
		
		log.debug("Connecting to database with credentials: " + userDB + " and password: ********");
		log.debug("Database chain: " + connectionChain);
		try {
			conn = DriverManager.getConnection(connectionChain, userDB, pwdDB);
			stmt = conn.createStatement();
			
			//SQL injection. No validation of malicious input
			ResultSet rs = stmt.executeQuery(query);  
	
			List<String> list = new ArrayList<String>();							
			while (rs.next()) {
				// Login Successful if match is found
				success = true;

				list.add(rs.getString("username"));
				list.add(rs.getString("password"));
				session.setAttribute("user", list);
			}
		} catch (Exception e) {
			log.error("Error to database: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				log.error("Error closing connection: " + e.getMessage());
			}
		}
		
		RequestDispatcher requestDispatcher = null;

		if (success) {
			requestDispatcher = request.getRequestDispatcher("/html/timer.jsp");
			requestDispatcher.forward(request, response);
		} else {
			log.error("Error in the connection to database chess");
			session.setAttribute("error", "Username or Password incorrect");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			requestDispatcher = request.getRequestDispatcher("/html/login.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	
}
