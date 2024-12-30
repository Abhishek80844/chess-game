package core.sebas.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sebas.core.Board;
import com.sebas.core.Match;
import com.sebas.core.Movement;
import com.sebas.core.Square;

/**
 * Servlet class
 * @author srevuelta
 *
 */
public class MatchServlet extends HttpServlet {
	
	private static final String MESSAGE_TO_PLAY = "Click play button or insert move as e2e4";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(MatchServlet.class);


	@Override
	protected void doGet(HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		
		String move = request.getParameter("move");
		boolean shouldMove = true;
		if (move != null) shouldMove = false;
		
		PrintWriter out = response.getWriter();
		Match match = updateMatch(session);
		
		try {
			StringBuffer html = paintMatch(match, move, shouldMove, MESSAGE_TO_PLAY);
			out.println(html.toString());
		    session.setAttribute("match", match);
		}
		catch(Exception e) {
			log.error("Error in the servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		
		String move = request.getParameter("move");
		boolean shouldMove = true;
		if (move != null) shouldMove = false;
		
		PrintWriter out = response.getWriter();
		Match match = updateMatch(session);
		
		String message = (String) session.getAttribute("message");
		try {
			StringBuffer html = paintMatch(match, move, shouldMove, message);
			out.println(html.toString());
		    session.setAttribute("match", match);
		    session.setAttribute("shouldMove",true);
		}
		catch(Exception e) {
			log.error("Error in the servlet" + this.getClass().getName());
		}
		finally {out.close();}
	}
	
	/**
	 * update Match
	 * @param session
	 * @return
	 */
	private Match updateMatch(HttpSession session) {
		
		Match match = (Match) session.getAttribute("match");
		if (match == null) { match = new Match("white"); }
		
		String player1 = (String)session.getAttribute("player1");
		String player2 = (String)session.getAttribute("player2");
		String timer = (String)session.getAttribute("timer");
		match.setPlayer1(player1);
		match.setPlayer2(player2);
		match.setTimer(timer);
		return match;
	}

	/**
	 * paint Match
	 * @param match
	 * @param shouldMove
	 * @param message
	 * @return
	 */
	private StringBuffer paintMatch(Match match, String move, boolean shouldMove, String message) {
		
		StringBuffer sb = new StringBuffer();
		Board board = match.getBoard();
		if (shouldMove) {
		  match.justMove(null);
		}
		else {
		  Movement movement = new Movement(board,move);
		  match.justMove(movement);
		}

		String history = match.getHistoryMatch();
		Square[][] squares = board.getSquares();
		
		sb.append("<head>");
		sb.append("<meta http-equiv='content-type' content='text/html; charset=utf-8' />");
		sb.append("<title>Vulnerable Chess Game</title>");
		sb.append("<script src='./js/ajax.js' language='JavaScript'></script>");
		sb.append("<link rel='stylesheet' type='text/css' href='./css/squares.css' media='screen' />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2>Vulnerable Chess Game</h2>");

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square square = squares[i][j];
				String casilla = square.getHorizontal()+square.getVertical();
				
				if (!square.isEmpty()) {
					
				 String pieza = square.getPieza().getType();
				 char color = square.getPieza().getColor().charAt(0);
				 sb.append("<div id='" + casilla  + "'><img src='./images/" + pieza + color + ".png'/></div>");
				}
				else {
					sb.append("<div id='" + casilla + "'></div>");
				}
			}
		}			
		sb.append("<div id='move'>");
		sb.append("<form action='./Play' method='GET'>");
		sb.append("<input id='text' name='movement' value=''>");
		sb.append("<input id='button' type='submit' value='Play'>");
		sb.append("</form>");
		sb.append("</div>");
		sb.append("<div id='match'>" + history + "</div>");
		sb.append("<div id='player1'>" + "Player 1: " + match.getPlayer1() + "</div>");
		sb.append("<div id='player2'>" + "Player 2: " + match.getPlayer2()  + "</div>");
		sb.append("<div id='timer1'>" + "Time: " + match.getTimer() + ":00" + "</div>");
		sb.append("<div id='timer2'>" + "Time: " + match.getTimer() + ":00" + "</div>");
		sb.append("<div id='play'> <a href='./Play'>Play</a></div>");
		sb.append("<div id='new'> <a href='./Game'>New</a></div>");
		sb.append("<div id='save'> <a href='./Save'>Save</a></div>");
		sb.append("<div id='load'> <a href='./Load'>Load</a></div>");
		sb.append("<div id='message'>" + message + "</div>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb;
	}
}
