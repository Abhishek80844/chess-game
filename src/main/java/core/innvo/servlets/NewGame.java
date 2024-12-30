package core.sebas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sebas.core.Board;
import com.sebas.core.Match;
import com.sebas.core.Square;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class NewGame extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(NewGame.class);
	private Match match;
	public Match getMatch() { return match; }
	public void setMatch(Match match) { this.match = match; }


	@Override
	protected void doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		Match match = new Match("white"); 
		Board board = match.getBoard();
		Square[][] squares = board.getSquares();

		try {
			out.println("<head>");
			out.println("<meta http-equiv='content-type' content='text/html; charset=utf-8' />");
			out.println("<title>Vulnerable Chess Game</title>");
			out.println("<script src='./js/ajax.js' language='JavaScript'></script>");
			out.println("<link rel='stylesheet' type='text/css' href='./css/squares.css' media='screen' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<h4>Vulnerable Chess Game</h4>");

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					Square square = squares[i][j];
					String casilla = square.getHorizontal()+square.getVertical();
					
					if (!square.isEmpty()) {
						
					 String pieza = square.getPieza().getType();
					 char color = square.getPieza().getColor().charAt(0);
					 out.println("<div id='" + casilla  + "'><img src='./images/" + pieza + color + ".png'/></div>");
					}
					else {
						out.println("<div id='" + casilla + "'></div>");
					}
				}
			}			
		    out.println("<div id='match'>" + "</div>");
		    out.println("<div id='play'> <a href='./Play'>Play</a></div>");
		    out.println("<div id='new'> <a href='./New'>New</a></div>");
		    out.println("<div id='save'> <a href='./Save'>Save</a></div>");
		    out.println("<div id='load'> <a href='./Load'>Load</a></div>");
		    out.println("</body>");
		    out.println("</html>");

		}
		catch(Exception e) {
			log.error("Error in the servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
}
