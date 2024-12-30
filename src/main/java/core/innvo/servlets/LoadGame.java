package core.sebas.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sebas.core.Match;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class LoadGame extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(LoadGame.class);


	@Override
	protected void doPost(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String file = request.getParameter("file");
		
		if (file.startsWith("/matches/")) {
	        File matchFile = new File(file);
	        StringBuffer sb = new StringBuffer();
	        try (BufferedReader br = new BufferedReader(new FileReader(matchFile))) {
				String st; 
				while ((st = br.readLine()) != null) {
				  sb.append(st);
				}
			} 
	        Match match = (Match) session.getAttribute("match");
	        match.setHistoryMatch(sb.toString());
	        session.setAttribute("match", match);
			session.setAttribute("shouldMove",false);
			session.setAttribute("message","Match correctly load from: " + matchFile.getAbsolutePath());
		}
		else {
			log.error("File should start with /matches/");
			session.setAttribute("shouldMove",false);
			session.setAttribute("message","Path file should start with /matches/");

		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./Play");
		requestDispatcher.forward(request, response);
        
	}
}
