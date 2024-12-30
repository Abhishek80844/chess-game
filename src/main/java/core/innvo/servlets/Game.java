package core.sebas.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Game extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String player1 = "unknown";
		String player2 = "unknown";
		String timer = "0";
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("timer")) {
			   timer = cookie.getValue();
		    }
		   if (cookie.getName().equals("player1")) {
			   player1 = cookie.getValue();
		    }
		   if (cookie.getName().equals("player2")) {
			   player2 = cookie.getValue();
		    }
		  }
		}
		session.setAttribute("timer",timer);
		session.setAttribute("player1",player1);
		session.setAttribute("player2",player2);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/html/game.jsp");
		requestDispatcher.forward(request, response);
	}
	
}
