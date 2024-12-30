package core.sebas.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class FirstAuthentication extends HttpServlet {

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
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean success = false;

		Connection conn = null;
		Statement stmt = null;
		Properties prop = new Properties();

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		if (inputStream != null) {
			prop.load(inputStream);
		}
		else {   
			throw new FileNotFoundException("property file config.properties not found in the classpath");
		}
		
		String userDB = prop.getProperty("userDB");
		String pwdDB = prop.getProperty("pwdDB");
		String hostDB = prop.getProperty("hostDB");
		String portDB = prop.getProperty("portDB");
		
		String connectionChain = "jdbc:mysql://" + hostDB + ":" + portDB + "/chess?serverTimezone=UTC";
		try {
			conn = DriverManager.getConnection(connectionChain, userDB, pwdDB);
			stmt = conn.createStatement();
			
		    PreparedStatement sqlStatement = conn.prepareStatement("select username,password from secure_user "
		    		+ "where username=? and password=SHA1(?)");
		    sqlStatement.setString(1, username);
		    sqlStatement.setString(2, password);
		    ResultSet rs = sqlStatement.executeQuery();
			
			List<String> list = new ArrayList<String>();							
			while (rs.next()) {
				// Login Successful if match is found
				success = true;

				list.add(rs.getString("username"));
				list.add(rs.getString("password"));
				session.setAttribute("user", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {}
		}
		
		RequestDispatcher requestDispatcher = null;

		if (success) {
			requestDispatcher = request.getRequestDispatcher("/html/login.jsp");
			requestDispatcher.forward(request, response);
		} else {
			session.setAttribute("error", "Username or Password incorrect");
			requestDispatcher = request.getRequestDispatcher("/html/firstlogin.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	
}
